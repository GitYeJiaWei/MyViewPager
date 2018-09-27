package com.example.yjw.photoupload.photoupload2;

import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;

import com.example.yjw.common.DialogUtils;
import com.example.yjw.myviewpager.R;

import java.io.File;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.adapter.rxjava.Result;

/**
 * Retrofit 上传图片
 * 图文上传
 */
public class PictureUpload2 extends AppCompatActivity {
    String path1, path2;
    @BindView(R.id.Buttom)
    Button Buttom;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_picture_upload2);
        ButterKnife.bind(this);

    }

    @OnClick(R.id.Buttom)
    public void onViewClicked() {
        upLoad();
    }

    /**
     * 上传图片
     * create by weiang at 2016/5/20 17:33.
     */
    private void upLoad() {
        //根据地址拿到File
        path1 = Environment.getExternalStorageDirectory().getAbsolutePath() + "/Pictures/Screenshots/p8_1.jpg";
        path2 = Environment.getDataDirectory().getParentFile().getAbsolutePath() + "/Pictures/Screenshots/p9_1.jpg";
        File file = new File(path1);//filePath 图片地址
        File file1 = new File(path2);
        String token = "ASDDSKKK19990SDDDSS";//用户token
        MultipartBody.Builder builder = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)//表单类型
                .addFormDataPart("Content", token)
                .addFormDataPart("ContactInformation","234");//ParamKey.TOKEN 自定义参数key常量类，即参数名

        RequestBody imageBody = RequestBody.create(MediaType.parse("multipart/form-data"), file);
        builder.addFormDataPart("imgfile", file.getName(), imageBody);//imgfile 后台接收图片流的参数名
        builder.addFormDataPart("imgfile1", file1.getName(), imageBody);

        List<MultipartBody.Part> parts = builder.build().parts();
        ApiUtil.uploadMemberIcon(parts).enqueue(new Callback<Result<String>>() {//返回结果
            @Override
            public void onResponse(Call<Result<String>> call, Response<Result<String>> response) {
               DialogUtils.ShowToast(PictureUpload2.this, response.message());
               DialogUtils.ShowToast(PictureUpload2.this, response.code()+"");
            }

            @Override
            public void onFailure(Call<Result<String>> call, Throwable t) {
                DialogUtils.ShowToast(PictureUpload2.this, t.getMessage());
            }
        });
    }

}
