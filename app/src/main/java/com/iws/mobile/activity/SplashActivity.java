package com.iws.mobile.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.iws.mobile.BuildConfig;
import com.iws.mobile.R;

public class SplashActivity extends AppCompatActivity {
    ImageView img;
    TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        img = findViewById(R.id.img_splash);
        tv = findViewById(R.id.tv_splash);

//        Glide.with(this)
//                .load(getDrawable(R.drawable.img_logo_google))
//                .into(img);

        tv.setText("Version " + BuildConfig.VERSION_NAME);

        final Handler handler = new Handler(Looper.getMainLooper());
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                boolean loggedin = true;
                Intent intent;

                if (loggedin){
                    intent = new Intent(SplashActivity.this, MainActivity.class);
                } else {
                    intent = new Intent(SplashActivity.this, LoginActivity.class);
                }

                startActivity(intent);
                finish();
            }
        }, 2000);
    }
}