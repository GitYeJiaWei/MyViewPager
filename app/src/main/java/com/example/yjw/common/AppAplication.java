package com.example.yjw.common;

import android.app.Application;
import android.content.Context;

import com.example.yjw.GreenDAO.GreenDaoManager;
import com.squareup.leakcanary.LeakCanary;

import cn.jpush.android.api.JPushInterface;


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
        JPushInterface.setDebugMode(true);
        JPushInterface.init(this);
        //获取设备，设置别名
        String name =DeviceUtils.getAndroidID(this);
        JPushInterface.setAlias(this,0, name);

        //greenDao全局配置,只希望有一个数据库操作对象
        GreenDaoManager.getInstance();

        //内存泄漏检测
        LeakCanary.install(this);
    }

    public static Context getMcontext(){
        return mcontext;
    }
}
