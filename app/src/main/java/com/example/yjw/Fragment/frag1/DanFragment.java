package com.example.yjw.Fragment.frag1;


import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.yjw.myviewpager.R;

/**
 * 获取从activity传来的值
 */
public class DanFragment extends Fragment {
    private TextView show;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
	    View view=inflater.inflate(R.layout.danfragment,null);
        TextView tv_shop=view.findViewById(R.id.tv_shop);
		/*Bundle bundle=getArguments();
		String a=bundle.getString("into");
		tv_shop.setText("789456");
		show=view.findViewById(R.id.show);*/
		return view;
	}

   /* //定义函数给TextView控件赋值
	private void setshow(String str){
	    show.setText(str);
    }*/
}
