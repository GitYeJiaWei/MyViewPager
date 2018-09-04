package com.example.yjw.KillProsser;

import android.app.ActivityManager;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

import java.util.List;

/**
 * @author YJW
 * @create 2018/7/27
 * @Describe
 */

public class AutoCleanService extends Service {
    private  ScreenOffReceiver screenOffReceiver;
    private ActivityManager am;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        am =(ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
        screenOffReceiver = new ScreenOffReceiver();
        registerReceiver(screenOffReceiver,new IntentFilter(Intent.ACTION_SCREEN_OFF));
        Log.d("service1", "service");
        super.onCreate();
    }

    @Override
    public void onDestroy() {
        unregisterReceiver(screenOffReceiver);
        screenOffReceiver = null;
        super.onDestroy();
    }
    private  class ScreenOffReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            List<ActivityManager.RunningAppProcessInfo> runningAppProcesses = am.getRunningAppProcesses();
            for (ActivityManager.RunningAppProcessInfo info : runningAppProcesses){
                //进程名字  即为包名
                String processName = info.processName;
                //锁屏的时候杀掉可以杀的
                am.killBackgroundProcesses(processName);
            }
        }
    }
}