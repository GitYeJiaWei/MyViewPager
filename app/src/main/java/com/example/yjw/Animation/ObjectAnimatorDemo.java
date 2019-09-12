package com.example.yjw.Animation;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.yjw.myviewpager.BaseActivity;
import com.example.yjw.myviewpager.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 可以直接对任意对象的任意属性进行动画操作的
 */
public class ObjectAnimatorDemo extends BaseActivity {

    @BindView(R.id.button2)
    Button button2;
    @BindView(R.id.textView6)
    TextView textView6;
    @BindView(R.id.button3)
    Button button3;
    @BindView(R.id.button1)
    Button button1;
    @BindView(R.id.button4)
    Button button4;
    @BindView(R.id.button5)
    Button button5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_object_animator_demo);
        ButterKnife.bind(this);

        initView();
    }

    private void initView() {
        ObjectAnimator moveIn = ObjectAnimator.ofFloat(textView6,"translationX",-500f,0f);
        ObjectAnimator rotate = ObjectAnimator.ofFloat(textView6, "rotation", 0f, 360f);
        ObjectAnimator fadeInOut = ObjectAnimator.ofFloat(textView6, "alpha", 1f, 0f, 1f);
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.play(rotate).with(fadeInOut).after(moveIn);
        animatorSet.setDuration(500);
        animatorSet.start();
        //动画监听
        animatorSet.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
                //onAnimationStart()方法会在动画开始的时候调用
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                //onAnimationEnd()方法会在动画结束的时候调用
            }

            @Override
            public void onAnimationCancel(Animator animation) {
                //onAnimationCancel()方法会在动画被取消的时候调用
            }

            @Override
            public void onAnimationRepeat(Animator animation) {
                //onAnimationRepeat()方法会在动画重复执行的时候调用
            }
        });

        //动画监听的适配器,重写你需要的方法
        animatorSet.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
            }
        });
    }

    @OnClick({R.id.button2, R.id.button3, R.id.button1, R.id.button4,R.id.button5})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.button2:
                //将一个TextView在5秒中内从常规变换成全透明，再从全透明变换成常规
                ObjectAnimator animator = ObjectAnimator.ofFloat(textView6, "alpha", 1f, 0f, 1f);
                animator.setDuration(5000);
                animator.start();
                break;
            case R.id.button3:
                //将一个TextView在5秒中内从常规变换成全透明，再从全透明变换成常规
                ObjectAnimator animator1 = ObjectAnimator.ofFloat(textView6, "rotation", 0f, 360f);
                animator1.setDuration(5000);
                animator1.start();
                break;
            case R.id.button1:
                float curTranslationx = textView6.getTranslationX();//获取当前的坐标
                ObjectAnimator animator2 = ObjectAnimator.ofFloat(textView6, "translationX", curTranslationx, -500f, curTranslationx);
                animator2.setDuration(5000);
                animator2.start();
                break;
            case R.id.button4:
                ObjectAnimator animator3 = ObjectAnimator.ofFloat(textView6, "scaleY", 1f, 3f, 1f);
                animator3.setDuration(3000);
                animator3.start();
                break;
            case R.id.button5:
                /**
                 * after(Animator anim)   将现有动画插入到传入的动画之后执行
                 * after(long delay)   将现有动画延迟指定毫秒后执行
                 * before(Animator anim)   将现有动画插入到传入的动画之前执行
                 * with(Animator anim)   将现有动画和传入的动画同时执行
                 */
                ObjectAnimator moveIn = ObjectAnimator.ofFloat(textView6,"translationX",-500f,0f);
                ObjectAnimator rotate = ObjectAnimator.ofFloat(textView6, "rotation", 0f, 360f);
                ObjectAnimator fadeInOut = ObjectAnimator.ofFloat(textView6, "alpha", 1f, 0f, 1f);
                AnimatorSet animatorSet = new AnimatorSet();
                animatorSet.play(rotate).with(fadeInOut).after(moveIn);
                animatorSet.setDuration(500);
                animatorSet.start();
                break;
        }

    }
}
