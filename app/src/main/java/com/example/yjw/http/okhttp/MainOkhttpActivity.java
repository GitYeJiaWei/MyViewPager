package com.example.yjw.http.okhttp;

import android.os.Bundle;
import com.example.yjw.common.BaseActivity;
import com.example.yjw.myviewpager.R;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.IOException;
import okhttp3.Call;
import okhttp3.Response;

public class MainOkhttpActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_okhttp);

        HttpUtil.sendOkhttpRequest("http://www.baidu.com",new okhttp3.Callback(){

            @Override
            public void onFailure(Call call, IOException e) {
                //在这里对异常情况进行处理
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                //得到服务器返回的具体内容，进行处理
                String responseData =response.body().string();
                parseJSONWithJAONObject(responseData);
            }
        });
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
