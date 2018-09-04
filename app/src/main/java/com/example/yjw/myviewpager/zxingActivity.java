package com.example.yjw.myviewpager;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.yjw.Zxing.android.CaptureActivity;
import com.example.yjw.common.BaseActivity;
import com.example.yjw.common.BitmapUtil;

public class zxingActivity extends BaseActivity implements View.OnClickListener{
    private Button btn_scanning,btn_make_erweima;
    private TextView tv_scanning_result;
    private EditText image_mess;
    private ImageView result_img;
    private static final int REQUEST_CODE_SCAN = 0x0000;
    private static final String DECODED_CONTENT_KEY = "codedContent";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zxing);

        btn_scanning =findViewById(R.id.btn_scanning);
        tv_scanning_result =findViewById(R.id.tv_scanning_result);
        image_mess =findViewById(R.id.image_mess);
        btn_make_erweima =findViewById(R.id.btn_make_erweima);
        result_img =findViewById(R.id.result_img);

        btn_scanning.setOnClickListener(this);
        btn_make_erweima.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_scanning:
                Intent Zxing =new Intent(this, CaptureActivity.class);
                startActivityForResult(Zxing,REQUEST_CODE_SCAN);
                break;
            case R.id.btn_make_erweima:
                String aimContent=image_mess.getText().toString();
                Create2QR2(aimContent,result_img);
                break;
                default:
        }
    }

    //生成二维码的方法
    private void Create2QR2(String urls,ImageView imageView) {
        String uri = urls;
        int mScreenWidth = 0;
        Bitmap bitmap;
        try {
            DisplayMetrics dm = new DisplayMetrics();
            getWindowManager().getDefaultDisplay().getMetrics(dm);
            mScreenWidth = dm.widthPixels;

            bitmap = BitmapUtil.createQRImage(uri, mScreenWidth,
                    BitmapFactory.decodeResource(getResources(), R.mipmap.me));//自己写的方法

            if (bitmap != null) {
                imageView.setImageBitmap(bitmap);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

        @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // 扫描二维码/条码回传
        if (requestCode == REQUEST_CODE_SCAN && resultCode == RESULT_OK) {
            if (data != null) {
                String content = data.getStringExtra(DECODED_CONTENT_KEY);
                if(content.contains("SITE:"));
                {
                    String string1=content.replace("SITE:","");
                    tv_scanning_result.setText("扫描结果："+string1);
                }
            }
        }

    }
}
