package com.iws.mobile.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.iws.mobile.R;
import com.iws.mobile.adapter.DetailBarangSliderAdapter;
import com.iws.mobile.model.SliderItem;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;

public class DetailBarangActivity extends AppCompatActivity {
    SliderView slider;
    DetailBarangSliderAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_barang);

        slider = findViewById(R.id.sv_detail_barang);
        adapter = new DetailBarangSliderAdapter(this);

        slider.setSliderAdapter(adapter);
        slider.setSliderTransformAnimation(SliderAnimations.SIMPLETRANSFORMATION);
        slider.setScrollTimeInSec(4);
        slider.setAutoCycle(true);
        slider.startAutoCycle();
    }
}