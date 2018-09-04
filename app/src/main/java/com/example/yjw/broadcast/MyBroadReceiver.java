package com.example.yjw.broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

/**
 * 静态接收广播
 */
public class MyBroadReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context,"接受广播",Toast.LENGTH_LONG).show();
        abortBroadcast();//截断广播
    }
}
