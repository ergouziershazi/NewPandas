package com.newpandas.ui.myself;

import android.widget.ImageButton;
import android.widget.TextView;

import com.newpandas.R;
import com.newpandas.base.BaseActivity;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by 联想 on 2017/7/29.
 */

public class AboutActivity extends BaseActivity {
    @BindView(R.id.about_back)
    ImageButton aboutBack;
    @BindView(R.id.about_version)
    TextView aboutVersion;
    @BindView(R.id.about_content)
    TextView aboutContent;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_about;
    }

    @Override
    protected void init() {

    }


    @OnClick(R.id.about_back)
    public void onViewClicked() {
        finish();
    }
}
