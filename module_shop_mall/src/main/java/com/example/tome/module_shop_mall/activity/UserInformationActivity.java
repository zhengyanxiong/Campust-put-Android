package com.example.tome.module_shop_mall.activity;

import android.content.Intent;
import android.os.Handler;
import android.os.Looper;
import android.webkit.JavascriptInterface;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.example.tome.core.base.mvc.BaseVcActivity;
import com.example.tome.core.constants.Constants;
import com.example.tome.core.util.L;
import com.example.tome.core.util.StatuBarCompat;
import com.example.tome.module_shop_mall.widget.WebViewInitUtils;
import com.example.tome.module_shop_mall.R;
import com.example.tome.module_shop_mall.R2;
import com.example.tome.module_shop_mall.widget.ActionSheetDialog;
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.config.PictureMimeType;
import com.luck.picture.lib.entity.LocalMedia;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class UserInformationActivity extends BaseVcActivity {
    @BindView(R2.id.user_information_web_view)
    WebView webView;
    private final static int Camea_OK = 10000;
    private final static int Pic_OK = 20000;
    private final static int CLIP = 30000;


    @Override
    protected int getLayoutId() {
        return R.layout.mall_activity_user_infrormation_web;
    }

    @Override
    public void initTitle() {

    }

    @Override
    public void initView() {

        mImmersionBar.fitsSystemWindows(true).statusBarColor(R.color.bar_grey).init();
        StatuBarCompat.setImmersiveStatusBar(true, R.color.comment_text, this);

        WebViewInitUtils.init(this, webView,null);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.addJavascriptInterface(new Js(), "appHeaderImageObj");

        webView.loadUrl(Constants.USER_INFORMATION_LINK);
        //点击拦截 true表示拦截, false表示不拦截
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                //view.loadUrl(articleLink);
                return false;
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);

            }
        });

    }


    public void shows() {
        new ActionSheetDialog(getContext())
                .builder()
                .setCancelable(false)
                .setCanceledOnTouchOutside(true)
                .addSheetItem("拍照", ActionSheetDialog.SheetItemColor.Blue, new ActionSheetDialog.OnSheetItemClickListener() {
                    @Override
                    public void onClick(int which) {
                        takePic();
                    }
                })
                .addSheetItem("从相册中选择", ActionSheetDialog.SheetItemColor.Blue, new ActionSheetDialog.OnSheetItemClickListener() {
                    @Override
                    public void onClick(int which) {
                        gallery();
                    }
                }).show();
    }

    //去图库选择
    private void gallery() {
        PictureSelector.create(this)
                .openGallery(PictureMimeType.ofImage())// 全部.PictureMimeType.ofAll()、图片.ofImage()、视频.ofVideo()、音频.ofAudio()
                .theme(R.style.picture_default_style)// 主题样式设置 具体参考 values/styles   用法：R.style.picture.white.style
                .maxSelectNum(1)// 最大图片选择数量
                .minSelectNum(1)// 最小选择数量
                .isCamera(false)// 是否显示拍照按钮
                .imageSpanCount(5)// 每行显示个数
                .compress(false)// 是否压缩 true or false
                .maxSelectNum(1)// 最大图片选择数量
                .isZoomAnim(true)// 图片列表点击 缩放效果 默认true
                .synOrAsy(true)//同步true或异步false 压缩 默认同步
                .glideOverride(120, 120)// glide 加载宽高，越小图片列表越流畅，但会影响列表图片浏览的清晰度
                //.selectionMedia(picList)// 是否传入已选图片
                .minimumCompressSize(100)// 小于100kb的图片不压缩
                .forResult(Pic_OK);//结果回调onActivityResult code
    }

    //拍照
    private void takePic() {
        //单独拍照
        PictureSelector.create(this)
                .openCamera(PictureMimeType.ofImage())
                .theme(R.style.picture_default_style)// 主题样式设置 具体参考 values/styles
                .minSelectNum(1)// 最小选择数量
                .compress(false)
                .selectionMode(PictureConfig.MULTIPLE)// 多选 or 单选
                .isCamera(false)// 是否显示拍照按钮
                .glideOverride(120, 120)// glide 加载宽高，越小图片列表越流畅，但会影响列表图片浏览的清晰度
                //.selectionMedia(picList)// 是否传入已选图片
                .imageSpanCount(5)// 每行显示个数
                .maxSelectNum(1)// 最大图片选择数量
                .isZoomAnim(true)// 图片列表点击 缩放效果 默认true
                .synOrAsy(true)//同步true或异步false 压缩 默认同步
                .minimumCompressSize(100)// 小于100kb的图片不压缩
                .forResult(Camea_OK);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        L.d("requestCode : "+requestCode);
        switch (requestCode) {
            case Camea_OK:
                // 图片选择结果回调
                List<LocalMedia> resultCamera = PictureSelector.obtainMultipleResult(data);
                if(resultCamera != null && resultCamera.size() > 0){
                    Intent cameraIntent = new Intent(this, ClipImageActivity.class);
                    cameraIntent.putExtra("path",resultCamera.get(0).getPath());
                    startActivityForResult(cameraIntent,CLIP);
                }
                break;
            case Pic_OK:
                // 图片选择结果回调
                List<LocalMedia> resultGallery = PictureSelector.obtainMultipleResult(data);
                if(resultGallery != null && resultGallery.size() > 0){
                    Intent picIntent = new Intent(this, ClipImageActivity.class);
                    picIntent.putExtra("path",resultGallery.get(0).getPath());
                    startActivityForResult(picIntent,CLIP);
                }
                break;
            case CLIP:
                if(data != null){
                    String path = data.getStringExtra("result_path");
                    L.d("接收到的要上传的path-------------------》"+path);

                    post_file(new File(path));
                    /*L.d("path : "+path);
                    if(path == null || path.length() == 0){
                        setPlatformType("上传超时！");
                    }else {
                        setPlatformType(path);
                    }*/

                }
                break;
        }

    }

    /*@Override
    public void getImageUrl(String url) {
        setPlatformType(url);
    }*/

    class Js{
        @JavascriptInterface
        public void showHeaderImageDialog() {
            shows();
        }
    }

    public void setPlatformType(final String url) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                Handler handler = new Handler(Looper.getMainLooper());
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        webView.loadUrl("javascript: uploadAvatarIsCompleted('" + url + "')");
                    }
                });
            }
        }).start();

    }

    protected void post_file(File file) {

        final String url = Constants.NGINGX_UPLOAD_LINK;
        OkHttpClient client = new OkHttpClient();
        // form 表单形式上传
        MultipartBody.Builder requestBody = new MultipartBody.Builder().setType(MultipartBody.FORM);
        if (file != null) {
            // MediaType.parse() 里面是上传的文件类型。
            RequestBody body = RequestBody.create(MediaType.parse("image/*"), file);
            String filename = file.getName();
            // 参数分别为， 请求key ，文件名称 ， RequestBody
            requestBody.addFormDataPart("file", file.getName(), body);
        }
       /* if (map != null) {
            // map 里面是请求中所需要的 key 和 value
            for (Map.Entry entry : map.entrySet()) {
                requestBody.addFormDataPart(valueOf(entry.getKey()), valueOf(entry.getValue()));
            }
        }*/
        Request request = new Request.Builder().url(url).post(requestBody.build()).build();
        // readTimeout("请求超时时间" , 时间单位);
        client.newBuilder().readTimeout(6000, TimeUnit.MILLISECONDS).build().newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                L.d("upload faile" + e);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()) {
                    try {
                        String str = response.body().string();
                        JSONObject object = new JSONObject(str);
                        L.d("lfq", response.message() + " , body " + str);

                        if ("200".equals(object.getString("code"))) {
                            JSONObject dataObject = object.getJSONObject("data");
                            String url = dataObject.getString("url");
                            L.d("图片上传成功，返回图片地址：" + url);
                            setPlatformType(url);
                        } else {
                            L.d("图片上传失败！");
                        }

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                } else {
                    L.d("lfq", response.message() + " error : body " + response.body().string());
                    L.d("图片上传失败！");
                }
            }
        });

    }
}
