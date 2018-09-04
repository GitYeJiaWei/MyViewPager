package com.example.yjw.myviewpager;

import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.animation.TranslateAnimation;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.yjw.KillProsser.AutoCleanService;
import com.example.yjw.common.BaseActivity;
import com.example.yjw.permission.PermissionsActivity;
import com.example.yjw.permission.PermissionsChecker;

import cn.jpush.android.api.JPushInterface;


public class MainActivity extends BaseActivity implements RadioGroup.OnCheckedChangeListener,
    ViewPager.OnPageChangeListener{

    //UI Objects
    private TextView txt_topar;
    private RadioGroup rg_tab_bar;
    private RadioButton rb_channel;
    private RadioButton rb_message;
    private RadioButton rb_better;
    private RadioButton rb_setting;
    private ViewPager vpager;

    private MyFragmentPagerAdapter mAdapter;
    private TranslateAnimation animation;
    //几个代表页面的常量
    public static final int PAGE_ONE = 0;
    public static final int PAGE_TWO = 1;
    public static final int PAGE_THREE = 2;
    public static final int PAGE_FOUR = 3;

    private static final int REQUEST_CODE = 0; // 请求码
    private PermissionsChecker mPermissionsChecker; // 权限检测器
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        JPushInterface.setDebugMode(true);
        JPushInterface.init(this);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar !=null){
            actionBar.hide();;
        }

        mAdapter = new MyFragmentPagerAdapter(getSupportFragmentManager());
        bindViews();
        rb_channel.setSelected(true);

        //动态添加权限
        mPermissionsChecker = new PermissionsChecker(this);
    }

    @Override
    protected void onNewIntent(Intent intent) {
       if (intent !=null){
           String tab=intent.getStringExtra("TAB");
       }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // 拒绝时, 关闭页面, 缺少主要权限, 无法运行
        if (requestCode == REQUEST_CODE && resultCode == PermissionsActivity.PERMISSIONS_DENIED) {
            finish();
            System.exit(0);
        }
    }
    @Override
    protected void onResume() {
        super.onResume();
        // 缺少权限时, 进入权限配置页面
        if (mPermissionsChecker.lacksPermissions()) {
            startPermissionsActivity();
        }
    }
    private void startPermissionsActivity() {
        PermissionsActivity.startActivityForResult(this, REQUEST_CODE);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.add_item:
                Toast.makeText(this,"add",Toast.LENGTH_LONG).show();
                break;
            case R.id.remove_item:
                Toast.makeText(this,"remove",Toast.LENGTH_LONG).show();
                break;
                default:
        }
        return true;
    }

    private void bindViews() {
        //启动Service
        Intent intentOne = new Intent(this, AutoCleanService.class);
        startService(intentOne);

        txt_topar = (TextView) findViewById(R.id.txt_topbar);
        rg_tab_bar = (RadioGroup) findViewById(R.id.rg_tab_bar);
        rb_channel = (RadioButton) findViewById(R.id.rb_channel);
        rb_message = (RadioButton) findViewById(R.id.rb_message);
        rb_better = (RadioButton) findViewById(R.id.rb_better);
        rb_setting = (RadioButton) findViewById(R.id.rb_setting);
        rg_tab_bar.setOnCheckedChangeListener(this);

        vpager =(ViewPager) findViewById(R.id.vpager);
        vpager.setAdapter(mAdapter);
        vpager.setCurrentItem(0);
        vpager.addOnPageChangeListener(this);
    }

    //重写ViewPager页面切换的处理方法
    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {

    }

    @Override
    public void onPageScrollStateChanged(int state) {
        //state的状态有三种，0表示什么都没做，1正在滑动，2滑动完毕
        if (state==2){
            switch (vpager.getCurrentItem()){
                case PAGE_ONE:
                    reselected();
                    rb_channel.setSelected(true);
                    break;
                case PAGE_TWO:
                    reselected();
                    rb_message.setSelected(true);
                    break;
                case PAGE_THREE:
                    reselected();
                    rb_better.setSelected(true);
                    break;
                case PAGE_FOUR:
                    reselected();
                    rb_setting.setSelected(true);
                    break;
            }
        }
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId){
            case R.id.rb_channel:
                vpager.setCurrentItem(PAGE_ONE);
                break;
            case R.id.rb_message:
                vpager.setCurrentItem(PAGE_TWO);
                break;
            case R.id.rb_better:
                vpager.setCurrentItem(PAGE_THREE);
                break;
            case R.id.rb_setting:
                vpager.setCurrentItem(PAGE_FOUR);
                break;
        }
    }

    private void reselected(){
        rb_setting.setSelected(false);
        rb_message.setSelected(false);
        rb_better.setSelected(false);
        rb_channel.setSelected(false);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //启动Service
        Intent intentOne1 = new Intent(this, AutoCleanService.class);
        stopService(intentOne1);
        Log.d("hehe", "onDestroy: ");
    }
}
