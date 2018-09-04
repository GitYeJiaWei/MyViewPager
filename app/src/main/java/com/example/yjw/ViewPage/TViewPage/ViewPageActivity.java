package com.example.yjw.ViewPage.TViewPage;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.yjw.common.BaseActivity;
import com.example.yjw.myviewpager.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * ViewPage 广告图
 */
public class ViewPageActivity extends BaseActivity {

    @BindView(R.id.llDotTitle)
    LinearLayout llDotTitle;//广告图标题点
    @BindView(R.id.vp)
    ViewPager vp;
    @BindView(R.id.title)
    TextView title;
    private int oldPosition = 0;
    private AdViewPagerAdapter adViewPagerAdapter=null;
    private boolean runable;
    private List<ImageView> imageViewList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_page);
        ButterKnife.bind(this);

        initview();


        new Thread(new Runnable() {
            @Override
            public void run() {
                runable =true;
                while (runable){
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            vp.setCurrentItem(vp.getCurrentItem()+1);
                        }
                    });

                }
            }
        }).start();


    }

    private void initview() {
        List<admodel> list = new ArrayList<>();
        admodel ad1 = new admodel();
        ad1.TempImageId = R.mipmap.a1;
        ad1.title = "第一图";
        list.add(ad1);

        admodel ad2 = new admodel();
        ad2.TempImageId = R.mipmap.a2;
        ad2.title = "第二图";
        list.add(ad2);

        admodel ad3 = new admodel();
        ad3.TempImageId = R.mipmap.a3;
        ad3.title = "第三图";
        list.add(ad3);

        admodel ad4 = new admodel();
        ad4.TempImageId = R.mipmap.a4;
        ad4.title = "第四图";
        list.add(ad4);

        appview(list);

    }



    private void appview(List<admodel> lists) {
        final String[] titles = new String[4];
        final List<View> viewList = new ArrayList<>();
        llDotTitle.removeAllViews();
        imageViewList = new ArrayList<>();
        try {
            for (int h = 0; h < lists.size(); h++) {
                admodel admodel = lists.get(h);
                //创建点,利用addView()方法动态添加组件
                View view = new View(this);
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(10, 10);
                params.setMargins(4, 0, 4, 0);//左，上，右，下
                view.setLayoutParams(params);
                view.setBackgroundResource(R.drawable.dot_normal);
                viewList.add(view);
                llDotTitle.addView(view);
                //创建标题
                titles[h] = admodel.title;
                //创建image
                ImageView imageView = new ImageView(this);//动态创建图
                imageView.setScaleType(ImageView.ScaleType.FIT_XY);//使用 FILL 方式缩放图像，以适应视图边界
                imageView.setImageDrawable(this.getResources().getDrawable(admodel.TempImageId));
                imageViewList.add(imageView);

            }
        } catch (Exception e) {
        }

        //初始化配置
        title.setText(titles[0]);
        oldPosition = 0;
        viewList.get(0).setBackgroundResource(R.drawable.dot_focused);


        if (adViewPagerAdapter != null) {
            adViewPagerAdapter.imageViews.clear();
            adViewPagerAdapter = null;
        }

        vp.setCurrentItem(500000);
        adViewPagerAdapter = new AdViewPagerAdapter();
        adViewPagerAdapter.imageViews = imageViewList;
        vp.setAdapter(adViewPagerAdapter);
        vp.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                int newPosition = position % imageViewList.size();
                title.setText(titles[newPosition]);
                viewList.get(newPosition).setBackgroundResource(R.drawable.dot_focused);
                viewList.get(oldPosition).setBackgroundResource(R.drawable.dot_normal);
                oldPosition = newPosition;
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });


    }
}
