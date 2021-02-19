package com.iws.mobile.retrofit_test;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface JsonApi_test {
    @GET("ambiltoken.php")
    Call<String> ambiltoken();

    @FormUrlEncoded
    @POST("settoken.php")
    Call<Void> settoken(@Field("token") String token);
}
