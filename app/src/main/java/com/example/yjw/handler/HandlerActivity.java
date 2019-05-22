package com.example.yjw.handler;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.yjw.common.BaseActivity;
import com.example.yjw.myviewpager.R;

public class HandlerActivity extends BaseActivity implements View.OnClickListener {

    private static final  int UPDATE_TEXT=1;
    private TextView textView;
    private Button change_text;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_handler);

        textView = findViewById(R.id.handlertext);
        change_text = findViewById(R.id.change_text);
        change_text.setOnClickListener(this);
    }

    private Handler handler =new Handler(Looper.getMainLooper()){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case UPDATE_TEXT:
                    //在这里可以进行UI操作
                    textView.setText("Nice to meet you");
                    break;
                    default:
                        break;
            }

        }
    };

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.change_text:
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        Message message=new Message();
                        message.what = UPDATE_TEXT;
                        message.obj = "asdf";
                        handler.sendMessage(message);//将message对象发出去
                    }
                }).start();
                break;
                default:
                    break;
        }
    }
}

