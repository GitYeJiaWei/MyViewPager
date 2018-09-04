package com.example.yjw.Fragment.frag2;

import android.os.Bundle;
import android.text.TextUtils;
import android.widget.TextView;

import com.example.yjw.common.BaseActivity;
import com.example.yjw.myviewpager.R;

/**
 * fragment向所在的activity传值
 * 步骤：
 * 1.Fragment中定义传值的回调接口，在生命周期的aoAttach()方法中获取接口的实现
 * 2.fragment需要传值的位置调用接口回调方法传值
 * 3.Activity实现回调接口重写回调方法获取传递的值
 */
public class Fragment1Activity extends BaseActivity implements ResureFragment.Mylistener{
    private TextView tv_show;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment1);
        tv_show =findViewById(R.id.tv_show);
    }

    /**
     * @param str
     * 回传数据进行展示
     */
    @Override
    public void sendmessage(String str) {
        if (!TextUtils.isEmpty(str)){
            tv_show.setText(str);
        }
    }
}
