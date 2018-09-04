package com.example.yjw.photoupload;

import android.app.DownloadManager;
import android.os.Bundle;
import android.os.Environment;
import android.telecom.Call;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.yjw.myviewpager.BaseActivity;
import com.example.yjw.myviewpager.R;

import java.io.File;
import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;
import okhttp3.Response;


public class PhotoUploadActivity extends BaseActivity implements View.OnClickListener{
    private  Button btn_up;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo_upload);
        btn_up = (Button) findViewById(R.id.myButton);
        btn_up.setOnClickListener(this);
    }


    /*private void upImage() {
        OkHttpClient mOkHttpClent = new OkHttpClient();
        File file = new File(Environment.getExternalStorageDirectory() + "/HeadPortrait.jpg");
        MultipartBody.Builder builder = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("img", "HeadPortrait.jpg",
                        RequestBody.create(MediaType.parse("image/png"), file));

        RequestBody requestBody = builder.build();

        DownloadManager.Request request = new DownloadManager.Request.Builder()
                .url(url)
                .post(requestBody)
                .build();
        Call call = mOkHttpClent.newCall(request);
        call.enqueue(new Call.Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(PhotoUploadActivity.this, "失败", Toast.LENGTH_SHORT).show();
                    }
                });
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(PhotoUploadActivity.this, "成功", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

    }*/

    @Override
    public void onClick(View v) {
        new Thread(new Runnable() {
            @Override
            public void run() {
               // upImage();
            }
        }).start();
    }
}