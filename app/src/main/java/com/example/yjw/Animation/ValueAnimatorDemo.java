package com.example.yjw.Animation;

import android.animation.ValueAnimator;
import android.os.Bundle;
import android.util.Log;

import com.example.yjw.myviewpager.BaseActivity;
import com.example.yjw.myviewpager.R;

/**
 * 属性动画(property animation)
 *
 * 逐帧动画(frame-by-frame animation)和补间动画(tweened animation)。
 * 逐帧动画的工作原理很简单，其实就是将一个完整的动画拆分成一张张单独的图片，
 * 然后再将它们连贯起来进行播放，类似于动画片的工作原理。
 * 补间动画则是可以对View进行一系列的动画操作，包括淡入淡出、缩放、平移、旋转四种
 */

/**
 * setStartDelay()方法来设置动画延迟播放的时间，
 * 调用setRepeatCount()和setRepeatMode()方法来设置动画循环播放的次数以及循环播放的模式，
 * 循环模式包括RESTART和REVERSE两种，分别表示重新播放和倒序播放的意思
 */
public class ValueAnimatorDemo extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_value_animator);

        //initView();
        initView2();
        //initView1();
    }

    private void initView(){
        //将一个值从0平滑过渡到1，时长300毫秒
        ValueAnimator anim = ValueAnimator.ofFloat(0f,1f);
        anim.setDuration(300);
        anim.start();
    }

    private void initView1(){
        //将一个整数值从0平滑地过渡到100
        ValueAnimator anim = ValueAnimator.ofInt(0,100);
        anim.setDuration(300);
        anim.start();
    }

    private void initView2(){
        //动画监听器
        final ValueAnimator animator = ValueAnimator.ofFloat(0f,1f,5f,3f);
        animator.setDuration(300);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float currentValue = (float) animator.getAnimatedValue();
                Log.d("TAG", "cuurent value is " + currentValue);
            }
        });
        animator.start();
    }
}
