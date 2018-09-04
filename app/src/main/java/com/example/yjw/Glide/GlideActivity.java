package com.example.yjw.Glide;

import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.yjw.common.BaseActivity;
import com.example.yjw.myviewpager.R;

import java.io.File;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 图片加载框架Glide
 */
public class GlideActivity extends BaseActivity {

    @BindView(R.id.image_view)
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_glide);
        ButterKnife.bind(this);

        initview();
    }

    public void loadImage(View view) {
        String url1 ="http://p1.pstatp.com/large/166200019850062839d3";                             //GIF图 Glide自动识别图片类型
        //////String url  ="http://cn.bing.com/az/hprichbg/rb/Dongdaemun_ZH-CN10736487148_1920x1080.jpg"; //静态图
        Glide.with(this)                            //创建图片加载实例
                .load(url1)                                  //指定待加载的图片资源
                //.asBitmap()                                 //取GIF图的第一帧
                .asGif()                                    //只能放置GIF图，静态图不能加载
                .placeholder(R.mipmap.loadimage)            //占位图
                .diskCacheStrategy(DiskCacheStrategy.NONE)  //禁用掉Glide的缓存功能
                .error(R.mipmap.error)                      //异常占位图
                .override(200,200)           //指定图片的大小
                .into(imageView);                           //放置图片
    }

    private void initview(){
       /* // 加载本地图片
        File file = new File(getExternalCacheDir() + "/image.jpg");
        Glide.with(this).load(file).into(imageView);

        // 加载应用资源
        int resource = R.drawable.image;
        Glide.with(this).load(resource).into(imageView);

        // 加载二进制流
        byte[] image = getImageBytes();
        Glide.with(this).load(image).into(imageView);

        // 加载Uri对象
        Uri imageUri = getImageUri();
        Glide.with(this).load(imageUri).into(imageView);*/
    }
}
