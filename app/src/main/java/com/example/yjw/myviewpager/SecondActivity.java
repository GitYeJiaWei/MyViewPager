package com.example.yjw.myviewpager;

import android.Manifest;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.yjw.Animation.ObjectAnimatorDemo;
import com.example.yjw.Animation.ValueAnimatorDemo;
import com.example.yjw.BaiduLBS.Baidu_MainActivity;
import com.example.yjw.BaseListView.MyListView;
import com.example.yjw.Canvas.CanvasActivity;
import com.example.yjw.CityList.Select_City_Activity;
import com.example.yjw.GreenDAO.GreenDaoActivity;
import com.example.yjw.LeakCanary.LeakCanaryActivity;
import com.example.yjw.MVP.MvpActivity;
import com.example.yjw.NFC.NFCActivity;
import com.example.yjw.NFC.UUIDActivity;
import com.example.yjw.StaggeredGridLagout.StaggeredGridLayoutActivity;
import com.example.yjw.camera.CameraActivity;
import com.example.yjw.common.ActivityCollecter;
import com.example.yjw.common.BaseActivity;
import com.example.yjw.contentResolver.ContentActivity;
import com.example.yjw.contentResolver.ContentResolverActivity;
import com.example.yjw.notification.NotificationActivity;
import com.example.yjw.photoupload.PictureUpload;
import com.example.yjw.photoupload.photoupload2.PictureUpload2;
import com.example.yjw.recyclerview.RecyclerViewActivity;
import com.example.yjw.save.FileSaveActivity;
import com.example.yjw.save.MyDatabaseHelper;
import com.example.yjw.save.SharePreferencesActivity;

public class SecondActivity extends BaseActivity implements View.OnClickListener {

    private MyDatabaseHelper dbHelper;

    public static void actionStart(Context context, String data1, String data2) {
        Intent intent = new Intent(context, SecondActivity.class);
        intent.putExtra("data1", data1);
        intent.putExtra("data2", data2);
        context.startActivity(intent);
        Log.d("data1", data1);
        Log.d("data2", data2);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        dbHelper = new MyDatabaseHelper(this, "BookStore.db", null, 1);

        Button btn_ObjectAnimator = findViewById(R.id.btn_ObjectAnimator);
        btn_ObjectAnimator.setOnClickListener(this);

        Button btn_ValueAnimator = findViewById(R.id.btn_ValueAnimator);
        btn_ValueAnimator.setOnClickListener(this);

        Button btn_Canvas = findViewById(R.id.btn_Canvas);
        btn_Canvas.setOnClickListener(this);

        Button btn_MvpActivity = findViewById(R.id.btn_MvpActivity);
        btn_MvpActivity.setOnClickListener(this);

        Button btn_LeakCanary = findViewById(R.id.btn_LeakCanary);
        btn_LeakCanary.setOnClickListener(this);

        Button btn_GreenDAO = findViewById(R.id.btn_GreenDAO);
        btn_GreenDAO.setOnClickListener(this);

        Button btn_PictureUpLoad = findViewById(R.id.btn_PictureUpLoad);
        btn_PictureUpLoad.setOnClickListener(this);

        Button btn_PictureUpLoad2 = findViewById(R.id.btn_PictureUpLoad2);
        btn_PictureUpLoad2.setOnClickListener(this);

        Button btn_NFC = findViewById(R.id.btn_NFC);
        btn_NFC.setOnClickListener(this);

        Button btn_NFCA = findViewById(R.id.btn_NFCA);
        btn_NFCA.setOnClickListener(this);

        Button button = findViewById(R.id.btn_http);
        button.setOnClickListener(this);

        Button button1 = findViewById(R.id.btn_dialog);
        button1.setOnClickListener(this);

        Button button3 = findViewById(R.id.btn_list);
        button3.setOnClickListener(this);

        Button button6 = findViewById(R.id.btn_recycler);
        button6.setOnClickListener(this);

        Button button7 = findViewById(R.id.btn_staggered);
        button7.setOnClickListener(this);

        Button button8 = findViewById(R.id.btn_save);
        button8.setOnClickListener(this);

        Button button9 = findViewById(R.id.btn_sharepreference);
        button9.setOnClickListener(this);

        Button button10 = findViewById(R.id.btn_SQL);
        button10.setOnClickListener(this);

        Button button11 =findViewById(R.id.btn_content);
        button11.setOnClickListener(this);

        Button button12 =findViewById(R.id.btn_content1);
        button12.setOnClickListener(this);

        Button button2 = findViewById(R.id.btn_exit);
        button2.setOnClickListener(this);

        Button btn_notification = findViewById(R.id.btn_notification);
        btn_notification.setOnClickListener(this);

        Button btn_camera = findViewById(R.id.btn_camera);
        btn_camera.setOnClickListener(this);

        Button btn_baiduLBS =findViewById(R.id.btn_baiduLBS);
        btn_baiduLBS.setOnClickListener(this);

        Button btn_city =findViewById(R.id.btn_city);
        btn_city.setOnClickListener(this);

        Intent intent = getIntent();
        String data1 = intent.getStringExtra("data1");
        String data2 = intent.getStringExtra("data2");
        Log.d("data1+data2", "data1+data2" + data1 + data2);
    }

