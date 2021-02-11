package com.iws.mobile.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.iws.mobile.R;

public class PembayaranActivity extends AppCompatActivity {
    TextView tvNav;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pembayaran);

        tvNav = findViewById(R.id.tv_navbar_other);
        tvNav.setText("Pembayaran");
    }
}