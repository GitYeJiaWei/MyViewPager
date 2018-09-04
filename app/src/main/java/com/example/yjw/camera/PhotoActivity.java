package com.example.yjw.camera;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.content.FileProvider;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.yjw.OkHttp3Utils.ImageLoaderUtils;
import com.example.yjw.common.BaseActivity;
import com.example.yjw.myviewpager.R;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public class PhotoActivity extends BaseActivity {
    private Button takephoto;
    public static final int TAKE_PHOTO =1;

    private ImageView picture;
    private Uri imageUri;
    private File outputImage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo);

        picture =findViewById(R.id.imagechose);
        takephoto = findViewById(R.id.takephoto);

        takephoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //创建File对象，用于存储拍照后的图片
                outputImage =new File(getExternalCacheDir(),"output_image.jpg");
                try{
                    if (outputImage.exists()){
                        outputImage.delete();
                    }
                    outputImage.createNewFile();
                }catch (IOException e){
                    e.printStackTrace();
                }
                if (Build.VERSION.SDK_INT>=24){
                    imageUri = FileProvider.getUriForFile(PhotoActivity.this,
                            "com.example.yjw.camera.cameraactivity",outputImage);
                }else{
                    imageUri =Uri.fromFile(outputImage);
                }
                //启动相机程序
                Intent intent =new Intent("android.media.action.IMAGE_CAPTURE");
                intent.putExtra(MediaStore.EXTRA_OUTPUT,imageUri);
                startActivityForResult(intent,TAKE_PHOTO);
            }
        });

        /*获取图片*/
        ImageLoaderUtils imageLoaderUtils =new ImageLoaderUtils(picture);
        imageLoaderUtils.LoadImage("images/fzhc0012018328161812.jpg");

    }

   /* *//**
     * 获取图片
     * 放到onCreate()中
     *//*
    @Override
    protected void onStart() {
        super.onStart();
        ImageLoaderUtils imageLoaderUtils =new ImageLoaderUtils(picture);
        imageLoaderUtils.LoadImage("images/fzhc0012018328161812.jpg");
    }*/


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch(requestCode){
            case TAKE_PHOTO:
                if (resultCode ==RESULT_OK){
                    try {
                        //将拍摄的照片显示出来
                        Log.d("chengong", "data.getData(): ");
                        Bitmap bitmap = BitmapFactory.decodeStream(getContentResolver().openInputStream(imageUri));
                        picture.setImageBitmap(bitmap);
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                }
                break;

            default:
                break;
        }
    }
}
