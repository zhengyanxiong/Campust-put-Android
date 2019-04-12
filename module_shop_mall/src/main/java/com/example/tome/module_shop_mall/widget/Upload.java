package com.example.tome.module_shop_mall.widget;


import android.webkit.JavascriptInterface;

import com.example.tome.core.constants.Constants;
import com.example.tome.core.util.L;
import com.example.tome.core.util.ToastUtils;
import com.example.tome.module_shop_mall.arouter.RouterCenter;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class Upload {

    OnImageUploadResult imageUploadResult;

    public interface OnImageUploadResult {
        void getImageUrl(String url);
    }

    public void setOnImageUploadResult(OnImageUploadResult uploadResult){
        this.imageUploadResult = uploadResult;
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
                            //imageUploadResult.getImageUrl(url);
                            L.d("图片上传成功，返回图片地址：" + url);
                        } else {
                            L.d( "图片上传失败！");
                        }

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                } else {
                    L.d("lfq", response.message() + " error : body " + response.body().string());
                }
            }
        });

    }

}
