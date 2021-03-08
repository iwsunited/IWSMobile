package com.iws.mobile.notification;

import androidx.annotation.NonNull;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.iws.mobile.CommonMethod;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MyFirebaseIDService extends FirebaseMessagingService {
    @Override
    public void onNewToken(@NonNull String s) {
        super.onNewToken(s);

        String token = FirebaseInstanceId.getInstance().getToken();
        updateToken(token);
    }

    private void updateToken(String token){
        //disini update tokennya di database
        Call<Void> call = CommonMethod.getJsonApiMemberIws().setToken(token, "10", "admin");
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {

            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {

            }
        });
    }
}
