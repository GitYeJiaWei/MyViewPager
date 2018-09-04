package com.example.yjw.MaterialDesign;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.DrawerLayout;
import android.view.View;
import android.widget.Toast;

import com.example.yjw.common.BaseActivity;
import com.example.yjw.myviewpager.R;

public class DrawerLayoutActivity extends BaseActivity {
    private DrawerLayout mDrawerLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drawer_layout);

        mDrawerLayout = findViewById(R.id.btn_Drawerlayout);

        FloatingActionButton fab=findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar.make(v,"123456",Snackbar.LENGTH_LONG)
                        .setAction("不是", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Toast.makeText(DrawerLayoutActivity.this,"你就是大傻逼",Toast.LENGTH_SHORT).show();
                            }
                        }).show();
            }
        });

    }
}
