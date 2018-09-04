package com.example.yjw.Retrofit;

import android.os.Bundle;
import android.view.View;
import com.example.yjw.common.BaseActivity;
import com.example.yjw.common.DialogUtils;
import com.example.yjw.myviewpager.R;

import java.util.HashMap;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;
import rx.Observable;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class DataServiceActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_service);

        //initview();
        //initview1();
        initview2();
        initview3();
    }

    /**
     * 结合Rxjava 和Retrofit
     * https://www.zftong.cn/tools/android.ashx?committype=login&sjh=18859863913&pwd=321
     */
    private void initview3(){
        HashMap<String,String> map = new HashMap<>();
        map.put("committype","login");
        map.put("sjh","18859863913");
        map.put("pwd","321");

        DialogUtils.showCustomerConfirmDialog(this, "1234", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String i="adjflefahesfl";
                DialogUtils.ShowToast(DataServiceActivity.this,i.indexOf("f")+"");
                //显示3，字符串i中“f”的位置，从0开始
            }
        }, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String i="adjflefahesfl";
                DialogUtils.ShowToast(DataServiceActivity.this,i.substring(i.indexOf("f"),i.indexOf("s")));
                //显示的是flefahe，substring是[ )的意思

            }
        });

        Retrofit retrofit =new Retrofit.Builder()
                //设置基础的URL
                .baseUrl("http://www.zftong.cn")
                //设置内容格式,这种对应的数据返回值是Gson类型，需要导包
                .addConverterFactory(GsonConverterFactory.create())
                //设置支持RxJava，应用observable观察者，需要导包
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .client(new OkHttpClient())
                .build();

        APIservice apIservice =retrofit.create(APIservice.class);

        Observable<QQDataT> qqDataCall =apIservice.QQ_DATA_CALL3("login","18859863913","321");
        qqDataCall.subscribeOn(Schedulers.io())//请求数据的事件发生在io线程
                .observeOn(AndroidSchedulers.mainThread())//请求完成后在主线程更新UI
                .subscribe(new Observer<QQDataT>() {//订阅
                               @Override
                               public void onCompleted() {
                                   //所有事件都完成，可以做些操作。。。
                               }
                               @Override
                               public void onError(Throwable e) {
                                   e.printStackTrace(); //请求过程中发生错误
                               }
                               @Override
                               public void onNext(QQDataT book) {//这里的book就是我们请求接口返回的实体类
                                   DialogUtils.ShowToast(DataServiceActivity.this,book.getDwdz());
                               }
                           }

                    );
    }

    /**
     * https://www.zftong.cn/tools/android.ashx?committype=login&sjh=18859863913&pwd=321
     */
    private void initview2(){
        //要传的值
        HashMap<String,String> map = new HashMap<>();
        map.put("committype","login");
        map.put("sjh","18859863913");
        map.put("pwd","321");

        Retrofit retrofit =new Retrofit.Builder()
                .baseUrl("http://www.zftong.cn")
                //设置内容格式,这种对应的数据返回值是Gson类型
                .addConverterFactory(GsonConverterFactory.create())
                .client(new OkHttpClient())
                .build();

        //参数定义为null则运用工具类中的方法，如果new 一个新的方法，则重写该方法
        DialogUtils.showCustomerConfirmDialog(DataServiceActivity.this,"45679",null,null);

        APIservice apIservice =retrofit.create(APIservice.class);

        Call<QQDataT> qqDataCall =apIservice.QQ_DATA_CALL2(map);
        qqDataCall.enqueue(new Callback<QQDataT>() {
            @Override
            public void onResponse(Call<QQDataT> call, Response<QQDataT> response) {
                DialogUtils.ShowToast(DataServiceActivity.this,response.body().getXm());
            }

            @Override
            public void onFailure(Call<QQDataT> call, Throwable t) {
                DialogUtils.ShowToast(DataServiceActivity.this,t.getMessage().toString());
            }
        });
    }

    /**
     *  https://www.zftong.cn/tools/Register.ashx?action=get&sjh=18859863913
     */
    private void initview1(){
        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl("https://www.zftong.cn")
                .addConverterFactory(GsonConverterFactory.create())
                .client(new OkHttpClient())
                .build();

        APIservice apIservice=retrofit.create(APIservice.class);
        Call<QQData> qqDataCall =apIservice.QQ_DATA_CALL("get","18859863913");

        qqDataCall.enqueue(new Callback<QQData>() {
            @Override
            public void onResponse(Call<QQData> call, Response<QQData> response) {
                DialogUtils.ShowToast(DataServiceActivity.this,response.body().getSjh());
            }

            @Override
            public void onFailure(Call<QQData> call, Throwable t) {
                DialogUtils.ShowToast(DataServiceActivity.this,t.getMessage().toString());
            }
        });
    }

    /**
     * 指定get请求方式 http://www.baidu.com
     */
    private void initview(){
        //创建Retrofit对象
        Retrofit retrofit = new Retrofit.Builder()
                //指定baseurl，这里有坑，最后后缀出带着“/”
                .baseUrl("http://www.baidu.com/")
                //设置内容格式,这种对应的数据返回值是String类型
                .addConverterFactory(ScalarsConverterFactory.create())
                //定义client类型
                .client(new OkHttpClient())
                //创建
                .build();


        //通过retrofit和定义的有网络访问方法的接口关联
        APIservice apIservice=retrofit.create(APIservice.class);
        //在这里又重新设定了一下baidu的地址，是因为Retrofit要求传入具体，如果是决定路径的话，路径会将baseUrl覆盖掉
        Call<String> baidu = apIservice.Baidu("http://wwww.baidu.com");
        //执行异步请求
        baidu.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                DialogUtils.ShowToast(DataServiceActivity.this,response.body());
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {

            }
        });

    }
}
