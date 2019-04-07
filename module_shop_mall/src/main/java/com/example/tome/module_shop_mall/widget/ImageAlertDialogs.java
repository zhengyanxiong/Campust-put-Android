package com.example.tome.module_shop_mall.widget;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.CursorLoader;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.FileProvider;

import com.bigkoo.alertview.AlertView;
import com.bigkoo.alertview.OnItemClickListener;
import com.example.tome.core.constants.Constants;
import com.example.tome.core.util.L;
import com.example.tome.core.util.PictureCompressionUtils;
import com.example.tome.core.util.ToastUtils;
import com.fec.core.router.arouter.IntentKV;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import permissions.dispatcher.NeedsPermission;
import permissions.dispatcher.RuntimePermissions;

/**
 * 图片选择
 * Created by Administrator on 2018/1/9.
 */
@RuntimePermissions
public class ImageAlertDialogs extends AlertView implements OnItemClickListener {
    private static String TAG = "ImageAlertDialogs";

    private final String IMAGEPATH = "/sdcard/Android/data/com.fecmobile.integrityec/cache";
    private static final int MY_PERMISSIONS_REQUEST_CALL_PHONE2 = 7;
    private Uri imageUri;

    private Activity activity;

    private File outFile;
    private String mImageName;

    OnImageSelectResult selectListener;
    private File currentFile;
    private Uri localUri = null;
    private Uri mPhotoUri;
    File photoFile = null;
    private Gson gson = new Gson();
    //临时文件
    private String mPublicPhotoPath;
    private String mImg_url;
    private String name_url;
    private String mImageFileName;
    private File mPath;

    public ImageAlertDialogs(Activity activity) {
        /*new AlertView("上传头像", null, "取消", null,
                new String[]{"拍照", "从相册中选择"},
                activity, AlertView.Style.ActionSheet, this).show();*/

        super(null, null, "取消", null,
                new String[]{"拍照", "从相册中选择"},
                activity, AlertView.Style.ActionSheet, null);
        super.onItemClickListener = this;
        this.activity = activity;
        initFileCache();
    }

