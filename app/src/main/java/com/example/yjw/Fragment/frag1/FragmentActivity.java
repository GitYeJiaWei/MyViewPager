package com.example.yjw.Fragment.frag1;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import com.example.yjw.common.BaseActivity;
import com.example.yjw.myviewpager.R;

/**
 * activity 传值到fragment
 */
public class FragmentActivity extends BaseActivity implements View.OnClickListener {

    private FragmentManager manager;
    private FragmentTransaction transaction;
    private RadioButton rb_shop,rb_share,rb_dan,rb_gift;
    private Bundle bundle;
    private String fragbundle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment);

        initview();

        ShopFragment shopFragment=new ShopFragment();
        shopFragment.setArguments(bundle);
        manager= getFragmentManager();
        transaction =manager.beginTransaction();
        transaction.add(R.id.content_layout, shopFragment);
        transaction.commit();
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

        rb_shop = findViewById(R.id.rb_shopshop);
        rb_share = findViewById(R.id.rb_shopshare);
        rb_gift =findViewById(R.id.rb_shopgift);
        rb_dan = findViewById(R.id.rb_shopdan);

        rb_shop.setOnClickListener(this);
        rb_share.setOnClickListener(this);
        rb_gift.setOnClickListener(this);
        rb_dan.setOnClickListener(this);
    }
	/*
	 * 点击radiobutton时触发的事件
	 */

    @Override
    public void onClick(View v) {
        manager =getFragmentManager();
        transaction =manager.beginTransaction();
        switch (v.getId()) {
            case R.id.rb_shopshop:
                ShopFragment shopFragment=new ShopFragment();
                shopFragment.setArguments(bundle);
                transaction.replace(R.id.content_layout, shopFragment);
                break;
            case R.id.rb_shopshare:
                ShareFragment shareFragment=new ShareFragment();
                shareFragment.setArguments(bundle);
                transaction.replace(R.id.content_layout, shareFragment);
                break;
            case R.id.rb_shopgift:
                GiftFragment giftFragment=new GiftFragment();
                giftFragment.setArguments(bundle);
                transaction.replace(R.id.content_layout,giftFragment);
                break;
            case R.id.rb_shopdan:
                DanFragment danFragment=new DanFragment();
                danFragment.setArguments(bundle);
                transaction.replace(R.id.content_layout, danFragment);
                break;
            default:
                break;
        }
        transaction.commitAllowingStateLoss();
    }


    private Fragment currentFragment;
    /**
     * 添加或者显示碎片
     * 每次只显示一个
     *
     * @param transaction
     * @param fragment
     */
    private void addOrShowFragment(FragmentTransaction transaction, Fragment fragment) {
        if (currentFragment == fragment)
            return;
        if (!fragment.isAdded()) { // 如果当前fragment未被添加，则添加到Fragment管理器中
            if(currentFragment == null)
            {
                transaction.add(R.id.content, fragment).commitAllowingStateLoss();
            }
            else
            {
                //transaction.hide(currentFragment).add(R.id.content_layout, fragment).commit();//不重新加载
                transaction.remove(currentFragment).add(R.id.content, fragment).commitAllowingStateLoss();
            }
        } else {
            //transaction.hide(currentFragment).show(fragment).commit();//不重新加载
            transaction.remove(currentFragment).show(fragment).commitAllowingStateLoss();
        }
        currentFragment = fragment;
    }

}
