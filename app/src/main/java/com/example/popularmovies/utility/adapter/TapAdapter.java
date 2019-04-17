package com.example.popularmovies.utility.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.popularmovies.ReviewFragment;
import com.example.popularmovies.TrailerFragment;

public class TapAdapter extends FragmentPagerAdapter {


    public TapAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int i) {

        if(i==0){
            return new ReviewFragment();
        }else{
            return new TrailerFragment();
        }


    }

    @Override
    public int getCount() {
        return 2;
    }
}
