package com.example.yjw.JsonObject;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.yjw.Exception.BaseException;
import com.example.yjw.Exception.RxErrorHandler;
import com.example.yjw.OkHttp3Utils.JsonUtil;
import com.example.yjw.common.BaseActivity;
import com.example.yjw.common.CustomProgressDialog;
import com.example.yjw.common.DialogUtils;
import com.example.yjw.myviewpager.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;


public class JsonActivity extends BaseActivity {

    @BindView(R.id.text_invid)
    EditText textInvid;
    @BindView(R.id.btn_invid)
    Button btnInvid;
    @BindView(R.id.btn_login)
    Button btnLogin;
    @BindView(R.id.btn_get)
    Button btnGet;
    @BindView(R.id.ip_tv)
    Button ipTv;
    private ConcurrentHashMap<String, Skinfin> hashMap = new ConcurrentHashMap<>();
    private String url=ApiService.BaseUrl;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_json);
        ButterKnife.bind(this);
    }

    private void initview() {
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("invoiceId", textInvid.getText().toString().trim());

        Retrofit retrofit = new Retrofit.Builder()
                //设置基础的URL
                .baseUrl(url)
                //设置内容格式,这种对应的数据返回值是Gson类型，需要导包
                .addConverterFactory(GsonConverterFactory.create())
                //设置支持RxJava，应用observable观察者，需要导包
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .client(new OkHttpClient())
                .build();

        ApiService apIservice = retrofit.create(ApiService.class);

        Observable<BaseBean<List<Park>>> qqDataCall = apIservice.GetMessage(hashMap);

        qqDataCall.subscribeOn(Schedulers.io())//请求数据的事件发生在io线程
                .observeOn(AndroidSchedulers.mainThread())//请求完成后在主线程更新UI
                .subscribe(new Observer<BaseBean<List<Park>>>() {//订阅
                               @Override
                               public void onCompleted() {
                                   //所有事件都完成，可以做些操作。。。
                               }

                               @Override
                               public void onError(Throwable e) {
                                   e.printStackTrace(); //请求过程中发生错误

                               }

                               @Override
                               public void onNext(BaseBean<List<Park>> listBaseBean) {//这里的book就是我们请求接口返回的实体类
                                   if (listBaseBean.getResultObj() == null || listBaseBean.getResultObj().equals("")) {
                                       DialogUtils.ShowToast(JsonActivity.this, listBaseBean.getMessage());
                                   } else {
                                       DialogUtils.ShowToast(JsonActivity.this, listBaseBean.getResultObj().get(0).getBoxId());
                                   }
                               }
                           }
                );
    }

    private void initview1() {
        Retrofit retrofit = new Retrofit.Builder()
                //设置基础的URL
                .baseUrl(url)
                //设置内容格式,这种对应的数据返回值是Gson类型，需要导包
                .addConverterFactory(GsonConverterFactory.create())
                //设置支持RxJava，应用observable观察者，需要导包
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .client(new OkHttpClient())
                .build();

        ApiService apIservice = retrofit.create(ApiService.class);

        Observable<BaseBean<LoginBean>> qqDataCall = apIservice.GetPark("admin", "123");

        qqDataCall.subscribeOn(Schedulers.io())//请求数据的事件发生在io线程
                .observeOn(AndroidSchedulers.mainThread())//请求完成后在主线程更新UI
                .subscribe(new Observer<BaseBean<LoginBean>>() {//订阅
                               @Override
                               public void onCompleted() {
                                   //所有事件都完成，可以做些操作。。。
                               }

                               @Override
                               public void onError(Throwable e) {
                                   e.printStackTrace(); //请求过程中发生错误
                               }

                               @Override
                               public void onNext(BaseBean<LoginBean> listBaseBean) {//这里的listBaseBean就是我们请求接口返回的实体类
                                   if (listBaseBean.getResultObj() == null || listBaseBean.getResultObj().equals("")) {
                                       DialogUtils.ShowToast(JsonActivity.this, listBaseBean.getMessage());
                                   } else {
                                       DialogUtils.ShowToast(JsonActivity.this, listBaseBean.getResultObj().getId());
                                   }
                               }
                           }
                );
    }

    //获取盘点任务和提交盘点
    private void initview2() {

        final CustomProgressDialog dialog = new CustomProgressDialog(JsonActivity.this, "获取数据中...");
        dialog.show();

        Map<String, String> params = new HashMap<>();
        params.put("userId", "1");
        params.put("whId", "289df933-eea0-495e-8755-df9687a52be5");

        Retrofit retrofit = new Retrofit.Builder()
                //设置基础的URL
                .baseUrl(url)
                //设置内容格式,这种对应的数据返回值是Gson类型，需要导包
                .addConverterFactory(GsonConverterFactory.create())
                //设置支持RxJava，应用observable观察者，需要导包
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .client(new OkHttpClient())
                .build();

        ApiService apIservice = retrofit.create(ApiService.class);

        Observable<BaseBean<List<Check>>> getAdata = apIservice.getStockTakeList(params);

        getAdata.subscribeOn(Schedulers.io())//请求数据的事件发生在io线程
                .observeOn(AndroidSchedulers.mainThread())//请求完成后在主线程更新UI
                .subscribe(new Observer<BaseBean<List<Check>>>() {//订阅
                               @Override
                               public void onCompleted() {
                                   //所有事件都完成，可以做些操作。。。

                                   dialog.dismiss();
                               }

                               @Override
                               public void onError(Throwable e) {
                                   dialog.dismiss();
                                   RxErrorHandler rxErrorHandler = new RxErrorHandler(JsonActivity.this);
                                   BaseException baseException =rxErrorHandler.handleError(e);
                                   rxErrorHandler.showErrorMessage(baseException);
                                   //e.printStackTrace(); //请求过程中发生错误
                               }

                               @Override
                               public void onNext(BaseBean<List<Check>> listBaseBean) {//这里的listBaseBean就是我们请求接口返回的实体类
                                   hashMap.clear();
                                   if (!listBaseBean.isSuccess()) {
                                       DialogUtils.ShowToast(JsonActivity.this, "请求失败");
                                   } else {
                                       if (listBaseBean.getResultObj() != null && listBaseBean.getResultObj().size() > 0) {
                                           for (Check info : listBaseBean.getResultObj()) {
                                               //把数据保存到序列化类中
                                               Check check = new Check();
                                               check.setLabelId(info.getLabelId());
                                               check.setStyle(info.getStyle());
                                               check.setColor(info.getColor());
                                               check.setSize(info.getSize());
                                               check.setTakeDate(info.getTakeDate());
                                               check.setTakeId(info.getTakeId());
                                               check.setTakeLocationId(info.getTakeLocationId());
                                               check.setTakeWHId(info.getTakeWHId());

                                               String key = info.getLabelId();
                                               if (!hashMap.containsKey(key)) {
                                                   Skinfin skinfin = new Skinfin();
                                                   skinfin.epc = info.getLabelId();
                                                   skinfin.get_EPC_LIST.add(check);
                                                   hashMap.put(key, skinfin);
                                               }
                                           }
                                           listshow();
                                       }else {
                                           DialogUtils.ShowToast(JsonActivity.this,"获取数据为空!");
                                       }
                                   }
                               }
                           }
                );
    }

    private void listshow() {
        List<Skinfin> mEpcList = new ArrayList<>();
        mEpcList.clear();
        Iterator it = hashMap.keySet().iterator();
        while (it.hasNext()) {
            String key1 = (String) it.next();
            Skinfin temp = hashMap.get(key1);
            mEpcList.add(temp);
        }
       /* if (adapter != null) { 更新适配器数据
            adapter.update(mEpcList);
        }*/

        ArrayList<Check> epcList = getEpcList();
        if (epcList.size() == 0) {
            DialogUtils.ShowToast(this, "提交数据为空!");
            return;
        }
        String json = JsonUtil.toJson(epcList);
        Log.d("json123", "listshow: " + json);
    }

    private ArrayList<Check> getEpcList() {
        long time =System.currentTimeMillis();
        Date date =new Date(time);
        SimpleDateFormat format =new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");

        ArrayList<Check> succ = new ArrayList<Check>();
        Iterator it = hashMap.keySet().iterator();
        while (it.hasNext()) {
            String key = (String) it.next();
            Skinfin skuInfo = hashMap.get(key);
            skuInfo.get_EPC_LIST.get(0).setTakeDate(format.format(date));
            succ.add(skuInfo.get_EPC_LIST.get(0));
        }
        return succ;
    }


    @OnClick({R.id.ip_tv,R.id.btn_invid, R.id.btn_login, R.id.btn_get})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_invid:
                initview();
                break;
            case R.id.btn_login:
                initview1();
                break;
            case R.id.btn_get:
                initview2();
                break;
            case R.id.ip_tv:
                url="http://192.168.1.100:8095/api/";
                break;
        }
    }
}
