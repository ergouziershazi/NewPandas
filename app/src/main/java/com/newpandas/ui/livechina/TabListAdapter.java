package com.newpandas.ui.livechina;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.newpandas.base.BaseFragment;

import java.util.ArrayList;

/**
 * Created by Nicky on 2017/7/30.
 */

public class TabListAdapter extends FragmentPagerAdapter {

    private ArrayList<BaseFragment> list;
    private ArrayList<String> titleList;

    public TabListAdapter(FragmentManager fm,ArrayList<BaseFragment> list,ArrayList<String> titleList) {
        super(fm);
        this.list=list;
        this.titleList=titleList;
    }

    @Override
    public Fragment getItem(int position) {
        return list.get(position);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titleList.get(position);
    }
}
