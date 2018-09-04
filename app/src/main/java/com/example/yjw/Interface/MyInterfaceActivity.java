package com.example.yjw.Interface;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.yjw.common.BaseActivity;
import com.example.yjw.myviewpager.R;

/**
 * 回调接口
 */
public class MyInterfaceActivity extends BaseActivity {
    private com.example.yjw.Interface.MyThread myThread=null;
    private Button bt1;
    private TextView textView3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_interface);

        bt1 =findViewById(R.id.but1);
        textView3 =findViewById(R.id.textView3);
        myThread =new MyThread();

        bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myThread.setSendmessage(new MyThread.Sendmessage() {
                    @Override
                    public void send(String string) {
                        Message message =new Message();
                        message.what=1;
                        message.obj=string;
                        handler.sendMessage(message);

                    }
                });
                myThread.start();
            }
        });
    }

    public Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case 1:
                    textView3.setText(msg.obj.toString());
                break;
                default:
            }

        }
    };


}
