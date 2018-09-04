package com.example.yjw.MVP.View;

import android.os.Bundle;

import com.example.yjw.MVP.Contract.SimpleContract;
import com.example.yjw.common.BaseActivity;
import com.example.yjw.myviewpager.R;

/**
 * view层，负责activity和fragment相关UI界面的展示、刷新（引用contract接口）
 */
public class SimpleActivity extends BaseActivity implements SimpleContract.View {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simple);
    }

    @Override
    public void errorGetSample(String msg) {

    }
}
