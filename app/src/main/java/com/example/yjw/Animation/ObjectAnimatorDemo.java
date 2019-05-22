package com.example.yjw.Animation;

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
                ObjectAnimator moveIn = ObjectAnimator.ofFloat(textView6,"translationX",-500f,0f);
                ObjectAnimator rotate = ObjectAnimator.ofFloat(textView6, "rotation", 0f, 360f);
                ObjectAnimator fadeInOut = ObjectAnimator.ofFloat(textView6, "alpha", 1f, 0f, 1f);

                break;
        }

    }
}
