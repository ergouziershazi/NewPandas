package com.newpandas.ui.myself;

import android.support.design.widget.TabLayout;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.widget.ImageButton;

import com.newpandas.R;
import com.newpandas.base.BaseActivity;
import com.newpandas.base.BaseFragment;
import com.newpandas.base.ViewpagerAdapter;
import com.newpandas.ui.myself.fragment.RegisterEmailFragment;
import com.newpandas.ui.myself.fragment.RegisterPhoneFragment;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by 联想 on 2017/7/28.
 */

public class RegisterActivity extends BaseActivity {
    @BindView(R.id.register_back)
    ImageButton registerBack;
    @BindView(R.id.register_tablayout)
    TabLayout registerTablayout;
    @BindView(R.id.register_viewpager)
    ViewPager registerViewpager;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_register;
    }

    @Override
    protected void init() {
        ArrayList<BaseFragment> fragments = new ArrayList<BaseFragment>();
        fragments.add(new RegisterPhoneFragment());
        fragments.add(new RegisterEmailFragment());
        ArrayList<String> tabs = new ArrayList<String>();
        tabs.add("手机注册");tabs.add("邮箱注册");
        ViewpagerAdapter viewpagerAdapter = new ViewpagerAdapter(getSupportFragmentManager(),fragments,tabs);
        registerTablayout.setTabTextColors(ContextCompat.getColor(this, R.color.black), ContextCompat.getColor(this, R.color.colorPrimaryDark));
        registerTablayout.setSelectedTabIndicatorColor(ContextCompat.getColor(this,R.color.colorPrimaryDark));
        registerTablayout.setTabMode(TabLayout.MODE_FIXED);
        registerTablayout.setupWithViewPager(registerViewpager);
        registerViewpager.setAdapter(viewpagerAdapter);
        registerViewpager.setOffscreenPageLimit(1);
    }

    @OnClick(R.id.register_back)
    public void onViewClicked() {
        finish();
    }
}
