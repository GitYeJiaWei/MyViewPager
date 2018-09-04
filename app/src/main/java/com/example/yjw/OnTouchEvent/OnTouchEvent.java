package com.example.yjw.OnTouchEvent;

import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;

import com.example.yjw.common.BaseActivity;
import com.example.yjw.myviewpager.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Button 的事件分发机制
 */
public class OnTouchEvent extends BaseActivity {

    @BindView(R.id.button)
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_on_touch_event);
        ButterKnife.bind(this);

        initbutton();


    }

    private void initbutton() {

       /* button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("TAG", "onClick 点击按钮");
            }
        });*/



        button.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                Log.d("TAG", "onTouch execute, action " + event.getAction());
                switch (event.getAction()){
                    case MotionEvent.ACTION_DOWN:
                        Log.d("TAG", "onClick 触摸按钮");
                        break;
                    case MotionEvent.ACTION_UP:
                        Log.d("TAG", "onClick 松开按钮");
                        break;
                    case MotionEvent.ACTION_MOVE:
                        Log.d("TAG", "onClick 按钮上滑动");
                        break;
                }
                return false;//onTouch返回false才会执行onTouchEvent(event)
            }
        });

    }

    @OnClick(R.id.button)
    public void onViewClicked() {
    Log.d("TAG", "onClick 点击按钮");
    }
}
