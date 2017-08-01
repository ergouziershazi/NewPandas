package com.newpandas.ui.myself;

import android.content.Intent;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.newpandas.R;
import com.newpandas.base.BaseActivity;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by 联想 on 2017/7/28.
 */

public class SettingActivity extends BaseActivity {
    @BindView(R.id.setting_back)
    ImageButton settingBack;
    @BindView(R.id.setting_message)
    CheckBox settingMessage;
    @BindView(R.id.setting_next)
    CheckBox settingNext;
    @BindView(R.id.setting_cache)
    TextView settingCache;
    @BindView(R.id.setting_help)
    LinearLayout settingHelp;
    @BindView(R.id.setting_upgrade)
    LinearLayout settingUpgrade;
    @BindView(R.id.setting_like)
    LinearLayout settingLike;
    @BindView(R.id.setting_about)
    LinearLayout settingAbout;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_setting;
    }

    @Override
    protected void init() {

    }


    @OnClick({R.id.setting_back, R.id.setting_message, R.id.setting_next, R.id.setting_cache, R.id.setting_help, R.id.setting_upgrade, R.id.setting_like, R.id.setting_about})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.setting_back:
                finish();
                break;
            case R.id.setting_message:
                break;
            case R.id.setting_next:
                break;
            case R.id.setting_cache:
                break;
            case R.id.setting_help:
                startActivity(new Intent(this,FeedbackActivity.class));
                break;
            case R.id.setting_upgrade:
                break;
            case R.id.setting_like:
                break;
            case R.id.setting_about:
                startActivity(new Intent(this,AboutActivity.class));
                break;
        }
    }
}
