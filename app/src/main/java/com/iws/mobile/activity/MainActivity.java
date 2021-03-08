package com.iws.mobile.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;
import com.iws.mobile.CommonMethod;
import com.iws.mobile.R;
import com.iws.mobile.fragment.BerandaFragment;
import com.iws.mobile.fragment.BonusFragment;
import com.iws.mobile.fragment.JaringanFragment;
import com.iws.mobile.fragment.ProfilFragment;
import com.iws.mobile.fragment.SettingFragment;
import com.iws.mobile.fragment.ShopFragment;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "ganteng";
    
    ConstraintLayout clBarBotBeranda, clBarBotShop, clBarBotBonus, clBarBotJaringan, clBarBotSetting, clBarBotProfil;
    ConstraintLayout clBarTopSetting, clBarTopCart, clBarTopNotif, clBarTopAkun, clBarTopLogout;
    TextView tvBallTopCart, tvBallTopNotif, tvTopBallAkun;
    ConstraintLayout clBallContainerTopCart, clBallContainerTopNotif, clBallContainerTopAkun;

    ImageView imgBarBotBeranda, imgBarBotShop, imgBarBotBonus, imgBarBotJaringan, imgBarBotSetting, imgBarBotProfil;

    Fragment fragBeranda, fragShop, fragBonus, fragJaringan, fragSetting, fragProfil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        onClick();
        setFlContainer();
//        setToken();
    }

    private void setToken(){
        Log.d(TAG, "setToken: jalan");
        FirebaseInstanceId.getInstance().getInstanceId()
                .addOnCompleteListener(new OnCompleteListener<InstanceIdResult>() {
                    @Override
                    public void onComplete(@NonNull Task<InstanceIdResult> task) {
                        Log.d(TAG, "onComplete: jalan");
                        String token = task.getResult().getToken();
                        String userId = "10";
                        String userType = "admin";

                        Call<Void> call = CommonMethod.getJsonApiMemberIws().setToken(token, userId, userType);
                        call.enqueue(new Callback<Void>() {
                            @Override
                            public void onResponse(Call<Void> call, Response<Void> response) {
                                Toast.makeText(MainActivity.this, "success token : " + token, Toast.LENGTH_SHORT).show();
                                Log.d(TAG, "onResponse: token : " + token);
                            }

                            @Override
                            public void onFailure(Call<Void> call, Throwable t) {
                                Toast.makeText(MainActivity.this, "failed msg : " + t.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                });
    }

    private void initView() {
        clBarBotBeranda = findViewById(R.id.cl_navbar_bot_beranda);
        clBarBotShop = findViewById(R.id.cl_navbar_bot_shop);
        clBarBotBonus = findViewById(R.id.cl_navbar_bot_bonus);
        clBarBotJaringan = findViewById(R.id.cl_navbar_bot_jaringan);
        clBarBotSetting = findViewById(R.id.cl_navbar_bot_setting);
        clBarBotProfil = findViewById(R.id.cl_navbar_bot_profil);
        clBarTopSetting = findViewById(R.id.cl_navbar_top_setting);
        clBarTopCart = findViewById(R.id.cl_navbar_top_cart);
        clBarTopNotif = findViewById(R.id.cl_navbar_top_notif);
        clBarTopAkun = findViewById(R.id.cl_navbar_top_akun);
        clBarTopLogout = findViewById(R.id.cl_navbar_top_logout);

        tvBallTopCart = findViewById(R.id.tv_navbar_top_angka_cart);
        tvBallTopNotif = findViewById(R.id.tv_navbar_top_angka_notif);
        tvTopBallAkun = findViewById(R.id.tv_navbar_top_angka_akun);

        clBallContainerTopCart = findViewById(R.id.cl_navbar_top_ball_cart);
        clBallContainerTopNotif = findViewById(R.id.cl_navbar_top_ball_notif);
        clBallContainerTopAkun = findViewById(R.id.cl_navbar_top_ball_akun);

        fragBeranda = new BerandaFragment();
        fragShop = new ShopFragment();
        fragBonus = new BonusFragment();
        fragJaringan = new JaringanFragment();
        fragSetting = new SettingFragment();
        fragProfil = new ProfilFragment();
    }

    private void setFlContainer() {
        //langsung ke beranda
        loadFragment(new BerandaFragment());
    }

    private void loadFragment(Fragment fragment) {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.fl_main, fragment);
        ft.commit();
    }

    private void onClick() {
        clBarBotBeranda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadFragment(fragBeranda);
            }
        });
        clBarBotShop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadFragment(fragShop);
            }
        });
        clBarBotBonus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, BonusActivity.class);
                startActivity(intent);
            }
        });
        clBarBotJaringan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, DiagramJaringanActivity.class);
                startActivity(intent);
            }
        });
        clBarBotSetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                loadFragment(fragSetting);
            }
        });
        clBarBotProfil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                loadFragment(fragProfil);
            }
        });
        clBarTopSetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //belum
            }
        });
        clBarTopCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //belum
            }
        });
        clBarTopNotif.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //belum
            }
        });
        clBarTopAkun.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //belum
            }
        });
        clBarTopLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getSharedPreferences(CommonMethod.PREF_KEY, 0).edit().clear().commit();

                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });
        clBarTopCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, KeranjangActivity.class);
                startActivity(intent);
            }
        });
    }
}