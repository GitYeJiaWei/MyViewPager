package com.example.yjw.ViewPage.WViewPage;

import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

/**
 * @author YJW
 * @create 2018/7/6
 * @Describe
 */
public class MyAdapter extends PagerAdapter{
    private List<ImageView> imageViewList =new ArrayList<>();

    public MyAdapter(List<ImageView> list){
        imageViewList =list;
    }

    @Override
    public int getCount() {
        return Integer.MAX_VALUE;
    }

    // 3. 指定复用的判断逻辑, 固定写法
    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        // 当划到新的条目, 又返回来, view是否可以被复用.
        // 返回判断规则
        return view==object;
    }

    // 1. 返回要显示的条目内容, 创建条目
    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        // container: 容器: ViewPager
        // position: 当前要显示条目的位置 0 -> 3

        //newPosition = position % 4,获取的位置一直是0.1.2.3
        int newPosition = position % imageViewList.size();

        ImageView imageView = imageViewList.get(newPosition);
        // a. 把View对象添加到container中
        container.addView(imageView);
        // b. 把View对象返回给框架, 适配器
        return imageView; // 必须重写, 否则报异常
    }

    // 2. 销毁条目
    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        // object 要销毁的对象
        container.removeView((View) object);
    }
}
