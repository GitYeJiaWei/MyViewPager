package com.example.yjw.AlarmManager;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.SystemClock;
import android.util.Log;

/**
 * @author YJW
 * @create 2018/7/3
 * @Describe 轮询工具类，运用闹钟管理类
 */
public class PollingUtils {
    //开启轮询服务
    public static void startPollingService(Context context, int seconds, Class<?> cls, String action) {
        //获取AlarmManager系统服务
        AlarmManager manager = (AlarmManager) context
                .getSystemService(Context.ALARM_SERVICE);

        //包装需要执行Service的Intent，提供一个pendingInternt对象
        Intent intent = new Intent(context, cls);
        intent.setAction(action);//可加可不加
        //PendingIntent这个类用于处理即将发生的事情。比如在通知Notification中用于跳转页面，但不是马上跳转
        PendingIntent pendingIntent = PendingIntent.getService(context, 0,
                intent, PendingIntent.FLAG_UPDATE_CURRENT);

        //触发服务的起始时间,elapsedRealTime()返回的是系统从启动到现在的时间
        long triggerAtTime = SystemClock.elapsedRealtime();

        //系统会自动检测目前有多少 Alarm 任务存在，然后将触发时间将近的几个任务放在
        //一起执行，这就可以大幅度地减少 CPU 被唤醒的次数，从而有效延长电池的使用时间

        // 这里要注意，如果API>=19，就不能再使用setRepeating，应该改为setWindow
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
           //manager.setExact(AlarmManager.RTC_WAKEUP,triggerAtTime,pendingIntent); 只执行第一次
            manager.setWindow(AlarmManager.RTC_WAKEUP, triggerAtTime,
                    seconds *1000, pendingIntent);
        } else {
            //使用AlarmManger的setRepeating方法设置定期执行的时间间隔（seconds秒）和需要执行的Service
            //该方法用于设置重复闹钟，第一个参数表示闹钟类型，第二个参数表示闹钟首次执行时间，第三个参数表示闹钟两次执行的间隔时间，第三个参数表示闹钟响应动作。
            manager.setRepeating(AlarmManager.RTC_WAKEUP, triggerAtTime,
                    seconds * 1000, pendingIntent);
        }
        }

    //停止轮询服务
    public static void stopPollingService(Context context, Class<?> cls, String action) {
        AlarmManager manager = (AlarmManager) context
                .getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(context, cls);
        intent.setAction(action);
        PendingIntent pendingIntent = PendingIntent.getService(context, 0,
                intent, PendingIntent.FLAG_UPDATE_CURRENT);
        //取消正在执行的服务
        manager.cancel(pendingIntent);

    }
}