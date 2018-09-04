package com.example.yjw.Clothestore;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.yjw.myviewpager.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;


/**
 * @author YJW
 * @create 2018/8/7
 * @Describe
 */
public class ClotheScanfragment extends Fragment {
    @BindView(R.id.tv_jiantou)
    TextView tvJiantou;
    @BindView(R.id.text)
    TextView text;
    Unbinder unbinder;
    @BindView(R.id.tv_open)
    Button tvOpen;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_clother, container, false);
        unbinder = ButterKnife.bind(this, view);

        tvOpen.setText("开门"+"\n"+"OPEN THE DOOR");
        tvJiantou.setText("未购物请点击" + "\n" + "上方开门按钮");
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
