package com.example.yjw.broadcast.localbroadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v4.content.LocalBroadcastManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import com.example.yjw.common.BaseActivity;
import com.example.yjw.myviewpager.R;

/**
 * 发送本地广播
 * 打开程序后可用
 */
public class LocalBroadCastActivity extends BaseActivity implements View.OnClickListener {
    private IntentFilter intentFilter;
    private LocalReceiver localReceiver;
    private LocalBroadcastManager localBroadcastManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_local_broad_cast);

        localBroadcastManager = LocalBroadcastManager.getInstance(this);//获取实例
        Button button =findViewById(R.id.btn_local);
        button.setOnClickListener(this);

        intentFilter =new IntentFilter();
        intentFilter.addAction("com.example.yjw.broadcast.LOCAL_BROADCAST");
        localReceiver =new LocalReceiver();

        localBroadcastManager.registerReceiver(localReceiver,intentFilter);//注册本地广播监听器
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //不能用unregisterReceiver(localReceiver);要加上localBroadcastManager
        localBroadcastManager.unregisterReceiver(localReceiver);
    }

    class LocalReceiver extends BroadcastReceiver{
        @Override
        public void onReceive(Context context, Intent intent) {
            Toast.makeText(context,"接收本地广播",Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_local:
                Intent intent =new Intent("com.example.yjw.broadcast.LOCAL_BROADCAST");
                localBroadcastManager.sendBroadcast(intent);//发送本地广播
                break;

                default:
                    break;
        }
    }
}
