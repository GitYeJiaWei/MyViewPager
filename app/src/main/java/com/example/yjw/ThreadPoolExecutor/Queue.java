package com.example.yjw.ThreadPoolExecutor;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.util.Log;
import com.example.yjw.common.BaseActivity;
import com.example.yjw.myviewpager.R;
import java.util.concurrent.Executor;

/**
 * 线程安全就是说多线程访问同一代码，不会产生不确定的结果。
 */
public class Queue extends BaseActivity {
    public static final String TAG="QUEUQ";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_queue);

        initview();

    }
    private void initview(){
        Executor executor =new Executor() {
            @Override
            public void execute(@NonNull Runnable command) {
                command.run();
            }
        };

        Runnable runnable1 = new Runnable() {

            @Override
            public void run() {
                // TODO Auto-generated method stub
                Log.d(TAG, "run: Runnable1");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        };

        Runnable runnable2 = new Runnable() {

            @Override
            public void run() {
                // TODO Auto-generated method stub
                Log.d(TAG, "run: Runnable2");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        };

        Runnable runnable3 = new Runnable() {

            @Override
            public void run() {
                // TODO Auto-generated method stub
                Log.d(TAG, "run: Runnable3");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        };

        Runnable runnable4 = new Runnable() {

            @Override
            public void run() {
                // TODO Auto-generated method stub
                Log.d(TAG, "run: Runnable4");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        };

        Runnable runnable5 = new Runnable() {

            @Override
            public void run() {
                // TODO Auto-generated method stub
                Log.d(TAG, "run: Runnable5");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        };

        SerialExecutor serialExecutor =new SerialExecutor(executor);
        serialExecutor.addrun(runnable1);
        serialExecutor.addrun(runnable2);
        serialExecutor.addrun(runnable3);
        serialExecutor.addrun(runnable5);
        serialExecutor.addrun(runnable4);

        serialExecutor.scheduleNext();
    }
}
