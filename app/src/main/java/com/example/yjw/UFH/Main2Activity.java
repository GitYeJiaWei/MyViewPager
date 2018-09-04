package com.example.yjw.UFH;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.ListView;

import com.example.yjw.ACache.ACache;
import com.example.yjw.common.BaseActivity;
import com.example.yjw.myviewpager.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;
import butterknife.ButterKnife;

public class Main2Activity extends BaseActivity {
    @BindView(R.id.tv_list)
    ListView tvList;
    private List<Main2Model> main2Models = new ArrayList<>();
    private MainAdapter mainAdapter =null;
    private int count;
    private ACache aCache;
    private Timer timer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        ButterKnife.bind(this);

        mainAdapter =new MainAdapter(this);
        tvList.setAdapter(mainAdapter);
        tvList.setHorizontalScrollBarEnabled(false);

        aCache =ACache.get(Main2Activity.this);
        if (aCache.getAsString("count") ==null){
            count =2;
        }else{
            count =Integer.valueOf(aCache.getAsString("count"));
        }
        Log.d("count", "count: "+count);
        count+=1;

        final SimpleDateFormat format =new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        onRefreshfruit();
/*       //定时器
        timer=new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                Date date =new Date(System.currentTimeMillis());
                onRefreshfruit();
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        Message message =new Message();
                        message.what =1;
                        handler.sendMessage(message);
                    }
                }).start();
                Log.d("timer", "date:"+format.format(date));
            }
        },0,2000);
    }

    private Handler handler =new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case 1:
                    onRefreshfruit();
                    break;
            }
        }*/
    }

    private void onRefreshfruit(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        initview();//初始化数据
                        mainAdapter.notifyDataSetChanged();//更新adapter
                    }
                });
            }
        }).start();
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (timer !=null){
            timer.cancel();
            timer=null;
        }

    }

    private void initview(){
        SimpleDateFormat format =new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date =new Date(System.currentTimeMillis());
        Main2Model a =new Main2Model();
        
        for (int i=1;i<5;i++){
            a.setAddress("厦门国际会展中心"+i);
            a.setUser("Admin"+i);
            a.setEvent("服装查询"+i);
            a.setRemark("remark"+i);
            a.setTime(format.format(date)+".374+08:00");
            a.setMessage("success");
            a.setStatus("pending");
            a.setTx_id("6451fd17af...e1bc");
            a.setNonce(4138788);
            a.setTimestamp(format.format(date));
            a.setSign("425e1c73c8...3100");
            main2Models.add(a);
        }

        mainAdapter.mymodelList = main2Models;
        mainAdapter.notifyDataSetChanged();
    }
}
