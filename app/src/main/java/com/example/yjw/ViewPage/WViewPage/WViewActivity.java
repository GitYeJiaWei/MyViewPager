package com.example.yjw.ViewPage.WViewPage;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.yjw.common.BaseActivity;
import com.example.yjw.common.Utils;
import com.example.yjw.myviewpager.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 简单的广告轮播
 */
public class WViewActivity extends BaseActivity {

    @BindView(R.id.viewpager)
    ViewPager viewpager;
    @BindView(R.id.tv_desc)
    TextView tvDesc;
    @BindView(R.id.ll_point_container)
    LinearLayout llPointContainer;
    private boolean runable;
    private int[] images;
    private String[] titles;
    private List<ImageView> imageViewList ;
    private int previousSelectedPosition = 0;
    private MyAdapter myAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wview);
        ButterKnife.bind(this);

        //model 数据
        initdata();

        //Controller 控制器
        initadapter();

        viewpager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                // 滚动时调用
            }

            @Override
            public void onPageSelected(int position) {
                // 新的条目被选中时调用，此方法是页面跳转完后得到调用，position是你当前选中的页面的Position（位置编号）(从A滑动到B，就是B的position)
                int newposition =position % imageViewList.size();
                //设置文本
                tvDesc.setText(titles[newposition]);
                // 把之前的禁用, 把最新的启用, 更新指示器
                llPointContainer.getChildAt(previousSelectedPosition).setEnabled(false);
                llPointContainer.getChildAt(newposition).setEnabled(true);

                // 记录之前的位置
                previousSelectedPosition=newposition;
                Log.d("newposition", "newposition: "+newposition);
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                // 此方法是在状态改变的时候调用，其中state这个参数有三种状态，跟手指的点击和滑动有关
            }
        });

        //开启轮询
        new Thread(new Runnable() {
            @Override
            public void run() {
                runable = true;
                while (runable){
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {

                            Log.d("random", "run: "+Utils.getRandomString(8));
                            viewpager.setCurrentItem(viewpager.getCurrentItem()+1);
                        }
                    });
                }
            }
        }).start();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //关闭轮询
        runable = false;
    }

    private void initdata(){
        //初始化要显示的数据

        //图片资源id数组
        images =new int[]{R.mipmap.a1,R.mipmap.a2,R.mipmap.a3,R.mipmap.a4};
        //提供标题
        titles = new String[]{"这是第一张图片","这是第二张图片","这是第三张图片","这是第四张图片"};

        //初始化要展示的4个ImageView
        imageViewList = new ArrayList<>();

        ImageView imageView;
        View view;
        LinearLayout.LayoutParams layoutParams;
        for (int i=0;i<images.length;i++){
            // 初始化要显示的图片对象,添加组件
            imageView =new ImageView(this);
            imageView.setBackgroundResource(images[i]);
            imageViewList.add(imageView);

            // 加小白点, 指示器
            view =new View(this);
            layoutParams = new LinearLayout.LayoutParams(5,5);
            view.setBackgroundResource(R.drawable.view_point);
            if ( i!=0){
                layoutParams.leftMargin =10;
            }

            // 设置默认所有都不可用
            view.setEnabled(false);
            llPointContainer.addView(view,layoutParams);

        }

    }

    private void initadapter(){

        llPointContainer.getChildAt(0).setEnabled(true);
        tvDesc.setText(titles[0]);
        previousSelectedPosition = 0;

        myAdapter =new MyAdapter(imageViewList);
        // 设置适配器
        viewpager.setAdapter(myAdapter);

        // 默认设置到中间的某个位置
        int pos = Integer.MAX_VALUE / 2 - (Integer.MAX_VALUE / 2 % imageViewList.size());
        // 2147483647 / 2 = 1073741823 - (1073741823 % 5)
        viewpager.setCurrentItem(5000000); // 设置到某个位置
    }
}
