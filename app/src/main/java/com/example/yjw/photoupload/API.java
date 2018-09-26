package com.example.yjw.photoupload;

import java.util.List;

import okhttp3.MultipartBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.scalars.ScalarsConverterFactory;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public class API {

    private static RetrofitAPI retrofitAPI;

    public static RetrofitAPI Retrofit() {
        if (retrofitAPI == null) {

            retrofitAPI = new Retrofit.Builder()
                    .baseUrl("http://192.168.31.112:8081/")
                    .addConverterFactory(ScalarsConverterFactory.create())
                    .build()
                    .create(RetrofitAPI.class);
        }
        return retrofitAPI;
    }

    //Retrofit 上传图片
    public interface RetrofitAPI {

        //单张图片上传
        @Multipart
        @POST("Upload")
        Call<String> updateImage(@Part MultipartBody.Part file);

        //多张图片上传
        @Multipart
        @POST("Upload")
        Call<String> updateImage(@Part MultipartBody.Part[] file);

        @Multipart
        @POST("Upload")
        Call<ResponseBody> feedBack(@Part("Content") String Content, @Part("ContactInformation")String conInfo,
                                    @Part() List<MultipartBody.Part> parts);

    }

}