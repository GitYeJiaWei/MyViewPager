package com.example.yjw.camera;

import android.Manifest;
import android.annotation.TargetApi;
import android.content.ContentUris;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.FileProvider;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.yjw.OkHttp3Utils.CallbackInterface;
import com.example.yjw.OkHttp3Utils.ImageLoaderUtils;
import com.example.yjw.common.DialogUtils;
import com.example.yjw.OkHttp3Utils.HttpService;
import com.example.yjw.OkHttp3Utils.JsonUtil;
import com.example.yjw.OkHttp3Utils.PostParameter;
import com.example.yjw.OkHttp3Utils.RequestResult;
import com.example.yjw.OkHttp3Utils.RequestResultModel;
import com.example.yjw.OkHttp3Utils.sys_users;
import com.example.yjw.common.BaseActivity;
import com.example.yjw.myviewpager.R;
import com.example.yjw.common.Utils;
import com.google.gson.reflect.TypeToken;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * 拍照获取图片
 * 从相册中获取图片
 * 上传图片
 */
public class CameraActivity extends BaseActivity {
    public static final int TAKE_PHOTO =1;
    public static final int CHOOSE_PHOTO=2;

    private ImageView picture;
    private Uri imageUri;
    private File outputImage;
    private Bitmap bitmap =null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);

        //拍照获取图片
        Button takephoto =findViewById(R.id.tale_photo);
        picture =findViewById(R.id.picture);
        takephoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
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
                    imageUri = FileProvider.getUriForFile(CameraActivity.this,
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

        //选择相册中的图片
        Button chooseptoto =findViewById(R.id.choose_from_photo);
        chooseptoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ContextCompat.checkSelfPermission(CameraActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
               != PackageManager.PERMISSION_GRANTED){
                    ActivityCompat.requestPermissions(CameraActivity.this,new String[]
                            {Manifest.permission.WRITE_EXTERNAL_STORAGE},1);
                }else{
                    openAlbm();
                }
            }
        });

        //上传图片
        Button take =findViewById(R.id.take);
        take.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (bitmap != null){
                    UploadHeaderImg(bitmap);
                }else{
                    DialogUtils.ShowToast(CameraActivity.this,"未选择图片");
                    return;//通过if语句判断后，使用return后面的所有代码都不用再执行，注意只能在函数中可以使用
                }
            }
        });


        /**
         * 获取图片
         */
        ImageLoaderUtils imageLoaderUtils =new ImageLoaderUtils(picture);
        imageLoaderUtils.LoadImage("images/fzhc0012018328161812.jpg");
    }


    /**
     * 上传图片
     * @param image
     */
    void UploadHeaderImg(Bitmap image)
    {
        //{"bdh":"1","bh":"2","bz":"3","cph":"4","id":"1","idh":"1","if_see":"1","sj":"03-21 11:13:02","tf":"8","zp":"9"}
        byte[] array = Utils.BitmapToByteArray(image);
        PostParameter parameter = new PostParameter();
        parameter.Add("ID","1");
        parameter.Add("account","fzhc001");
        parameter.Add("img",  Base64.encodeToString(array, Base64.DEFAULT));
        HttpService httpService = new HttpService(this,  "正在上传数据...",1, callbackInterface);
        httpService.postAsyncHttp("/Park/Upload", parameter);
    }

    /**
     * 回调
     * */
    CallbackInterface callbackInterface = new CallbackInterface() {
        @Override
        public void onMySuccess(final String result, final int flag) {
            if(flag == 0) {
                RequestResult Model = JsonUtil.fromJson(result, RequestResult.class);
                DialogUtils.ShowToast(CameraActivity.this, Model.Message);
            }
            if(flag == 1){
                java.lang.reflect.Type type = new TypeToken<RequestResultModel<sys_users>>() {
                }.getType();
                RequestResultModel<sys_users> Model = JsonUtil.fromJson(result, type);
                DialogUtils.ShowToast(CameraActivity.this, Model.Message);
                if(Model.ErrorCode == 0)
                {
                   /* header_img2.setImageBitmap(newHeaderImage);
                    LoginUserInfo.UserModel = Model.DataModel;*/
                }
            }
        }
        @Override
        public void onMyError(final String result){
        }
    };


    private void openAlbm(){
        Intent intent =new Intent("android.intent.action.GET_CONTENT");
        intent.setType("image/*");
        startActivityForResult(intent,CHOOSE_PHOTO);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode){
            case 1:
                if (grantResults.length >0 && grantResults[0] ==PackageManager.PERMISSION_GRANTED){
                    openAlbm();
                }else{
                    Toast.makeText(this,"You denied the permission",Toast.LENGTH_SHORT).show();
                }
                break;
                default:
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch(requestCode){
            case TAKE_PHOTO:
                if (resultCode ==RESULT_OK){
                    try {
                        //将拍摄的照片显示出来
                        bitmap = BitmapFactory.decodeStream(getContentResolver().openInputStream(imageUri));
                        picture.setImageBitmap(bitmap);
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                }
                break;
            case CHOOSE_PHOTO:
                if (resultCode ==RESULT_OK){
                    //判断手机系统版本
                    if (Build.VERSION.SDK_INT>=19){
                        //4.4及以上系统使用这个方法处理图片
                        handleImageOnKitKat(data);
                    }else{
                        //4.4及以下系统使用这个方法
                        handleImageBeforeKitKat(data);
                    }
                }
                    break;
                default:
                    break;
        }
    }
    @TargetApi(19)
    private void handleImageOnKitKat(Intent data){
        String imagePath =null;
        Uri uri =data.getData();
        if (DocumentsContract.isDocumentUri(this,uri)){
            //如果是document 类型的Uri,则通过document id处理
            String docId =DocumentsContract.getDocumentId(uri);
            if ("com.android.providers.media.documents".equals(uri.getAuthority())){
                String id =docId.split(":")[1]; //解析出数字格式的id
                String selection =MediaStore.Images.Media._ID +"=" +id;
                imagePath =getImagePath(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,selection);
            }else if("com.android.providers.downloads.documents".equals(uri.getAuthority())) {
                Uri contentUri = ContentUris.withAppendedId(Uri.parse("content://downloads/public_downloads"),
                        Long.valueOf(docId));
                imagePath = getImagePath(contentUri, null);
            }
            }else if ("content".equalsIgnoreCase(uri.getScheme())){
                //如果是content类型的Uri,则使用普通方式处理
                imagePath =getImagePath(uri,null);
            }else if ("file".equalsIgnoreCase(uri.getScheme())){
                //如果是file类型的Uri,直接获取图片路径即可
                imagePath =uri.getPath();
            }
            displayImage(imagePath);//根据图片路径显示图片

    }

    private void handleImageBeforeKitKat(Intent data){
        Uri uri =data.getData();
        String imagePath =getImagePath(uri,null);
        displayImage(imagePath);
    }

    private String getImagePath(Uri uri,String selection){
        String path =null;
        //通过Uri和selection来获取真实的图片路径
        Cursor cursor =getContentResolver().query(uri,null,selection,null,null);
        if (cursor !=null){
            if (cursor.moveToFirst()){
                path =cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DATA));
            }
            cursor.close();
        }
        return path;
    }

    private void displayImage(String imagePath){
        if (imagePath !=null){
            bitmap =BitmapFactory.decodeFile(imagePath);
            picture.setImageBitmap(bitmap);
        }else{
            Toast.makeText(this,"failed to get image",Toast.LENGTH_SHORT).show();
        }
    }
}
