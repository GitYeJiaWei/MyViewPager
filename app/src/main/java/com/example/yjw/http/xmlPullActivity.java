package com.example.yjw.http;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.yjw.common.BaseActivity;
import com.example.yjw.myviewpager.R;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.StringReader;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class xmlPullActivity extends BaseActivity implements View.OnClickListener {
    private TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xml_pull);
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
                    parsexmlwithpull(responseData);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    private void parsexmlwithpull(String xmlData){
        try {
            XmlPullParserFactory factory =XmlPullParserFactory.newInstance();
            XmlPullParser xmlPullParser =factory.newPullParser();
            xmlPullParser.setInput(new StringReader(xmlData));
            int eventType =xmlPullParser.getEventType();
            String id ="";
            String name ="";
            String version ="";
            while(eventType !=XmlPullParser.END_DOCUMENT){
                String nodename =xmlPullParser.getName();
                switch (eventType){
                    //开始解析某个节点
                    case XmlPullParser.START_TAG:{
                        if ("id".equals(nodename)){
                            id=xmlPullParser.nextText();
                        }else if ("name".equals(nodename)){
                            name =xmlPullParser.nextText();
                        }else if ("version".equals(nodename)){
                            version =xmlPullParser.nextText();
                        }
                        break;
                    }
                    case XmlPullParser.END_TAG:{
                        if ("app".equals(nodename)){
                            Log.d("xmlPullActivity", "parsexmlwithpull: ");
                        }
                        break;
                    }
                    default:
                        break;
                }
                eventType =xmlPullParser.next();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
