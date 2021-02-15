package com.iws.mobile.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.iws.mobile.R;

public class PertumbuhanJaringanActivity extends AppCompatActivity {
    TableLayout tableLayout;
    TextView tvPanahBack, tvNav;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pertumbuhan_jaringan);

        tvNav = findViewById(R.id.tv_navbar_other);
        tvNav.setText("Pertumbuhan Jaringan");

        tvPanahBack = findViewById(R.id.tv_pertjaringan_pageback);
        tvPanahBack.setText("<");

        tableLayout = findViewById(R.id.tl_pertjaringan);

        add();
        addabuabu();
        add();
        addabuabu();
        add();
        addabuabu();
        add();
        addabuabu();
        add();
        addabuabu();
        add();
        addabuabu();
        add();
        addabuabu();
    }

    private void add(){
        TableRow row = new TableRow(this);

        row.setLayoutParams(new TableRow.LayoutParams(
                TableRow.LayoutParams.MATCH_PARENT,
                TableRow.LayoutParams.WRAP_CONTENT));

        TableRow.LayoutParams params = new TableRow.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT,
                1.0f);

        TextView tv1 = new TextView(this);
        tv1.setText("aha");
        TextView tv2 = new TextView(this);
        tv2.setText("ehe");
        TextView tv3 = new TextView(this);
        tv3.setText("oho");

        tv1.setLayoutParams(params);
        tv2.setLayoutParams(params);
        tv3.setLayoutParams(params);

        row.addView(tv1);
        row.addView(tv2);
        row.addView(tv3);

        tableLayout.addView(row);
    }

    private void addabuabu(){
        TableRow row = new TableRow(this);
        row.setBackgroundColor(getResources().getColor(R.color.abuabumuda));

        row.setLayoutParams(new TableRow.LayoutParams(
                TableRow.LayoutParams.MATCH_PARENT,
                TableRow.LayoutParams.WRAP_CONTENT));

        TableRow.LayoutParams params = new TableRow.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT,
                1.0f);

        TextView tv1 = new TextView(this);
        tv1.setText("aha");
        TextView tv2 = new TextView(this);
        tv2.setText("ehe");
        TextView tv3 = new TextView(this);
        tv3.setText("oho");

        tv1.setLayoutParams(params);
        tv2.setLayoutParams(params);
        tv3.setLayoutParams(params);

        row.addView(tv1);
        row.addView(tv2);
        row.addView(tv3);

        tableLayout.addView(row);
    }
}