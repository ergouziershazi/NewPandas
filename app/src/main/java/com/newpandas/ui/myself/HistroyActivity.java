package com.newpandas.ui.myself;

import android.widget.ImageButton;

import com.newpandas.R;
import com.newpandas.base.BaseActivity;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by 联想 on 2017/7/29.
 */

public class HistroyActivity extends BaseActivity {
    @BindView(R.id.histroy_back)
    ImageButton histroyBack;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_histroy;
    }

    @Override
    protected void init() {

    }


    @OnClick(R.id.histroy_back)
    public void onViewClicked() {
        finish();
    }
}
