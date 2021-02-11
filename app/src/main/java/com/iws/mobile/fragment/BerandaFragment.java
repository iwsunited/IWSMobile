package com.iws.mobile.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.iws.mobile.R;
import com.iws.mobile.activity.PertumbuhanJaringanActivity;
import com.iws.mobile.adapter.BerandaSliderBotAdapter;
import com.iws.mobile.adapter.BerandaSliderTopAdapter;
import com.iws.mobile.model.SliderItem;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;

public class BerandaFragment extends Fragment {
//    ArrayList<SliderItem> sliderItemsTop, sliderItemsBot;
    SliderView sliderTop, sliderBot;
    BerandaSliderTopAdapter adapterTop;
    BerandaSliderBotAdapter adapterBot;

    View v;

    ConstraintLayout clPertJaringan;

    public BerandaFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.fragment_beranda, container, false);

        sliderTop = v.findViewById(R.id.slider_beranda_top);
        sliderBot = v.findViewById(R.id.slider_beranda_bot);

//        sliderItemsTop = new ArrayList<>();
//        sliderItemsBot = new ArrayList<>();

        adapterTop = new BerandaSliderTopAdapter(v.getContext());
        adapterBot = new BerandaSliderBotAdapter(v.getContext());

        setSlider();

        clPertJaringan = v.findViewById(R.id.cl_beranda_pertjaringan);
        clPertJaringan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), PertumbuhanJaringanActivity.class);
                startActivity(intent);
            }
        });

        return v;
    }

    private void setSlider(){

//        adapterTop.addItem(new SliderItem("https://img.okezone.com/content/2021/01/18/46/2346166/dikartu-merah-berapa-lama-lionel-messi-bakal-absen-ris8ED8yjX.JPG"));
//        adapterTop.addItem(new SliderItem("https://img.okezone.com/content/2021/01/18/46/2346166/dikartu-merah-berapa-lama-lionel-messi-bakal-absen-ris8ED8yjX.JPG"));
//        adapterTop.addItem(new SliderItem("https://img.okezone.com/content/2021/01/18/46/2346166/dikartu-merah-berapa-lama-lionel-messi-bakal-absen-ris8ED8yjX.JPG"));

        adapterTop.addItem(new SliderItem(R.drawable.slider_test_1));
        adapterTop.addItem(new SliderItem(R.drawable.slider_test_2));
        adapterTop.addItem(new SliderItem(R.drawable.slider_test_3));
        sliderTop.setSliderAdapter(adapterTop);
        sliderTop.setSliderTransformAnimation(SliderAnimations.SIMPLETRANSFORMATION);
        sliderTop.setScrollTimeInSec(4);
        sliderTop.setAutoCycle(true);
        sliderTop.startAutoCycle();

        adapterBot.addItem(new SliderItem(R.drawable.slider_test_1));
        adapterBot.addItem(new SliderItem(R.drawable.slider_test_2));
        adapterBot.addItem(new SliderItem(R.drawable.slider_test_3));
        sliderBot.setSliderAdapter(adapterBot);
        sliderBot.setSliderTransformAnimation(SliderAnimations.SIMPLETRANSFORMATION);
        sliderBot.setScrollTimeInSec(4);
        sliderBot.setAutoCycle(true);
        sliderBot.startAutoCycle();
    }
}