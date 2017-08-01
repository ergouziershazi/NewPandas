package com.newpandas.ui.pandalive;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;

/**
 * Created by yan on 2017/7/31.
 */

public class OutVpAdapter extends FragmentPagerAdapter {
    ArrayList<Fragment> fragments;
    ArrayList<String> strings;
    public OutVpAdapter(FragmentManager fm,ArrayList<Fragment> fragments,ArrayList<String> strings) {
        super(fm);
        this.fragments=fragments;
        this.strings=strings;
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return strings.get(position);
    }
}
