package com.iws.mobile.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.iws.mobile.R;

public class BonusActivity extends AppCompatActivity {
    TextView tvPanahBack, tvNav;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bonus);

        tvNav = findViewById(R.id.tv_navbar_other);
        tvNav.setText("Bonus");
        tvPanahBack = findViewById(R.id.tv_bonus_pageback);
        tvPanahBack.setText("<");
    }
}