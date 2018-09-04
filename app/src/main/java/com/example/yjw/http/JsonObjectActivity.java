package com.example.yjw.http;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.yjw.common.BaseActivity;
import com.example.yjw.myviewpager.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class JsonObjectActivity extends BaseActivity implements View.OnClickListener{
    private TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_json_object);
        textView =findViewById(R.id.respon_text);
        Button button =findViewById(R.id.send_request);
        button.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        {
            switch (v.getId()){
                case R.id.send_request:
                    sendRequest();
                    break;
                default:
                    break;
            }

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
                    parseJSONWithJAONObject(responseData);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    private void parseJSONWithJAONObject(String jsonData){
        try {
            JSONObject resultJson = null;
            try {
                resultJson = new JSONObject(jsonData);
            } catch (JSONException e) {
                e.printStackTrace();
                return;
            }
            JSONArray jsonArray =resultJson.optJSONArray("data");
            for (int i=0;i<jsonArray.length();i++){
                JSONObject jsonObject =jsonArray.getJSONObject(i);
                String id=jsonObject.optString("id");
                String name=jsonObject.optString("name");
                String version=jsonObject.optString("version");
            }
           /* JSONArray jsonArray=new JSONArray(jsonData);
            for (int i=0;i<jsonArray.length();i++){
                JSONObject jsonObject =jsonArray.getJSONObject(i);
                String id=jsonObject.getString("id");
                String name=jsonObject.getString("name");
                String version=jsonObject.getString("version");
            }*/
        } catch(Exception e) {
            e.printStackTrace();
        }

    }
}
