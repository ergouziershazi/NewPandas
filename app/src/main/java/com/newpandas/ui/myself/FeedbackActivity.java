package com.newpandas.ui.myself;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.widget.ImageButton;

import com.newpandas.R;
import com.newpandas.base.BaseActivity;
import com.newpandas.base.BaseFragment;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by 联想 on 2017/7/29.
 */

public class FeedbackActivity extends BaseActivity {
    @BindView(R.id.feedback_back)
    ImageButton feedbackBack;
    @BindView(R.id.feedback_tablayout)
    TabLayout feedbackTablayout;
    @BindView(R.id.feedback_viewpager)
    ViewPager feedbackViewpager;
    private ArrayList<String> tabs;
    private ArrayList<BaseFragment> fragments;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_feedback;
    }

    @Override
    protected void init() {
        initdata();

    }

    private void initdata() {
        tabs = new ArrayList<>();
        fragments = new ArrayList<>();
        tabs.add("遇到的问题");tabs.add("常见问题");
    }


    @OnClick(R.id.feedback_back)
    public void onViewClicked() {
        finish();
    }
}
