package com.example.yjw.Clothestore;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RadioButton;

import com.example.yjw.Fragment.frag1.DanFragment;
import com.example.yjw.Fragment.frag1.GiftFragment;
import com.example.yjw.Fragment.frag1.ShareFragment;
import com.example.yjw.Fragment.frag1.ShopFragment;
import com.example.yjw.myviewpager.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ClotheStore extends AppCompatActivity{

    @BindView(R.id.title_edit)
    Button titleEdit;
    @BindView(R.id.choth_content)
    LinearLayout chothContent;
    private FragmentManager manager;
    private FragmentTransaction transaction;
    private Bundle bundle;
    private String fragbundle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_clothe_store);
        ButterKnife.bind(this);

        initview();

        ClotheScanfragment clotheScanfragment=new ClotheScanfragment();
        clotheScanfragment.setArguments(bundle);
        manager= getFragmentManager();
        transaction =manager.beginTransaction();
        transaction.add(R.id.choth_content, clotheScanfragment);
        transaction.commit();
    }

    @OnClick(R.id.title_edit)
    public void onViewClicked() {

    }

    /*
     * 初始化view的视图
     */

    private void initview() {
        //创建bundle对象  将需要传递的数据存储到bundle中
        //然后调用fragment的setAraguments()方法传递bundle
        fragbundle =getIntent().getStringExtra("123");
        bundle=new Bundle();
        bundle.putString("into",fragbundle);

        titleEdit.setText("返回购物区");

    }
}
