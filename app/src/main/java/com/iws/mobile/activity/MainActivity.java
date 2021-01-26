package com.iws.mobile.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;

import com.google.android.material.tabs.TabLayout;
import com.iws.mobile.R;
import com.iws.mobile.adapter.FragPagerAdapter;

public class MainActivity extends AppCompatActivity {

    TabLayout tabLayout;
    ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        setTabLayout();
    }

    private void initView(){
        tabLayout = findViewById(R.id.tl_main);
        viewPager = findViewById(R.id.vp_main);
    }

    private void setTabLayout(){
        int[] arrayDrawable = {R.drawable.ic_account_circle,
                R.drawable.ic_account_circle,
                R.drawable.ic_account_circle,
                R.drawable.ic_account_circle,
                R.drawable.ic_account_circle,
                R.drawable.ic_account_circle};

        FragPagerAdapter adapter = new FragPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
        for (int i = 0; i < arrayDrawable.length; i++){
            tabLayout.getTabAt(i).setIcon(arrayDrawable[i]);
        }

//        viewPager.setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View v, MotionEvent event) {
//                return true;
//            }
//        });

//        viewPager.onInterceptTouchEvent(new View.OnInter)
    }
}