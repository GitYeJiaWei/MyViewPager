package com.example.yjw.photoupload.photoupload2;

import java.util.List;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.adapter.rxjava.Result;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface ApiService {
    @Multipart
    @POST("Upload")
    Call<Result<String>> uploadMemberIcon(@Part List<MultipartBody.Part> partList);
}
