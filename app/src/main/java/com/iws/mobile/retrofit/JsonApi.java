package com.iws.mobile.retrofit;

import com.google.gson.JsonElement;

import org.json.JSONObject;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface JsonApi {
    @GET("User")
    Call<ResponseBody> login(@Query("username") String username, @Query("password") String password);
}
