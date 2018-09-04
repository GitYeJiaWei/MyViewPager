package com.example.yjw.AlarmManager;

import android.os.Bundle;
import android.util.Log;

import com.example.yjw.common.BaseActivity;
import com.example.yjw.myviewpager.R;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Timer 定时器
 */
public class MessageActivity extends BaseActivity {
    private Timer timer;
    private Date date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);

        PollingUtils.startPollingService(this, 5, PollingService.class, PollingService.ACTION);
        final SimpleDateFormat format =new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");


        timer =new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                date =new Date();
                Log.d("timer", "run: "+format.format(date));
            }
        },2000,1000);//延迟两秒后开始运行定时器，每隔一秒一次

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        PollingUtils.stopPollingService(this,PollingService.class,PollingService.ACTION);
        if(timer != null){
            timer.cancel();
            // 一定设置为null，否则定时器不会被回收
            timer = null;
        }
    }
}
