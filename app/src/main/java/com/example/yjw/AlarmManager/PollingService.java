package com.example.yjw.AlarmManager;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import com.example.yjw.myviewpager.MainActivity;
import com.example.yjw.myviewpager.R;

/**
 * @author YJW
 * @create 2018/7/3
 * @Describe
 */
public class PollingService extends Service {
    public static final String ACTION = "com.example.yjw.AlarmManager.PollingService";

    private NotificationManager mManager;
    private Notification notification1;

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        initNotifiManager();
        new PollingThread().start();
    }

    //初始化通知栏配置
    private void initNotifiManager() {
        mManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
    }

    //弹出Notification
    private void showNotification() {

        Notification.Builder builder1 = new Notification.Builder(this);
        builder1.setSmallIcon(R.mipmap.ic_launcher); //设置图标
        builder1.setContentTitle("通知"); //设置标题
        builder1.setContentText("你有新的消息"); //消息内容
        builder1.setWhen(System.currentTimeMillis()); //发送时间
        builder1.setDefaults(Notification.DEFAULT_ALL); //设置默认的提示音，振动方式，灯光
        builder1.setAutoCancel(true);//打开程序后图标消失
        Intent intent =new Intent (this,MainActivity.class);
        PendingIntent pendingIntent =PendingIntent.getActivity(this, 0, intent, 0);
        builder1.setContentIntent(pendingIntent);
        notification1 = builder1.build();
        mManager.notify(0, notification1);
    }

    /**
     * Polling thread
     * 模拟向Server轮询的异步线程
     *
     * @Author Ryan
     * @Create 2013-7-13 上午10:18:34
     */
    int count = 0;

    class PollingThread extends Thread {
        @Override
        public void run() {
            count++;
            showNotification();

        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
