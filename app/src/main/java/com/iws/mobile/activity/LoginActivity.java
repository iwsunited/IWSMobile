package com.iws.mobile.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.InputFilter;
import android.text.SpannableString;
import android.text.style.UnderlineSpan;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.iws.mobile.CommonMethod;
import com.iws.mobile.R;

import org.json.JSONObject;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {
    private static final String TAG = "ganteng";
    ImageView imgLogo, imgBackground;
    EditText edtId, edtPass;
    ConstraintLayout btnLogin;
    TextView tvLupaPass, tvLink;
    ProgressDialog pd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        initView();
        onClick();
    }

    private void initView(){
        pd = new ProgressDialog(this);
        pd.setMessage(getString(R.string.loading));
        imgLogo = findViewById(R.id.img_login_logo);
        imgBackground = findViewById(R.id.img_login_background);
        edtId = findViewById(R.id.edt_login_idmember);
        edtPass = findViewById(R.id.edt_login_password);
        btnLogin = findViewById(R.id.cl_login);
        tvLupaPass = findViewById(R.id.tv_login_lupapassword);
        tvLink = findViewById(R.id.tv_login_link);

        Glide.with(this)
                .load(getDrawable(R.drawable.img_splash))
                .into(imgLogo);

        Glide.with(this)
                .load(getDrawable(R.drawable.bg_login))
                .into(imgBackground);

        edtId.setFilters(new InputFilter[]{new InputFilter.AllCaps()});

        SpannableString content = new SpannableString("www.iwsunited.co.id");
        content.setSpan(new UnderlineSpan(), 0, content.length(), 0);
        tvLink.setText(content);

        SpannableString content2 = new SpannableString("Lupa Password?");
        content2.setSpan(new UnderlineSpan(), 0, content2.length(), 0);
        tvLupaPass.setText(content2);
    }

    private void onClick(){
        tvLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("http://www.iwsunited.co.id"));
                startActivity(intent);
            }
        });

        tvLupaPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //lupa password kemana
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
            }
        });
    }

    private void login(){
        if (edtId.getText().toString().equals("") || edtPass.getText().toString().equals("")){
            Toast.makeText(this, getString(R.string.data_is_not_complete), Toast.LENGTH_SHORT).show();
            return;
        }

        pd.show();

        String id = edtId.getText().toString();
        String pass = edtPass.getText().toString();

        Log.d(TAG, "login: click");

        Call<ResponseBody> call = CommonMethod.getJsonApi().login(id,pass);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (!response.isSuccessful()){
                    pd.dismiss();
                    return;
                }

                try {
                    JSONObject hasil = new JSONObject(response.body().string());
                    Log.d(TAG, "onResponse: " + hasil.getString("message"));

                    String message = hasil.getString("message");

                    if (message.equals("Username Found")){
                        String nama = hasil.getJSONArray("data").getJSONObject(0).getString("member_nama");
                        Toast.makeText(LoginActivity.this, "Selamat datang " + nama, Toast.LENGTH_SHORT).show();
                        pd.dismiss();
                    } else {
                        Toast.makeText(LoginActivity.this, "Login gagal", Toast.LENGTH_SHORT).show();
                        pd.dismiss();
                    }
                } catch (Exception e){
                    pd.dismiss();
                    Log.d(TAG, "onResponse: " + e.getMessage());
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Toast.makeText(LoginActivity.this, getString(R.string.no_internet_connection), Toast.LENGTH_SHORT).show();
                pd.dismiss();
                Log.d(TAG, "onFailure: " + t.getMessage());
            }
        });
    }
}