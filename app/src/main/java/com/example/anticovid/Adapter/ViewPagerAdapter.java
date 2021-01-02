package com.example.anticovid.Adapter;

import com.example.anticovid.Fragment.fragmentNews;
import com.example.anticovid.Fragment.fragmentStatistic;
import com.example.anticovid.Fragment.fragmentFaq;
import com.example.anticovid.Fragment.fragmentHome;


import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

public class ViewPagerAdapter extends FragmentStatePagerAdapter {
    public ViewPagerAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return new fragmentHome();
            case 1:
                return  new fragmentNews();
            case 2:
                return  new fragmentStatistic();
            case 3:
                return new fragmentFaq();
            default:
                return new fragmentHome();
        }
       
    }

    @Override
    public int getCount() {
        return 4;
    }
}
