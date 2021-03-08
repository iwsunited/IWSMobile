package com.iws.mobile.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.text.InputFilter;
import android.text.SpannableString;
import android.text.style.UnderlineSpan;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.iws.mobile.CommonMethod;
import com.iws.mobile.R;

import org.json.JSONObject;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {
    private static final String TAG = "ganteng";
    ImageView imgLogo, imgBackground;
    EditText edtId, edtPass;
    ConstraintLayout btnLogin;
    TextView tvLupaPass, tvLink;
    ProgressDialog pd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        initView();
        onClick();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    private void initView() {
        pd = new ProgressDialog(this);
        pd.setMessage(getString(R.string.loading));
        imgLogo = findViewById(R.id.img_login_logo);
        imgBackground = findViewById(R.id.img_login_background);
        edtId = findViewById(R.id.edt_login_idmember);
        edtPass = findViewById(R.id.edt_login_password);
        btnLogin = findViewById(R.id.cl_login);
        tvLupaPass = findViewById(R.id.tv_login_lupapassword);
        tvLink = findViewById(R.id.tv_login_link);

        Glide.with(this)
                .load(getDrawable(R.drawable.img_splash))
                .into(imgLogo);

        Glide.with(this)
                .load(getDrawable(R.drawable.bg_login))
                .into(imgBackground);

        edtId.setFilters(new InputFilter[]{new InputFilter.AllCaps()});

        SpannableString content = new SpannableString("www.iwsunited.co.id");
        content.setSpan(new UnderlineSpan(), 0, content.length(), 0);
        tvLink.setText(content);

        SpannableString content2 = new SpannableString("Lupa Password?");
        content2.setSpan(new UnderlineSpan(), 0, content2.length(), 0);
        tvLupaPass.setText(content2);
    }

    private void onClick() {
        tvLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("http://www.iwsunited.co.id"));
                startActivity(intent);
            }
        });

        tvLupaPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //lupa password kemana
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
            }
        });
    }

    private void login() {
        if (edtId.getText().toString().equals("") || edtPass.getText().toString().equals("")) {
            Toast.makeText(this, getString(R.string.data_is_not_complete), Toast.LENGTH_SHORT).show();
            return;
        }

        pd.show();

        String username = edtId.getText().toString();
        String pass = edtPass.getText().toString();

        Call<ResponseBody> call = CommonMethod.getJsonApiGeniIws().login(username, pass);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (!response.isSuccessful()) {
                    pd.dismiss();
                    return;
                }

                try {
                    JSONObject hasil = new JSONObject(response.body().string());

                    String statusData = hasil.getString("status_data");
                    Log.d(TAG, "onResponse: " + statusData);

                    if (statusData.equals("y")) {
                        JSONObject result = hasil.getJSONObject("data");

                        String secure_username = result.getString("secure_username");
                        String secure_id = result.getString("secure_id");
                        String member_nama = result.getString("member_nama");
                        String member_email = result.getString("member_email");
                        String member_kelamin = result.getString("member_kelamin");
                        String member_telf = result.getString("member_telf");
                        String member_wa = result.getString("member_wa");
                        String member_alamat = result.getString("member_alamat");
                        String member_negara = result.getString("member_negara");
                        String member_provinsi = result.getString("member_provinsi");
                        String member_kota = result.getString("member_kota");
                        String member_kecamatan = result.getString("member_kecamatan");
                        String member_kodepost = result.getString("member_kodepost");
                        String member_tempatlahir = result.getString("member_tempatlahir");
                        String member_tanggallahir = result.getString("member_tanggallahir");
                        String member_nama_rekening = result.getString("member_nama_rekening");
                        String member_no_rekening = result.getString("member_no_rekening");
                        String member_bank = result.getString("member_bank");
                        String member_ktp = result.getString("member_ktp");
                        String member_npwp = result.getString("member_npwp");
                        String member_nama_ibu = result.getString("member_nama_ibu");
                        String member_nama_ahli_waris = result.getString("member_nama_ahli_waris");
                        String member_hubungan_aw = result.getString("member_hubungan_aw");
                        String member_produk = result.getString("member_produk");
                        String member_tanggal_join = result.getString("member_tanggal_join");
                        String member_foto = result.getString("member_foto");
                        String member_daftar_ulang = result.getString("member_daftar_ulang");
                        String member_stockis = result.getString("member_stockis");
                        String bv_id = result.getString("bv_id");
                        String bv_amount = result.getString("bv_amount");
                        String paket_nama = result.getString("paket_nama");

                        Toast.makeText(LoginActivity.this, "Selamat datang " + member_nama, Toast.LENGTH_SHORT).show();
                        pd.dismiss();

                        //simpan data user
                        SharedPreferences pref = getSharedPreferences(CommonMethod.PREF_KEY, MODE_PRIVATE);
                        SharedPreferences.Editor editor = pref.edit();

                        editor.putBoolean("login_status", true);
                        editor.putString("secure_username", secure_username);
                        editor.putString("secure_id", secure_id);
                        editor.putString("member_nama", member_nama);
                        editor.putString("member_email", member_email);
                        editor.putString("member_kelamin", member_kelamin);
                        editor.putString("member_telf", member_telf);
                        editor.putString("member_wa", member_wa);
                        editor.putString("member_alamat", member_alamat);
                        editor.putString("member_negara", member_negara);
                        editor.putString("member_provinsi", member_provinsi);
                        editor.putString("member_kota", member_kota);
                        editor.putString("member_kecamatan", member_kecamatan);
                        editor.putString("member_kodepost", member_kodepost);
                        editor.putString("member_tempatlahir", member_tempatlahir);
                        editor.putString("member_tanggallahir", member_tanggallahir);
                        editor.putString("member_nama_rekening", member_nama_rekening);
                        editor.putString("member_no_rekening", member_no_rekening);
                        editor.putString("member_bank", member_bank);
                        editor.putString("member_ktp", member_ktp);
                        editor.putString("member_npwp", member_npwp);
                        editor.putString("member_nama_ibu", member_nama_ibu);
                        editor.putString("member_nama_ahli_waris", member_nama_ahli_waris);
                        editor.putString("member_hubungan_aw", member_hubungan_aw);
                        editor.putString("member_produk", member_produk);
                        editor.putString("member_tanggal_join", member_tanggal_join);
                        editor.putString("member_foto", member_foto);
                        editor.putString("member_daftar_ulang", member_daftar_ulang);
                        editor.putString("member_stockis", member_stockis);
                        editor.putString("bv_id", bv_id);
                        editor.putString("bv_amount", bv_amount);
                        editor.putString("paket_nama", paket_nama);

                        editor.apply();

                        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                        startActivity(intent);
                        finish();
                    } else {
                        Toast.makeText(LoginActivity.this, "Login gagal", Toast.LENGTH_SHORT).show();
                        pd.dismiss();
                    }
                } catch (Exception e) {
                    Log.d(TAG, "onResponse: error gan");
                    pd.dismiss();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Toast.makeText(LoginActivity.this, getString(R.string.no_internet_connection), Toast.LENGTH_SHORT).show();
                pd.dismiss();
            }
        });
    }
}