package com.example.yjw.service;

import android.app.IntentService;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * Created by yjw on 2018/3/5 0005.
 */

public class MyIntentService extends IntentService {
    public MyIntentService(String name) {
        super("MyIntentService");//调用父类的有参构造函数
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        //打印当前线程的id
        Log.d("MyIntentService", "onHandleIntent: ");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d("MyIntentService", "onDestroy: ");
    }
}
