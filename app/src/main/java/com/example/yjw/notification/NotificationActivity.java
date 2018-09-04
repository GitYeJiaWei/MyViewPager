package com.example.yjw.notification;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;

import com.example.yjw.common.BaseActivity;
import com.example.yjw.myviewpager.MainActivity;
import com.example.yjw.myviewpager.R;

import java.io.File;

/**
 * 手机顶部通知栏
 */
public class NotificationActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);

        initview();
    }

    private void initview(){
        Intent intent =new Intent(this, MainActivity.class);
        PendingIntent pi=PendingIntent.getActivity(this,0,intent,0);

        NotificationManager manager =(NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        Notification notification =new NotificationCompat.Builder(getApplicationContext())
                .setContentTitle("这是通知title")
                .setContentText("这是通知text")
                .setWhen(System.currentTimeMillis())
                .setSmallIcon(R.mipmap.ker)
                .setLargeIcon(BitmapFactory.decodeResource(getResources(),R.mipmap.ket))
                .setContentIntent(pi)
                .setStyle(new NotificationCompat.BigPictureStyle().bigPicture(
                        BitmapFactory.decodeResource(getResources(),R.mipmap.ic_launcher)
                ))
                .setPriority(NotificationCompat.PRIORITY_MAX)
                .setAutoCancel(true)
                .setSound(Uri.fromFile(new File("/system/media/audio/ringtones/Luna.ogg")))
                .build();

        manager.notify(1,notification);

    }
}
