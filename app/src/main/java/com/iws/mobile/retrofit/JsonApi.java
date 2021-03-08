package com.iws.mobile.retrofit;

import com.google.gson.JsonElement;

import org.json.JSONObject;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface JsonApi {
    @FormUrlEncoded
    @POST("login.php")
    Call<ResponseBody> login(@Field("username") String username, @Field("password") String password);

    @FormUrlEncoded
    @POST("android/setToken")
    Call<Void> setToken(@Field("user_token") String user_token, @Field("user_id") String user_id, @Field("user_type") String user_type);
}
