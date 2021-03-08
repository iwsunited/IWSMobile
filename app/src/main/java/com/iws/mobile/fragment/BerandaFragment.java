package com.iws.mobile.fragment;

import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.VideoView;

import com.bumptech.glide.Glide;
import com.iws.mobile.CommonMethod;
import com.iws.mobile.R;
import com.iws.mobile.activity.DiagramJaringanActivity;
import com.iws.mobile.activity.PertumbuhanJaringanActivity;
import com.iws.mobile.adapter.BerandaSliderBotAdapter;
import com.iws.mobile.model.SliderItem;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;

import org.json.JSONArray;
import org.json.JSONObject;

import de.hdodenhof.circleimageview.CircleImageView;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.Context.MODE_PRIVATE;

public class BerandaFragment extends Fragment {
    private static final String TAG = "ganteng";

    //    ArrayList<SliderItem> sliderItemsTop, sliderItemsBot;
    SliderView sliderBot;
    BerandaSliderBotAdapter adapterBot;

    CircleImageView imgProfil;
    TextView tvNama, tvBv, tvUsername, tvTanggal;
    ImageView imgPaket;

    VideoView video;

    View v;

    ConstraintLayout clPertJaringan, clDiagJaringan;

    SharedPreferences preferences;

    public BerandaFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.fragment_beranda, container, false);

        preferences = getActivity().getSharedPreferences(CommonMethod.PREF_KEY, MODE_PRIVATE);

        initView();
        loadData();
        setSlider();
        onClick();

        return v;
    }

    private void loadData() {
        Glide.with(getContext())
                .load("https://member.iwsunited.co.id/assets/uploads/user/" + preferences.getString("member_foto", ""))
                .into(imgProfil);

        tvNama.setText(preferences.getString("member_nama", ""));
        tvBv.setText(preferences.getString("bv_amount", "") + " BV");
        tvUsername.setText(preferences.getString("secure_username", ""));
        tvTanggal.setText(preferences.getString("member_tanggal_join", ""));

        String paket = preferences.getString("paket_nama", "");
        if (paket.equalsIgnoreCase("gold")) {
            imgPaket.setImageResource(R.drawable.dash_gold);
            imgPaket.setAdjustViewBounds(true);
        } else if (paket.equalsIgnoreCase("silver")) {
            imgPaket.setImageResource(R.drawable.dash_silver);
            imgPaket.setAdjustViewBounds(true);
        } else {
            imgPaket.setImageResource(R.drawable.dash_bronze);
            imgPaket.setAdjustViewBounds(true);
        }

        video.setVideoURI(Uri.parse("http://www.iwsunited.co.id/assets/img/slides/bumper_iws.mp4"));
//        video.setVisibility(View.VISIBLE);
        video.start();

        video.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                mp.setVolume(0f, 0f);
                mp.setLooping(true);
            }
        });
    }

    private void initView() {

        video = v.findViewById(R.id.vv_beranda);
        imgProfil = v.findViewById(R.id.img_beranda_profil);
        tvNama = v.findViewById(R.id.tv_beranda_nama);
        tvBv = v.findViewById(R.id.tv_beranda_bvamount);
        tvUsername = v.findViewById(R.id.tv_beranda_username);
        tvTanggal = v.findViewById(R.id.tv_beranda_tgljoin);

        sliderBot = v.findViewById(R.id.slider_beranda_bot);

//        sliderItemsBot = new ArrayList<>();

        adapterBot = new BerandaSliderBotAdapter(v.getContext());

        clPertJaringan = v.findViewById(R.id.cl_beranda_pertjaringan);
        clDiagJaringan = v.findViewById(R.id.cl_beranda_diagjaringan);

        imgPaket = v.findViewById(R.id.img_beranda_paket);
    }

    private void onClick() {
        clPertJaringan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), PertumbuhanJaringanActivity.class);
                startActivity(intent);
            }
        });

        clDiagJaringan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), DiagramJaringanActivity.class);
                startActivity(intent);
            }
        });
    }

    private void setSlider() {
        //ambil linknya dulu
        Call<ResponseBody> call = CommonMethod.getJsonApiGeniIws().getSliderBeranda();
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (!response.isSuccessful()) {
                    return;
                }

                try {
                    JSONObject hasil = new JSONObject(response.body().string());
                    JSONArray result = hasil.getJSONObject("data").getJSONArray("image");
                    for (int i = 0; i < result.length(); i++){
                        adapterBot.addItem(new SliderItem(result.getString(i)));
                    }
                } catch (Exception e) {
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
            }
        });

        sliderBot.setSliderAdapter(adapterBot);
        sliderBot.setSliderTransformAnimation(SliderAnimations.SIMPLETRANSFORMATION);
        sliderBot.setScrollTimeInSec(4);
        sliderBot.setAutoCycle(true);
        sliderBot.startAutoCycle();
    }
}