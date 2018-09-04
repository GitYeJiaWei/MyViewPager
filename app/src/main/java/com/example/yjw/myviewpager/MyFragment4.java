package com.example.yjw.myviewpager;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by YJW on 2018/1/2.
 */

public class MyFragment4 extends Fragment {

    public MyFragment4(){

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view =inflater.inflate(R.layout.fg_content,container,false);
        TextView textView = view.findViewById(R.id.txt_content);
        textView.setText("第四个Fragment");
        return  view;
    }
}
