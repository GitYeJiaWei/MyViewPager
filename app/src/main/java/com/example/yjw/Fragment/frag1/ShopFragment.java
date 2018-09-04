package com.example.yjw.Fragment.frag1;


import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.yjw.myviewpager.R;

public class ShopFragment extends Fragment {
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View view=inflater.inflate(R.layout.shopfragment,null);
		TextView tv_shop=view.findViewById(R.id.tv_shop3);
		Bundle bundle=getArguments();
		String a=bundle.getString("into");
		tv_shop.setText(a);
		return view;
	}
}
