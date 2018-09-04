package com.example.yjw.JsonObject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;
import rx.Observable;

/**
 * @author YJW
 * @create 2018/7/27
 * @Describe
 */
public interface ApiService {
    String BaseUrl ="http://192.168.31.67:8095/api/";
    //后面要加/

    //{"Success":true,"Message":null,"ResultObj":[{"Id":"CKD1807180003","OrderId":"SCDD1807030003"},{...},{...}]}
    //http://192.168.31.67:8095/api/warehouse/GetInvoiceEpcList?invoiceId={invoiceId}
    @GET("warehouse/GetInvoiceEpcList")
    Observable<BaseBean<List<Park>>> GetMessage(@QueryMap HashMap<String,String> params);


    //{"Success":true,"Message":null,"ResultObj":["CKD1807180003","SCDD1807030003"]}
    //http://192.168.31.67:8095/api/warehouse/GetRecommendLocation?boxId={boxId}
    @GET("warehouse/GetRecommendLocation")
    Observable<BaseBean<List<String>>> GetString(@Query("boxId") String parmas);

    //{"Success":true,"Message":null,"ResultObj":{ "Id": "1","UserName": "Admin",}}
    //http://mall.ioter-e.com:8095/api/warehouse/Login?userName=admin&password=123
    @GET("warehouse/Login")
    Observable<BaseBean<LoginBean>> GetPark(@Query("userName") String name,@Query("password") String password);


    @GET("warehouse/GetStockTakeList")//获取盘点任务
    Observable<BaseBean<List<Check>>> getStockTakeList(@QueryMap Map<String,String> parmas);

    @FormUrlEncoded
    @POST("warehouse/SubmitStockTakeList")//提交盘点数据
    Observable<BaseBean> getStockTake(@FieldMap Map<String,String> parmas);
}
