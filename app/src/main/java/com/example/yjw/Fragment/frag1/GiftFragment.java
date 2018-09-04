package com.example.yjw.Fragment.frag1;


import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.yjw.myviewpager.R;

/**
 * 同一个界面两个不同的fragment传值
 */
public class GiftFragment extends Fragment {
    private EditText et_content;
    private Button btn_send;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View view=inflater.inflate(R.layout.giftfragment,null);
		TextView tv_shop=view.findViewById(R.id.tv_shop1);
		Bundle bundle=getArguments();
		String a=bundle.getString("into");
		tv_shop.setText(a);
        et_content =view.findViewById(R.id.et_content);
        btn_send =view.findViewById(R.id.btn_send);
        btn_send.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                String str =et_content.getText().toString().trim();

				/*1//方式一：可以调用findFragmentById()方法根据id获取fragment的对象 调用fragment中的方法赋值
				RightFragment rightFragment=(RightFragment)getFragmentManager()
						.findFragmentById(R.id.rigrhfragment);
				rightFragment.setTextView(str);*/

				/*2//方式二：先调用getFragmentManger() 获得fragmentmanger对象
				//然后调用findFragmentById()方法获得右侧的fragment
				//再调用getview()获得右侧fragment的view对象  最后调用view的findViewById()获得赋值的控件
				TextView tv=(TextView) getFragmentManager().findFragmentById(R.id.rigrhfragment)
						.getView().findViewById(R.id.tv_show);
				tv.setText(str);*/

				/*3//方式三：先调用getactivity()方法获取所属的activity的对象  然后调用findViewById()获取目标控件
				TextView tv = (TextView) getActivity().findViewById(R.id.tv_show);
				tv.setText(str);*/

            }
        });
		return view;
	}
}
