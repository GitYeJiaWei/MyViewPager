package com.example.yjw.Butterknife;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.yjw.common.BaseActivity;
import com.example.yjw.myviewpager.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class Butterknife extends BaseActivity {

    @BindView(R.id.play)
    Button play;
    @BindView(R.id.pause)
    Button pause;
    @BindView(R.id.stop)
    Button stop;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_butterknife);
        ButterKnife.bind(this);


    }

    @OnClick({R.id.play, R.id.pause, R.id.stop})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.play:
                break;
            case R.id.pause:
                break;
            case R.id.stop:
                break;
        }
    }
}
