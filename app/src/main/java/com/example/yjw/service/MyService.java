package com.example.yjw.service;

import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Binder;
import android.os.IBinder;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.example.yjw.myviewpager.R;

public class MyService extends Service {
    public MyService() {
    }

    private DownloadBinder mBinder =new DownloadBinder();
    class DownloadBinder extends Binder{

        public void startDownload(){
            Log.d("MyService", "startDownload: ");
        }

        public int getProgerss(){
            Log.d("MyService", "getProgerss: ");
            return 0;
        }

    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d("MyService", "onCreate: TAG");
        Intent intent =new Intent(this,MyServiceActivity.class);
        PendingIntent pi =PendingIntent.getActivity(this,0,intent,0);
        Notification notification=new NotificationCompat.Builder(this)
                .setContentTitle("this is content title")
                .setContentText("this is content text")
                .setWhen(System.currentTimeMillis())
                .setSmallIcon(R.mipmap.ic_launcher)
                .setLargeIcon(BitmapFactory.decodeResource(getResources(),
                        R.mipmap.ic_launcher))
                .setContentIntent(pi)
                .build();
        startForeground(1,notification);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d("MyService", "onStartCommand: TAG");
        return super.onStartCommand(intent, flags, startId);

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d("MyService", "onDestroy: TAG");
    }

    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }
}
