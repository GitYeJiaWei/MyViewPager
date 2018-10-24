package com.example.yjw.myviewpager;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by YJW on 2018/1/11.
 */

public class TitleLayout extends LinearLayout implements View.OnClickListener{

    public TitleLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.title,this);

        Button button = findViewById(R.id.title_back);
        button.setOnClickListener(this);

        Button button1 = findViewById(R.id.title_edit);
        button1.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.title_back:
                ((Activity)getContext()).finish();
                break;
            case R.id.title_edit:
                Toast.makeText(getContext(),"This is a edit",Toast.LENGTH_LONG).show();
                break;
            default:
                break;
        }
    }
}
