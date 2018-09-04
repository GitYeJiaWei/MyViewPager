package com.example.yjw.common;

import android.app.Application;
import android.content.Context;


public class AppAplication extends Application {

    private static Context mcontext;

    @Override
    public void onCreate() {
        super.onCreate();
        this.mcontext =this;
        // 异常处理，不需要处理时注释掉这两句即可！
        CrashHandler crashHandler = CrashHandler.getInstance();
        // 注册crashHandler,//初始化全局异常管理
        crashHandler.init(getApplicationContext());
    }

    public static Context getMcontext(){
        return mcontext;
    }
}
