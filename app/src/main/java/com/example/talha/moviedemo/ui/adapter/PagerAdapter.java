package com.example.talha.moviedemo.ui.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.talha.moviedemo.ui.fragment.PopularFragment;
import com.example.talha.moviedemo.ui.fragment.TopRatedFragment;
import com.example.talha.moviedemo.ui.fragment.UpcomingFragment;

public class PagerAdapter extends FragmentStatePagerAdapter {
    int mNumOfTabs;

    public PagerAdapter(FragmentManager fm, int NumOfTabs) {
        super(fm);
        this.mNumOfTabs = NumOfTabs;
    }

    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                UpcomingFragment upcomingFragment = new UpcomingFragment();
                return upcomingFragment;
            case 1:
                TopRatedFragment topRatedFragment = new TopRatedFragment();
                return topRatedFragment;
            case 2:
                PopularFragment popularFragment = new PopularFragment();
                return popularFragment;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return mNumOfTabs;
    }
}
