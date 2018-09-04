package com.example.yjw.Fragment.frag2;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.yjw.myviewpager.R;

/**
 * Created by YJW on 2018/4/24.
 */

public class ResureFragment extends Fragment {
    private EditText et_content;
    private Button btn;
    private Mylistener mylistener;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //创建接口的子类对象
        //获取当前fragment所属的activity（Fragment1Activity）
        //activity(Fragment1Activity) 实现了MyListener接口是其子类
        mylistener=(Mylistener) getActivity();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.layout_resure,null);
        et_content =view.findViewById(R.id.et_content);
        btn =view.findViewById(R.id.btn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //接口回调的形式回传数据
                mylistener.sendmessage(et_content.getText().toString());
            }
        });
        return view;
    }

    /**
     * 定义接口 接口中定义回传数据的函数
     */
    public interface Mylistener{
        public abstract void sendmessage(String str);
    }
}