    private void call(){
        try {
            //Intent intent =new Intent(Intent.ACTION_VIEW);
            //Intent intent =new Intent(Intent.ACTION_DIAL);
            //Intent.ACTION_VIEW和Intent.ACTION_DIAL都是打开拨打界面，
            //不需要权限，而ACTION_CALL是直接拨打电话需要权限
            Intent intent = new Intent(Intent.ACTION_CALL);
            intent.setData(Uri.parse("tel:17759053913"));
            startActivity(intent);
        }catch (SecurityException e){
            e.printStackTrace();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode){
            case 1:
                if (grantResults.length >0 && grantResults[0]==PackageManager.PERMISSION_GRANTED){
                    call();
                }else{
                    Toast.makeText(this,"YOU denied the permission",Toast.LENGTH_LONG).show();
                }
                break;
                default:
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_ObjectAnimator:
                Intent btn_ObjectAnimator =new Intent(SecondActivity.this,ObjectAnimatorDemo.class);
                startActivity(btn_ObjectAnimator);
                break;
            case R.id.btn_ValueAnimator:
                Intent btn_ValueAnimator =new Intent(SecondActivity.this,ValueAnimatorDemo.class);
                startActivity(btn_ValueAnimator);
                break;
            case R.id.btn_Canvas:
                Intent btn_Canvas =new Intent(SecondActivity.this,CanvasActivity.class);
                startActivity(btn_Canvas);
                break;
            case R.id.btn_MvpActivity:
                Intent btn_MvpActivity =new Intent(SecondActivity.this,MvpActivity.class);
                startActivity(btn_MvpActivity);
                break;
            case R.id.btn_LeakCanary:
                Intent btn_LeakCanary =new Intent(SecondActivity.this,LeakCanaryActivity.class);
                startActivity(btn_LeakCanary);
                break;
            case R.id.btn_GreenDAO:
                Intent btn_GreenDAO =new Intent(SecondActivity.this,GreenDaoActivity.class);
                startActivity(btn_GreenDAO);
                break;
            case R.id.btn_PictureUpLoad2:
                Intent btn_PictureUpLoad2 =new Intent(SecondActivity.this,PictureUpload2.class);
                startActivity(btn_PictureUpLoad2);
                break;
            case R.id.btn_PictureUpLoad:
                Intent btn_PictureUpLoad =new Intent(SecondActivity.this,PictureUpload.class);
                startActivity(btn_PictureUpLoad);
                break;
            case R.id.btn_NFCA:
                Intent btn_NFCA =new Intent(SecondActivity.this,UUIDActivity.class);
                startActivity(btn_NFCA);
                break;
            case R.id.btn_NFC:
                Intent btn_NFC =new Intent(SecondActivity.this,NFCActivity.class);
                startActivity(btn_NFC);
                break;
            case R.id.btn_http:
                //动态获取权限
                if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(SecondActivity.this,new
                    String[]{ Manifest.permission.CALL_PHONE,Manifest.permission.READ_PHONE_STATE},1);
                }else{
                    call();
                }
                break;
            case R.id.btn_dialog:
                Intent intent1 =new Intent(SecondActivity.this,DialogActivity.class);
                startActivity(intent1);
                break;
            case R.id.btn_exit:
                ActivityCollecter.finishAll();
                break;
            case R.id.btn_list:
                //Intent intent2 =new Intent(SecondActivity.this, ListViewActivity.class);
                Intent intent2 =new Intent(SecondActivity.this, MyListView.class);
                startActivityForResult(intent2,2);
                break;
            case R.id.btn_recycler:
                Intent intent3 =new Intent(SecondActivity.this, RecyclerViewActivity.class);
                startActivityForResult(intent3,3);
                break;
            case R.id.btn_staggered:
                Intent intent4 =new Intent(SecondActivity.this, StaggeredGridLayoutActivity.class);
                startActivityForResult(intent4,4);
                break;
            case R.id.btn_save:
                Intent intent5 =new Intent(SecondActivity.this,FileSaveActivity.class);
                startActivityForResult(intent5,5);
                break;
            case R.id.btn_sharepreference:
                Intent intent6 =new Intent(SecondActivity.this,SharePreferencesActivity.class);
                startActivityForResult(intent6,5);
                break;
            case R.id.btn_SQL:
                dbHelper.getWritableDatabase();
                break;
            case R.id.btn_content:
                Intent intent7 =new Intent(SecondActivity.this,ContentResolverActivity.class);
                startActivityForResult(intent7,5);
                break;
            case R.id.btn_content1:
                Intent intent8 =new Intent(SecondActivity.this,ContentActivity.class);
                startActivityForResult(intent8,5);
                break;
            case R.id.btn_notification:
                Intent intent9 =new Intent(SecondActivity.this,NotificationActivity.class);
                startActivityForResult(intent9,5);
                break;
            case R.id.btn_camera:
                    Intent intent10 =new Intent(SecondActivity.this,CameraActivity.class);
                startActivityForResult(intent10,5);
                break;
            case R.id.btn_baiduLBS:
                Intent intent11 =new Intent(SecondActivity.this,Baidu_MainActivity.class);
                startActivityForResult(intent11,5);
                break;

            case R.id.btn_city:
                Intent intent14 =new Intent(SecondActivity.this,Select_City_Activity.class);
                startActivityForResult(intent14,5);
                break;

            default:
        }
    }
}
