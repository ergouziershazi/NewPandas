package com.newpandas.ui.pandalive;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.newpandas.R;
import com.newpandas.base.BaseFragment;
import com.newpandas.model.entity.pandalive.MultipleBean;
import com.newpandas.model.entity.pandalive.PandaEyeTabBean;
import com.newpandas.model.entity.pandalive.SendingBean;
import com.newpandas.ui.pandalive.detilsfragment.LiveFragment;
import com.newpandas.ui.pandalive.detilsfragment.OtherFragment;
import com.newpandas.ui.pandalive.detilsfragment.OtherPresenter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.Unbinder;

/**
 * Created by yan on 2017/7/28.
 */
public class PandaLiveFragment extends BaseFragment implements PandaEyeContract.view {
    List<PandaEyeTabBean.TablistBean> tablistBeen;
    @BindView(R.id.panda_eye_tablayout)
    TabLayout pandaEyeTablayout;
    Unbinder unbinder;
    @BindView(R.id.pandaeye_vp)
    ViewPager pandaeyeVp;
    ArrayList<Fragment> fragments;
    private ArrayList<String> strings;
    PandaEyeContract.persenter persenter;
    PandaEyePresenter pandaEyePresenter;
    OutVpAdapter adapter;

    @Override
    protected int getLayoutId() {
        return R.layout.pandalive_fragment;
    }

    @Override
    protected void init(View view) {
        pandaEyePresenter=new PandaEyePresenter(this);
        tablistBeen = new ArrayList<>();
        fragments = new ArrayList<>();
        strings = new ArrayList<>();
        adapter = new OutVpAdapter(getActivity().getSupportFragmentManager(), fragments, strings);
        pandaeyeVp.setAdapter(adapter);
        pandaEyeTablayout.setupWithViewPager(pandaeyeVp);
        pandaEyeTablayout.setTabMode(TabLayout.MODE_SCROLLABLE);
    }

    @Override
    protected void loadData() {
        persenter.start();
    }

    @Override
    public void showTabTitles(PandaEyeTabBean pandaEyeTabBean) {
        tablistBeen.addAll(pandaEyeTabBean.getTablist());
        pandaeyeVp.setOffscreenPageLimit(tablistBeen.size());
        if (tablistBeen.size() > 0) {
            for (int i = 0; i < tablistBeen.size(); i++) {
                if (i == 0) {
                    LiveFragment liveFragment = new LiveFragment();
                    new PandaEyePresenter(liveFragment);
                    fragments.add(0, liveFragment);
                    strings.add(0, tablistBeen.get(0).getTitle());
                } else {
                    OtherFragment otherFragment = new OtherFragment();
                    Bundle bundle = new Bundle();
                    new OtherPresenter(otherFragment);
                    bundle.putString("vsid", tablistBeen.get(i).getId());
                    otherFragment.setArguments(bundle);
                    fragments.add(otherFragment);
                    strings.add(tablistBeen.get(i).getTitle());
                }
            }
            adapter.notifyDataSetChanged();
            pandaeyeVp.setOffscreenPageLimit(tablistBeen.size());
        }

    }

    @Override
    public void setMultipleBean(MultipleBean bean) {

    }

    @Override
    public void setPandaLiveDate(SendingBean bean) {

    }

    @Override
    public void showProgress() {

    }

    @Override
    public void dimissProgress() {

    }

    @Override
    public void showMessage(String msg) {

    }

    @Override
    public void setPresenter(PandaEyeContract.persenter persenter) {
      this.persenter= persenter;
    }

}