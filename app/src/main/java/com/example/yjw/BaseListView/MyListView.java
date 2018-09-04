package com.example.yjw.BaseListView;

import android.os.Build;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;
import android.widget.ImageView;
import com.example.yjw.common.BaseActivity;
import com.example.yjw.myviewpager.R;
import java.util.ArrayList;
import java.util.List;

public class MyListView extends BaseActivity {
    private List<Mymodel> _mymodelList =new ArrayList<>();
    private Myadapter myadapter=null;
    private com.example.yjw.BaseListView.AutoListView autoListView=null;
    private SwipeRefreshLayout swipeRefreshLayout;
    private ImageView top;
    int page = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_list_view);

        autoListView =findViewById(R.id.autoListView);
        myadapter = new Myadapter(MyListView.this);
        autoListView.setAdapter(myadapter);

        autoListView.setOnLoadListener(new AutoListView.OnLoadListener() {
            @Override
            public void onLoad() {
                onRefreshfruit();
                page++;
            }

            @Override
            public void onAfterScroll(int firstVisibleItem) {
                if (firstVisibleItem>=2){
                    top.setVisibility(View.VISIBLE);
                }else {
                    top.setVisibility(View.GONE);
                }
            }
        });

        //返回顶部
        top =findViewById(R.id.top);
        top.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Build.VERSION.SDK_INT>=6){
                    autoListView.smoothScrollToPosition(0);//返回顶部由滑动效果
                }else {
                    autoListView.setSelection(0);//直接返回顶部
                }
            }
        });

        //下拉刷新
        swipeRefreshLayout =(SwipeRefreshLayout)this.findViewById(R.id.swipe);
        swipeRefreshLayout.setColorSchemeResources(R.color.colorPrimary);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                page = 1;
                onRefreshfruit();
            }
        });

        initParkingRecord();
    }

    /**
     * SwipeRefreshLayout 上，下拉刷新
     */
    private void onRefreshfruit(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        initParkingRecord();//初始化数据
                        myadapter.notifyDataSetChanged();//更新adapter
                        swipeRefreshLayout.setRefreshing(false);//刷新结束，隐藏刷新进度条

                        autoListView.onLoadComplete();
                        autoListView.setResultSize(9);
                    }
                });
            }
        }).start();
    }

    private void initParkingRecord(){
        for (int i=0;i<10;i++){
            Mymodel a =new Mymodel();
            a.setName("闽DC8U81"+i);
            if (i==2 || i==4){
                a.setSex("未停车");
            }else if (i==1 || i==6 || i==7){
                a.setSex("已停车");
            }else{
                a.setSex("已支付");
            }
            a.setAge(10+i);
            a.setStarttime("2018-12-12 22:22"+i);
            a.setEndtime("2018-12-12 22:22"+i);
            _mymodelList.add(a);
        }
        myadapter.mymodelList = _mymodelList;
        myadapter.notifyDataSetChanged();
    }
}
