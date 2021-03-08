package com.iws.mobile;

import android.util.Log;

import com.iws.mobile.retrofit.JsonApi;

import java.security.GeneralSecurityException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.util.Arrays;
import java.util.Collections;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import okhttp3.CipherSuite;
import okhttp3.ConnectionSpec;
import okhttp3.OkHttpClient;
import okhttp3.Protocol;
import okhttp3.TlsVersion;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CommonMethod {
    public static String PREF_KEY = "pref_key";
    public static String ID_MEMBER_KEY = "id_member_key";

    public static JsonApi getJsonApiGeniIws() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.100.90/geni/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        JsonApi jsonApi = retrofit.create(JsonApi.class);
        return jsonApi;
    }

    public static JsonApi getJsonApiMemberIws() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://member.iwsunited.co.id/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        JsonApi jsonApi = retrofit.create(JsonApi.class);
        return jsonApi;
    }
}
