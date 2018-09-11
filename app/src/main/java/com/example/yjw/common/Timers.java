package com.example.yjw.common;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class Timers {
    private Timer timer;
    private TimerTask timerTask;

    //开始时调用
    private void starttime(){
        if (timer==null){
            timer =new Timer();
        }
        if (timerTask == null){
            timerTask =new TimerTask() {
                @Override
                public void run() {
                    SimpleDateFormat format =new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");
                    Date date =new Date();
                    System.out.print(format.format(date)+"\n");
                }
            };
        }
        timer.schedule(timerTask,0,1000);
    }

    //结束时调用
    private void stoptime(){
        if (timer!=null){
            timer.cancel();
            timer =null;
        }
        if (timerTask!=null){
            timerTask.cancel();
            timerTask =null;
        }
    }
}
