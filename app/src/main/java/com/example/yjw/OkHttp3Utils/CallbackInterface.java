package com.example.yjw.OkHttp3Utils;

/**
 * 定义回调接口
 */

public abstract class CallbackInterface {
    // 请求成功时的回调函数
    public abstract void onMySuccess(String result, int flag);

    // 请求失败时的回调函数
    public abstract void onMyError(String result);
}
