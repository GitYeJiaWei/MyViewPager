package com.example.yjw.Retrofit;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;
import retrofit2.http.Url;
import rx.Observable;

/**
 * @author YJW
 * @create 2018/6/26
 * @Describe
 */
public interface APIservice {
    //指定get请求方式 http://www.baidu.com
    @GET
    Call<String> Baidu(@Url String url);

    // https://www.zftong.cn/tools/Register.ashx?action=get&sjh=18859863913
    @GET("/tools/Register.ashx")
    Call<QQData> QQ_DATA_CALL(@Query("action") String action, @Query("sjh") String Sjh);

    // https://www.zftong.cn/tools/android.ashx?committype=login&sjh=18859863913&pwd=321
    @FormUrlEncoded
    @POST("/tools/android.ashx")
    Call<QQDataT> QQ_DATA_CALL2(@FieldMap Map<String,String> map);

   //结合Rxjava 和Retrofit
   // https://www.zftong.cn/tools/android.ashx?committype=login&sjh=18859863913&pwd=321
   @FormUrlEncoded
   @POST("/tools/android.ashx")
   Observable<QQDataT> QQ_DATA_CALL3(@Field("committype") String login,@Field("sjh") String Sjh,@Field("pwd") String pword);

    //http://localhost:8081/Json?name=admin&password=123
    @GET("/Json")
    Observable<Json> JSON(@QueryMap Map<String,String> params);
}
