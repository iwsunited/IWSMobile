package com.iws.mobile.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.iws.mobile.fragment.BerandaFragment;
import com.iws.mobile.fragment.BonusFragment;
import com.iws.mobile.fragment.JaringanFragment;
import com.iws.mobile.fragment.ProfilFragment;
import com.iws.mobile.fragment.SettingFragment;
import com.iws.mobile.fragment.ShopFragment;

public class FragPagerAdapter extends FragmentPagerAdapter {

    public FragPagerAdapter(@NonNull FragmentManager fm) {
        super(fm);
    }

    public FragPagerAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return new BerandaFragment();
            case 1:
                return new ShopFragment();
            case 2:
                return new BonusFragment();
            case 3:
                return new JaringanFragment();
            case 4:
                return new SettingFragment();
        }
        return new ProfilFragment();
    }

    @Override
    public int getCount() {
        return 6;
    }
}
