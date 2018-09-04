package com.example.yjw.ViewPage;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import java.util.List;

/**
 * @author YJW
 * @create 2018/6/28
 * @Describe
 */
public class PresiterAdapter extends PagerAdapter {
    private Context mcomtext;
    private List<View> mviewlist;

    public PresiterAdapter(Context context,List<View> viewList){
        this.mcomtext = context;
        this.mviewlist =viewList;
    }

    @Override
    public int getCount() {
        return mviewlist.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @Override
    public void destroyItem(ViewGroup container, int position,
                            Object object) {
        ((ViewPager)container).removeView(mviewlist.get(position));
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        position %=mviewlist.size();
        if (position <0){
            position = mviewlist.size()+1;
        }
        View view =mviewlist.get(position);
        ViewParent vp = view.getParent();
        if (vp !=null){
            ViewGroup parent =(ViewGroup) vp;
            parent.removeView(view);
        }

        container.addView(view);

        return view;
    }
}
