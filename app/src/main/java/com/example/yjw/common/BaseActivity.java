package com.example.yjw.common;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.example.yjw.BroadCasrTest.LoginActivity;

/**
 * Created by YJW on 2018/1/8.
 */

public class BaseActivity extends AppCompatActivity {
    private ForceOfflinReceiver receiver;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("BaseActivity",getClass().getSimpleName());
        ActivityCollecter.addActivity(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        IntentFilter intentFilter =new IntentFilter();
        intentFilter.addAction("com.example.FORCE_OFFICE");
        receiver =new ForceOfflinReceiver();
        registerReceiver(receiver,intentFilter);
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (receiver !=null){
            unregisterReceiver(receiver);
            receiver =null;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ActivityCollecter.removeActivity(this);
    }

    class ForceOfflinReceiver extends BroadcastReceiver{

        @Override
        public void onReceive(final Context context, final Intent intent) {
            AlertDialog.Builder builder =new AlertDialog.Builder(context);
            builder.setTitle("warning");
            builder.setCancelable(false);
            builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    ActivityCollecter.finishAll();
                    Intent intent1 =new Intent(context, LoginActivity.class);
                    startActivity(intent1);
                }
            });
            builder.show();
        }
    }
}
