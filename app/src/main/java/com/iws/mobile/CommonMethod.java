package com.iws.mobile;

import com.iws.mobile.retrofit.JsonApi;
import com.iws.mobile.retrofit_test.JsonApi_test;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CommonMethod {
    public static String PREF_KEY = "pref_key";
    public static String ID_MEMBER_KEY = "id_member_key";

    public static JsonApi getJsonApi (){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://geni.iwsunited.co.id/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        JsonApi jsonApi = retrofit.create(JsonApi.class);
        return jsonApi;
    }

    public static JsonApi_test getJsonApi_test (){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.0.3/aldy/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        JsonApi_test jsonApi = retrofit.create(JsonApi_test.class);
        return jsonApi;
    }
}
