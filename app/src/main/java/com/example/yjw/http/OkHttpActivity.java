package com.example.yjw.http;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import com.example.yjw.common.BaseActivity;
import com.example.yjw.myviewpager.R;
import java.io.IOException;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class OkHttpActivity extends BaseActivity implements View.OnClickListener{
    private TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ok_http);

        textView =findViewById(R.id.respon_text);
        Button button =findViewById(R.id.send_request);
        button.setOnClickListener(this);
        Button bt_pull =findViewById(R.id.bt_pull);
        bt_pull.setOnClickListener(this);
        Button bt_json =findViewById(R.id.bt_json);
        bt_json.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.bt_pull:
                Intent intent=new Intent(OkHttpActivity.this,xmlPullActivity.class);
                startActivity(intent);
                break;
            case R.id.bt_json:
                Intent bt_json=new Intent(OkHttpActivity.this,JsonObjectActivity.class);
                startActivity(bt_json);
                break;
            case R.id.send_request:
                sendRequest();
                break;
                default:
                    break;
        }

    }

    private void sendRequest(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                OkHttpClient client =new OkHttpClient();
                Request request =new Request.Builder()
                        .url("http://www.baidu.com")
                        .build();
                Response response =client.newCall(request).execute();
                String responseData =response.body().string();
                showResponse(responseData);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    private void showResponse(final String response){
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                //在这里对UI操作，将结果显示在界面上
                textView.setText(response);
            }
        });
    }
}
