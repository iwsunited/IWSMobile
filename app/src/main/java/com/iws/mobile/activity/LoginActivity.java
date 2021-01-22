package com.iws.mobile.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.iws.mobile.R;

public class LoginActivity extends AppCompatActivity {
    ImageView imgLogo, imgBackground;
    TextView tvLupaPassword, tvLink;
    EditText edtId, edtPass;
    Button btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        initView();
    }

    private void initView(){
        imgLogo = findViewById(R.id.img_login_logo);
        imgBackground = findViewById(R.id.img_login_background);
        tvLupaPassword = findViewById(R.id.tv_login_lupapassword);
        tvLink = findViewById(R.id.tv_login_link);
        edtId = findViewById(R.id.edt_login_idmember);
        edtPass = findViewById(R.id.edt_login_password);
        btnLogin = findViewById(R.id.btn_login);
    }
}