    @NeedsPermission({
            Manifest.permission.CAMERA,
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.READ_EXTERNAL_STORAGE,
    })
    private void initFileCache() {
//        currentFile = new File(IMAGEPATH);

//        if (!currentFile.exists()) {
//            currentFile.mkdirs();
//            mImg_url = IMAGEPATH + "/cacheImage_" + System.currentTimeMillis() + ".jpg";
//            name_url = "/cacheImage_" + System.currentTimeMillis() + ".jpg";
//            currentFile = new File(mImg_url);
////            currentFile = new File(IMAGEPATH +"/cacheImage"+".jpg");
//            if (!currentFile.exists()) {
//                try {
//                    currentFile.createNewFile();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//        } else {
//            mImg_url = IMAGEPATH + "/cacheImage_" + System.currentTimeMillis() + ".jpg";
//            name_url = "/cacheImage_" + System.currentTimeMillis() + ".jpg";
//            currentFile = new File(mImg_url);
//        }
//        imageUri = Uri.fromFile(currentFile);


//
        mPath = null;
        if (hasSdcard()) {
            mPath = Environment.getExternalStoragePublicDirectory(
                    Environment.DIRECTORY_DCIM);

        }
        Date date = new Date();
        String timeStamp = getTime(date, "yyyyMMdd_HHmmss", Locale.CHINA);
        mImageFileName = "Camera/" + "IMG_" + timeStamp + ".jpg";
        File image = new File(mPath, mImageFileName);

        imageUri = Uri.fromFile(image);

        //创建临时图片文件
        try {
            photoFile = createPublicImageFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onItemClick(Object o, int position) {
        if (position == 0) {
            //拍照
            L.d(TAG, "点击了拍照");
            openCamera();
        } else if (position == 1) {
            //选择照片
            L.d(TAG, "点击了照片");
            selectImage();
        }
    }

    public void openCamera() {
        String state = Environment.getExternalStorageState();
        if (state.equals(Environment.MEDIA_MOUNTED)) {
            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

            File file = Environment.getExternalStoragePublicDirectory(
                    Environment.DIRECTORY_DCIM);
            outFile = new File(file, mImageFileName);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                //如果是7.0及以上的系统使用FileProvider的方式创建一个Uri
                imageUri = FileProvider.getUriForFile(contextWeak.get(), "com.fecmobile.integrityec.fileprovider", outFile);
//                mPhotoUri = FileProvider.getUriForFile(contextWeak.get(), "com.fecmobile.integrityec.fileprovider", outFile);
//                mPhotoUri = FileProvider.getUriForFile(contextWeak.get(), "com.fecmobile.integrityec.fileprovider", photoFile);
                intent.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                intent.setFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION);


                if (ContextCompat.checkSelfPermission(activity, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED ||
                        ContextCompat.checkSelfPermission(activity, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(activity, new String[]{Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
//                    Toast.makeText(this,"执行了权限请求",Toast.LENGTH_LONG).show();
                }
            } else {
                //7.0以下使用这种方式创建一个Uri
                imageUri = Uri.fromFile(outFile);
            }
            intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
            intent.putExtra(MediaStore.EXTRA_VIDEO_QUALITY, 1);
            activity.startActivityForResult(intent, IntentKV.FLAG_IMAGE_FROM_CAMERA);
        } else {
            L.e(TAG, "请确认已经插入SD卡");
        }

        //将照片添加到相册中
        galleryAddPic(mPublicPhotoPath, activity);
    }

    /**
     * 将照片添加到相册中
     */
    public static void galleryAddPic(String mPublicPhotoPath, Context context) {
        Intent mediaScanIntent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
        File f = new File(mPublicPhotoPath);
        Uri contentUri = Uri.fromFile(f);
        mediaScanIntent.setData(contentUri);
        context.sendBroadcast(mediaScanIntent);
    }


    public void selectImage() {
        Intent intent = new Intent(Intent.ACTION_PICK, null);
        intent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
        activity.startActivityForResult(intent, IntentKV.FLAG_IMAGE_FROM_ALBUM);
    }

    public void onActivityResult(int flag, Intent data) {

        if (flag == IntentKV.FLAG_IMAGE_FROM_ALBUM) {
            //相册选择
            try {
                String realPathFromURI = getRealPathFromURI(data.getData());

                if (ContextCompat.checkSelfPermission(activity,
                        Manifest.permission.READ_EXTERNAL_STORAGE)
                        != PackageManager.PERMISSION_GRANTED) {

                    L.d(TAG, "权限控制====拥有阅读的权限");
                }

                if (ContextCompat.checkSelfPermission(activity,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE)
                        != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(activity,
                            new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                            MY_PERMISSIONS_REQUEST_CALL_PHONE2);
                }

                /*带截图*/
                if (realPathFromURI.startsWith("file://")) {

                    L.d(TAG, "相册imageUri" + Uri.parse(realPathFromURI));
                    L.d(TAG, "相册" + Uri.parse(realPathFromURI).getPath());

                    final String compressImage = PictureCompressionUtils.compressImage(Uri.parse(realPathFromURI).getPath(), mPublicPhotoPath, 30);

                    final File compressedPic = new File(compressImage);
                    if (compressedPic.exists()) {
                        selectListener.getImage(new File(compressImage));
                    } else {//直接上传
                        selectListener.getImage(new File(Uri.parse(realPathFromURI).getPath()));
                    }


                } else {
//                    Crop crop = Crop.of(Uri.parse("file://" + realPathFromURI), imageUri);
//
//                    L.d("================相册imageUri" + Uri.parse(realPathFromURI));
//                    L.d("================相册" + Uri.parse(realPathFromURI).getPath());
//
//                    crop.asSquare();
//                    crop.start(activity, IFlag.FLAG_IMAGE_CUTTING);


//                            Crop.of(Uri.parse("file://" +realPathFromURI),imageUri).asSquare().start(getActivity(),Flags.REQUESTCODE_CUTTING);
//                            startPhotoZoom(Uri.parse("file://" +realPathFromURI));

                    L.d(TAG, "相册压缩前的路径" + Uri.parse("file://" + realPathFromURI).getPath());
                    final String compressImage = PictureCompressionUtils.compressImage(Uri.parse("file://" + realPathFromURI).getPath(), mPublicPhotoPath, 30);

                    final File compressedPic = new File(compressImage);
                    if (compressedPic.exists()) {
                        post_file(new File(compressImage));

                        L.d(TAG, "相册imageUri有压缩" + Uri.parse(realPathFromURI));
                        L.d(TAG, "相册有压缩" + Uri.parse(realPathFromURI).getPath());
                    } else {//直接上传
                        post_file(new File(Uri.parse("file://" + realPathFromURI).getPath()));
                        L.d(TAG, "相册imageUri没有压缩" + Uri.parse(realPathFromURI));
                        L.d(TAG, "相册没有压缩" + Uri.parse(realPathFromURI).getPath());
                    }
                }
                /*不带截图*/
                L.d(TAG, "realPathFromURI" + realPathFromURI);
//                final String compressImage = PictureCompressionUtils.compressImage(realPathFromURI, mPublicPhotoPath, 30);
//
//                final File compressedPic = new File(compressImage);
//                if (compressedPic.exists()) {
//                    selectListener.getImage(new File(compressImage));
//                }else{//直接上传
//                    selectListener.getImage(new File(realPathFromURI));
//                }


            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (flag == IntentKV.FLAG_IMAGE_FROM_CAMERA) {


            if (ContextCompat.checkSelfPermission(activity,
                    Manifest.permission.READ_EXTERNAL_STORAGE)
                    != PackageManager.PERMISSION_GRANTED) {

                L.d(TAG, "权限控制====拥有阅读的权限");
            }
            File externalCacheDir = activity.getExternalCacheDir();


            //拍照获取
            /*----带截图--*/
//            Crop crop = Crop.of(Uri.fromFile(outFile), imageUri);
//            crop.asSquare();
//            crop.start(activity, IFlag.FLAG_IMAGE_CUTTING);


//            /*----不带截图----*/
            if (selectListener != null) {
//                selectListener.getImage(new File(imageUri.getPath()));


                L.d(TAG, "相机imageUri" + imageUri);
                L.d(TAG, "相机mPhotoUri" + mPhotoUri);
                L.d(TAG, "相机" + imageUri.getPath());
                L.d(TAG, "相机" + new File(imageUri.getPath()));


                if (ContextCompat.checkSelfPermission(activity, Manifest.permission.READ_EXTERNAL_STORAGE)
                        == PackageManager.PERMISSION_GRANTED) {
//                    拥有读写文件权限
                    L.d(TAG, "相机***拥有读写文件权限");
                } else {
                    L.d(TAG, "相机***没有读写权限");
                    //没有读写权限
                    if (ActivityCompat.shouldShowRequestPermissionRationale(activity, Manifest.permission.READ_EXTERNAL_STORAGE)) {
                        ActivityCompat.requestPermissions(activity, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                                140);
                    } else {
                    }
                }

                //这里已经得到了权限了,但如果我们使用imageUrl.getPath 他却一直报FileNotFoundException
                String path2 = "/storage/emulated/0/DCIM/" + mImageFileName;
                final String compressImage = PictureCompressionUtils.compressImage(path2, mPublicPhotoPath, 30);

                final File compressedPic = new File(compressImage);
                if (compressedPic.exists()) {
                    selectListener.getImage(new File(compressImage));
                } else {//直接上传
                    selectListener.getImage(new File(imageUri.getPath()));
                }
            }


        } else if (flag == IntentKV.FLAG_IMAGE_CUTTING) {
            if (selectListener != null) {
                selectListener.getImage(new File(imageUri.getPath()));


                L.d(TAG, "相册" + imageUri);
                L.d(TAG, "相册" + imageUri.getPath());
                L.d(TAG, "相册" + new File(imageUri.getPath()));
            }
        } else if (flag == IntentKV.FLAG_IMAGE_CUTTING2)

        {
            if (selectListener != null) {
            }

        }

    }


    public void setImageSelectListener(OnImageSelectResult selectListener) {
        this.selectListener = selectListener;
    }


    public interface OnImageSelectResult {
        void getImage(File fileName);

    }


    /**
     * 根据content:///图片需要获取图片真实路径
     */
    private String getRealPathFromURI(Uri contentUri) { //传入图片uri地址
        String path = contentUri.getPath();
        String uriString = contentUri.toString();
        if (path.startsWith("file")) {
            return path;
        }
        if (uriString.startsWith("file")) {
            return uriString;
        }
        String[] proj = {MediaStore.Images.Media.DATA};
        CursorLoader loader = new CursorLoader(activity, contentUri, proj, null, null, null);
        Cursor cursor = loader.loadInBackground();
        if (cursor == null) {
            return path;
        }
        int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        cursor.moveToFirst();
        return cursor.getString(column_index);
    }


    public static int calculateInSampleSize(BitmapFactory.Options options, int reqWidth, int reqHeight) {
        final int height = options.outHeight;
        final int width = options.outWidth;
        int inSampleSize = 1;
        if (height > reqHeight || width > reqWidth) {
            final int heightRatio = Math.round((float) height
                    / (float) reqHeight);
            final int widthRatio = Math.round((float) width / (float) reqWidth);
            inSampleSize = heightRatio < widthRatio ? heightRatio : widthRatio;
        }
        return inSampleSize;
    }

    private File createPublicImageFile() throws IOException {
        File path = null;
        if (hasSdcard()) {
            path = Environment.getExternalStoragePublicDirectory(
                    Environment.DIRECTORY_DCIM);//
//          path = Environment.getExternalStoragePublicDirectory(
//                    Environment.DIRECTORY_PICTURES);
        }
        Date date = new Date();
        String timeStamp = getTime(date, "yyyyMMdd_HHmmss", Locale.CHINA);
        String imageFileName = "Camera/" + "IMG_" + timeStamp + ".jpg";
        File image = new File(path, imageFileName);
        mPublicPhotoPath = image.getAbsolutePath();
        return image;
    }


    /**
     * 判断sdcard是否被挂载
     *
     * @return
     */
    public static boolean hasSdcard() {
        if (Environment.getExternalStorageState().equals(
                Environment.MEDIA_MOUNTED)) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 获取时间的方法
     *
     * @param date
     * @param mode
     * @param locale
     * @return
     */
    private String getTime(Date date, String mode, Locale locale) {
        SimpleDateFormat format = new SimpleDateFormat(mode, locale);
        return format.format(date);
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
                        } else {
                            L.d( "图片上传失败！");
                        }

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                } else {
                    L.d("lfq", response.message() + " error : body " + response.body().string());
                    ToastUtils.showLong(activity, "图片上传失败！");
                }
            }
        });

    }
}
