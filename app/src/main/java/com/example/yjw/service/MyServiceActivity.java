package com.example.yjw.service;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.yjw.common.BaseActivity;
import com.example.yjw.myviewpager.R;

public class MyServiceActivity extends BaseActivity implements View.OnClickListener {

    private MyService.DownloadBinder downloadBinder;

    private ServiceConnection connection=new ServiceConnection() {

        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            downloadBinder =(MyService.DownloadBinder) iBinder;
            downloadBinder.startDownload();
            downloadBinder.getProgerss();
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_service);

        Button start_service=findViewById(R.id.start_service);
        start_service.setOnClickListener(this);

        Button stop_service =findViewById(R.id.stop_service);
        stop_service.setOnClickListener(this);

        Button bind_service =findViewById(R.id.bind_service);
        bind_service.setOnClickListener(this);

        Button unbind_service=findViewById(R.id.unbind_service);
        unbind_service.setOnClickListener(this);

        Button start_IntentService =findViewById(R.id.start_IntentService);
        start_IntentService.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.start_service:
                Intent startIntent =new Intent(this,MyService.class);
                startService(startIntent);//启动服务
                break;
            case R.id.stop_service:
                Intent stopIntent =new Intent(this,MyService.class);
                stopService(stopIntent);   //停止服务
                break;
            case R.id.bind_service:
                Intent bindIntent = new Intent(this,MyService.class);
                bindService(bindIntent,connection,BIND_AUTO_CREATE);//绑定服务
                break;
            case R.id.unbind_service:
                unbindService(connection);
                break;
            case R.id.start_IntentService:
                //打印主线程的ID
                Log.d("MyServiceActivity", "Thread id id"+Thread.currentThread().getId());
                Intent intentService =new Intent(this,MyIntentService.class);
                startService(intentService);
                break;
                default:
                    break;
        }
    }
}
