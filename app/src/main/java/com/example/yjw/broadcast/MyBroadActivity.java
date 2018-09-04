package com.example.yjw.broadcast;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.yjw.common.BaseActivity;
import com.example.yjw.myviewpager.R;

/**
 * 发送标准广播
 * 发送有序广播
 */
public class MyBroadActivity extends BaseActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_broad);

        Button button =findViewById(R.id.btn_send);
        button.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_send:
                Intent intent =new Intent("com.example.broadcasttest.MY_BROADCAST");
                sendBroadcast(intent);//标准广播
                //sendOrderedBroadcast(intent,null);//有序广播
                break;
                default:
                    break;
        }
    }
}
