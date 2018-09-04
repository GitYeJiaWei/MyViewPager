package com.example.yjw.AsyncTask;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.widget.ImageView;

import com.example.yjw.common.BitmapUtil;
import com.example.yjw.myviewpager.BaseActivity;
import com.example.yjw.myviewpager.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * AsyncTask 异步任务处理复杂操作，每次只能execute一次，经常启用会耗内存
 */

public class AsyncTask extends BaseActivity {
    @BindView(R.id.tv_image)
    ImageView tvImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_async_task);
        ButterKnife.bind(this);

        new MyAsyncTask().execute("12345677");
    }

    class MyAsyncTask extends android.os.AsyncTask<String,Void,Bitmap> {


        //onPreExecute用于异步处理前的操作
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            //此处将progressBar设置为可见.
        }

        //在doInBackground方法中进行异步任务的处理.
        @Override
        protected Bitmap doInBackground(String... params) {
            //获取传进来的参数
            String url = params[0];
            return Create2QR2(url);
        }

        //onPostExecute用于UI的更新.此方法的参数为doInBackground方法返回的值.
        @Override
        protected void onPostExecute(Bitmap bitmap) {
            super.onPostExecute(bitmap);
            if (bitmap != null) {
                tvImage.setImageBitmap(bitmap);
            }
        }
    }

    //生成二维码的方法
    private Bitmap Create2QR2(String urls) {
        String uri = urls;
        int mScreenWidth = 0;
        Bitmap bitmap = null;
        try {
            DisplayMetrics dm = new DisplayMetrics();
            this.getWindowManager().getDefaultDisplay().getMetrics(dm);
            //this，context,一定要加
            mScreenWidth = dm.widthPixels;

            bitmap = BitmapUtil.createQRImage(uri, mScreenWidth,
                    BitmapFactory.decodeResource(getResources(), R.mipmap.baidu));//自己写的方法
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bitmap;
    }


}
