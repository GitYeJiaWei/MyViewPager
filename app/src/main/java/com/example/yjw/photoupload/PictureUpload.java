package com.example.yjw.photoupload;

import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.yjw.myviewpager.R;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PictureUpload extends AppCompatActivity {
    String path1, path2;
    File file1, file2;
    @BindView(R.id.myButton1)
    Button myButton1;
    @BindView(R.id.myButton2)
    Button myButton2;
    @BindView(R.id.myButton3)
    Button myButton3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_picture_upload);
        ButterKnife.bind(this);

        initView();
    }

    private void initView() {
        Log.e("cwj", "内置SD卡路径 = " + Environment.getExternalStorageDirectory().getAbsolutePath());
        Log.e("cwj", "手机内存根目录路径  = " + Environment.getDataDirectory().getParentFile().getAbsolutePath());

        //根据地址拿到File
        path1 = Environment.getExternalStorageDirectory().getAbsolutePath() + "/Pictures/Screenshots/p8_1.jpg";
        path2 = Environment.getDataDirectory().getParentFile().getAbsolutePath() + "/Pictures/Screenshots/p9_1.jpg";
        file1 = new File(path1);
        file2 = new File(path2);
    }

    //上传单张图片
    private void uploap1() {
        // 创建 RequestBody，用于封装构建RequestBody，其中`multipart/form-data`为编码类型
        RequestBody requestFile = RequestBody.create(MediaType.parse("multipart/form-data"), file1);

        // MultipartBody.Part ，这里的partName和后端约定好Key，单场图片上传，目前没用到KEY
        MultipartBody.Part part = MultipartBody.Part.createFormData("uploadFile", file1.getName(), requestFile);

        //调用uploadImage上传单张图片
        API.Retrofit().updateImage(part).enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                Toast.makeText(PictureUpload.this, response.message(), Toast.LENGTH_SHORT).show();
                Toast.makeText(PictureUpload.this, response.code() + "", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Toast.makeText(PictureUpload.this, t.toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    //上传多张图片
    private void uploap2() {
        // 创建 RequestBody，用于封装构建RequestBody，其中`multipart/form-data`为编码类型
        RequestBody requestFile = RequestBody.create(MediaType.parse("multipart/form-data"), file1);

        // MultipartBody.Part ，这里的partName和后端约定好Key，目前没用到KEY
        //多张图片上传
        MultipartBody.Part[] file = new MultipartBody.Part[2];
        file[0] = MultipartBody.Part.createFormData("img", file1.getName(), requestFile);
        file[1] = MultipartBody.Part.createFormData("img", file2.getName(), requestFile);

        //调用uploadImage上传多张图片
        API.Retrofit().updateImage(file).enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                Toast.makeText(PictureUpload.this, response.message(), Toast.LENGTH_SHORT).show();
                Toast.makeText(PictureUpload.this, response.code() + "", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Toast.makeText(PictureUpload.this, t.toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    //上传图文
    private void uploap3() {
        // 创建 RequestBody，用于封装构建RequestBody，其中`multipart/form-data`为编码类型
        RequestBody requestFile = RequestBody.create(MediaType.parse("multipart/form-data"), file1);

        // MultipartBody.Part ，这里的partName和后端约定好Key，目前没用到KEY
        //多张图片上传
        MultipartBody.Part[] file = new MultipartBody.Part[2];
        file[0] = MultipartBody.Part.createFormData("img", file1.getName(), requestFile);
        file[1] = MultipartBody.Part.createFormData("img", file2.getName(), requestFile);

        List<MultipartBody.Part> parts = new ArrayList<>();
        parts.add(file[1]);
        parts.add(file[0]);

        //调用uploadImage上传多张图片
        API.Retrofit().feedBack("content","123456",parts).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                Toast.makeText(PictureUpload.this, response.message(), Toast.LENGTH_SHORT).show();
                Toast.makeText(PictureUpload.this, response.code() + "", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Toast.makeText(PictureUpload.this, t.toString(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    @OnClick({R.id.myButton1, R.id.myButton2, R.id.myButton3})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.myButton1:
                uploap1();
                break;
            case R.id.myButton2:
                uploap2();
                break;
            case R.id.myButton3:
                uploap3();
                break;
        }
    }
}
