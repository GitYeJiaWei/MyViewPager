package com.example.yjw.Socket.Mini;

import android.os.Bundle;

import com.example.yjw.myviewpager.BaseActivity;
import com.example.yjw.myviewpager.R;

/**
 * Mina Socket通信
 */
public class MinaActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mina);

        MinaThread minaThread = new MinaThread();
        minaThread.start();
    }

}
