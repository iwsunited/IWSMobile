package com.iws.mobile.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.iws.mobile.CommonMethod;
import com.iws.mobile.R;
import com.iws.mobile.model.SliderItem;

import org.json.JSONArray;
import org.json.JSONObject;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BonusActivity extends AppCompatActivity {
    private static final String TAG = "ganteng";
    TextView tvPanahBack, tvNav, tvBonusHariIni, tvBonusBulanIni, tvNama, tvUsername, tvBonusTerbentuk, tvBonusTransfer, tvBonusBelumTransfer;
    TableLayout tableLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bonus);

        initView();
        loadData();
    }

    private void initView() {
        tvNav = findViewById(R.id.tv_navbar_other);
        tvNav.setText("Bonus");
        tvPanahBack = findViewById(R.id.tv_bonus_pageback);
        tvPanahBack.setText("<");

        tvBonusHariIni = findViewById(R.id.tv_bonus_hariini);
        tvBonusBulanIni = findViewById(R.id.tv_bonus_bulanini);
        tvNama = findViewById(R.id.tv_bonus_nama);
        tvUsername = findViewById(R.id.tv_bonus_username);
        tableLayout = findViewById(R.id.tl_bonus);
        tvBonusTerbentuk = findViewById(R.id.tv_bonus_bonusterbentuk);
        tvBonusTransfer = findViewById(R.id.tv_bonus_bonusditransfer);
        tvBonusBelumTransfer = findViewById(R.id.tv_bonus_bonusbelumditansfer);
    }

    private void loadData() {
        SharedPreferences pref = getSharedPreferences(CommonMethod.PREF_KEY, MODE_PRIVATE);

        String userid = pref.getString("secure_id", "");
        String nama = pref.getString("member_nama", "");
        String username = pref.getString("secure_username", "");

        tvUsername.setText(username);
        tvNama.setText(nama);

        Log.d(TAG, "loadData: userid : " + userid);

        Call<ResponseBody> call = CommonMethod.getJsonApiGeniIws().bonus("1", "1", "", "", "10");
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (!response.isSuccessful()) {
                    Log.d(TAG, "onResponse: code : " + response.code());
                    return;
                }

                try {
                    JSONObject hasil = new JSONObject(response.body().string());
                    JSONObject result = hasil.getJSONObject("data2");
                    String bonusHariIni = (result.getString("bonus_hari_ini") == null) ? "0" : result.getString("bonus_hari_ini");
                    String bonusBulanIni = (result.getString("bonus_bulan_ini") == null) ? "0" : result.getString("bonus_bulan_ini");
                    String bonusTerbentuk = (result.getString("bonus_terbentuk") == null) ? "0" : result.getString("bonus_terbentuk");
                    String bonusTransfer = (result.getString("bonus_ditransfer") == null) ? "0" : result.getString("bonus_ditransfer");
                    String bonusBelumTransfer = (result.getString("bonus_belum_ditransfer") == null) ? "0" : result.getString("bonus_belum_ditransfer");

                    tvBonusHariIni.setText("Rp. " + bonusHariIni);
                    tvBonusBulanIni.setText("Rp. " + bonusBulanIni);
                    tvBonusTerbentuk.setText("Rp. " + bonusTerbentuk);
                    tvBonusTransfer.setText("Rp. " + bonusTransfer);
                    tvBonusBelumTransfer.setText("Rp. " + bonusBelumTransfer);

                    JSONArray bonuslist = hasil.getJSONArray("data");
                    if (bonuslist.length() == 0) {
                        return;
                    }

                    for (int i = 0; i < bonuslist.length(); i++) {
                        String tanggal = bonuslist.getJSONObject(i).getString("bonus_date");
                        String jenis = bonuslist.getJSONObject(i).getString("bonus_name");
                        String jumlah = bonuslist.getJSONObject(i).getString("bonus_amount_formatted");

                        if (i%2 == 0){
                            addTableRow(tanggal, jenis, jumlah, false);
                        } else {
                            addTableRow(tanggal, jenis, jumlah, true);
                        }
                    }

                } catch (Exception e) {
                    Log.d(TAG, "onResponse: " + e.getMessage());
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.d(TAG, "onFailure: " + t.getMessage());
            }
        });
    }

    private void addTableRow(String tanggal, String jenis, String jumlah, boolean abuabu) {
        TableRow row = new TableRow(this);
        if (abuabu) {
            row.setBackgroundColor(getResources().getColor(R.color.abuabumuda));
        }

        row.setLayoutParams(new TableRow.LayoutParams(
                TableRow.LayoutParams.MATCH_PARENT,
                TableRow.LayoutParams.WRAP_CONTENT));

        TableRow.LayoutParams params = new TableRow.LayoutParams(0,
                ViewGroup.LayoutParams.WRAP_CONTENT,
                1.0f);

        TextView tv1 = new TextView(this);
        tv1.setText(tanggal);
        tv1.setGravity(Gravity.CENTER);
        TextView tv2 = new TextView(this);
        tv2.setText(jenis);
        tv2.setGravity(Gravity.CENTER);
        TextView tv3 = new TextView(this);
        tv3.setText(jumlah);
        tv3.setGravity(Gravity.CENTER);

        tv1.setLayoutParams(params);
        tv2.setLayoutParams(params);
        tv3.setLayoutParams(params);

        row.addView(tv1);
        row.addView(tv2);
        row.addView(tv3);

        tableLayout.addView(row);
    }
}