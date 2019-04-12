package com.example.tome.module_shop_mall.activity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.media.ExifInterface;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.tome.core.constants.Constants;
import com.example.tome.core.util.FileUtils;
import com.example.tome.core.util.L;
import com.example.tome.core.util.ToastUtils;
import com.example.tome.module_shop_mall.R;
import com.example.tome.module_shop_mall.R2;
import com.example.tome.module_shop_mall.base.BaseActivity;
import com.example.tome.module_shop_mall.widget.ClipImageLayout;

import java.io.FileInputStream;
import java.io.IOException;


import butterknife.BindView;


public class ClipImageActivity extends BaseActivity {
    @BindView(R2.id.title_back)
    TextView left_textview;
    @BindView(R2.id.title_content_text)
    TextView center_textview;
    @BindView(R2.id.title_right_text)
    TextView right_textview;
    @BindView(R2.id.clipImageLayout)
    ClipImageLayout clipImageLayout;
    private String path;
    private ViewGroup titleGroup;    //读写权限
    private static String[] PERMISSIONS_STORAGE = {
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE};
    //请求状态码
    private static int REQUEST_PERMISSION_CODE = 1;
    private static int REQUEST_PERMISSION_CODE_SUCCESS = 0;


    @Override
    public int setContentLayout() {
        return R.layout.mall_activity_activity_clip_image;
    }

    @Override
    public void initTitle() {
        center_textview.setText("头像裁剪");
    }

    @Override
    public void initView() {
        titleGroup = (ViewGroup) findViewById(R.id.v_title_container);
        L.d("状态栏titleGroup"+titleGroup);
        if (titleGroup != null) {
            setTitleMargin(titleGroup);
        }

        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                    REQUEST_PERMISSION_CODE);
            L.d("权限控制====开启写的权限");
        }

        path = getIntent().getStringExtra("path");
        L.d("接收到图片的地址为：" + path);
        // 有的系统返回的图片是旋转了，有的没有旋转，所以处理
        int degreee = readBitmapDegree(path);
        Bitmap bitmap = createBitmap(path);
        if (bitmap != null) {
            if (degreee == 0) {
                clipImageLayout.setImageBitmap(bitmap);
            } else {
                clipImageLayout.setImageBitmap(rotateBitmap(degreee, bitmap));
            }
        } else {
            finish();
        }

        left_textview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        right_textview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String file = FileUtils.saveBitmapFile(clipImageLayout.clip(), "head");
                L.d("adfafafasf----------------》"+file);
                Intent intent = new Intent();
                intent.putExtra("result_path", file);
                setResult(1, intent);
                finish();
            }
        });

    }



    private Bitmap createBitmap(String path) {
        if (path == null) {
            return null;
        }

        BitmapFactory.Options opts = new BitmapFactory.Options();
        //不在内存中读取图片的宽高
        opts.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(path, opts);
        int width = opts.outWidth;

        opts.inSampleSize = width > 1080 ? (int) (width / 1080) : 1;//注意此处为了解决1080p手机拍摄图片过大所以做了一定压缩，否则bitmap会不显示

        opts.inJustDecodeBounds = false;// 这里一定要将其设置回false，因为之前我们将其设置成了true
        opts.inPurgeable = true;
        opts.inInputShareable = true;
        opts.inDither = false;
        opts.inPurgeable = true;
        FileInputStream is = null;
        Bitmap bitmap = null;
        try {
            is = new FileInputStream(path);
            bitmap = BitmapFactory.decodeFileDescriptor(is.getFD(), null, opts);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (is != null) {
                    is.close();
                    is = null;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return bitmap;
    }

    // 读取图像的旋转度
    private int readBitmapDegree(String path) {
        int degree = 0;
        try {
            ExifInterface exifInterface = new ExifInterface(path);
            int orientation = exifInterface.getAttributeInt(
                    ExifInterface.TAG_ORIENTATION,
                    ExifInterface.ORIENTATION_NORMAL);
            switch (orientation) {
                case ExifInterface.ORIENTATION_ROTATE_90:
                    degree = 90;
                    break;
                case ExifInterface.ORIENTATION_ROTATE_180:
                    degree = 180;
                    break;
                case ExifInterface.ORIENTATION_ROTATE_270:
                    degree = 270;
                    break;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return degree;
    }

    // 旋转图片
    private Bitmap rotateBitmap(int angle, Bitmap bitmap) {
        // 旋转图片 动作
        Matrix matrix = new Matrix();
        matrix.postRotate(angle);
        // 创建新的图片
        Bitmap resizedBitmap = Bitmap.createBitmap(bitmap, 0, 0,
                bitmap.getWidth(), bitmap.getHeight(), matrix, false);
        return resizedBitmap;
    }

}
