package com.iws.mobile.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.iws.mobile.R;
import com.iws.mobile.adapter.KeranjangAdapter;
import com.iws.mobile.model.KeranjangItem;

import java.util.ArrayList;

public class KeranjangActivity extends AppCompatActivity {
    RecyclerView rv;
    KeranjangAdapter adapter;
    ArrayList<KeranjangItem> items;
    TextView tvNav;
    ConstraintLayout clBayar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_keranjang);

        initView();
    }

    private void initView(){
        tvNav = findViewById(R.id.tv_navbar_other);
        tvNav.setText("Keranjang Saya");

        rv = findViewById(R.id.rv_keranjang);

        items = new ArrayList<>();
        items.add(new KeranjangItem());
        items.add(new KeranjangItem());
        items.add(new KeranjangItem());
        items.add(new KeranjangItem());
        items.add(new KeranjangItem());
        items.add(new KeranjangItem());

        adapter = new KeranjangAdapter(this, items);
        rv.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));
        rv.setAdapter(adapter);

        clBayar = findViewById(R.id.cl_keranjang_finishpayment);
        clBayar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(KeranjangActivity.this, PembayaranActivity.class);
                startActivity(intent);
            }
        });
    }
}