package com.example.yjw.HandlerThread;

import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.widget.TextView;

import com.example.yjw.Interface.Sendmessage;
import com.example.yjw.myviewpager.BaseActivity;
import com.example.yjw.myviewpager.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * HandlerThread 处理复杂操作
 */
public class HandlerThreadActivity extends BaseActivity {
    @BindView(R.id.tv_handler)
    TextView tvHandler;
    private HandlerThread mhandlerThread;
    private Handler mThreadHandler; //子线程中的Handler
    private Handler mMainHandler;   //主线程（UI线程）中的Handler

    private static final int THREAD = 1;
    private boolean isstop ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_handler_thread);
        ButterKnife.bind(this);

        intThread();


    }

    @Override
    protected void onResume() {
        super.onResume();
        //开始查询
        isstop = true;
        mThreadHandler.sendEmptyMessage(THREAD);
    }

    @Override
    protected void onPause() {
        super.onPause();
        //停止查询
        //以防退出界面后Handler还在执行
        isstop = false;
        mThreadHandler.removeMessages(THREAD);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //释放资源
        mhandlerThread.quit();
    }

    private void intThread() {
        //开启HandlerThread子线程
        mhandlerThread = new HandlerThread("handler_thread");
        mhandlerThread.start();

        //子线程handler
        mThreadHandler = new Handler(mhandlerThread.getLooper()) {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                switch (msg.what){
                    case THREAD:
                        updata();
                        if (isstop) {
                            mThreadHandler.sendEmptyMessage(THREAD);
                        }
                        break;
                }

            }
        };

        //主线程handler（Looper.getMainLooper()加不加都可以）
        mMainHandler = new Handler(Looper.getMainLooper()){
            @Override
            public void handleMessage(Message msg)
            {
                switch (msg.what){
                    case 1:
                        String result ="每隔两秒发送更新一次数据：";
                        result += (int)(Math.random()*10);
                        tvHandler.setText(result);
                        break;
                }
            }
        };
    }

    private void updata() {
        try {
            Thread.sleep(2000);
            //handler改变UI界面用post方法或者sendmessage()
            /*mMainHandler.post(new Runnable() {
                @Override
                public void run() {
                    String result ="每隔两秒发送更新一次数据：";
                    result += (int)(Math.random()*10);
                    tvHandler.setText(result);
                }
            });*/
            mMainHandler.sendEmptyMessage(1);

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
