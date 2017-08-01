package com.newpandas.ui.myself;

import android.content.Intent;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.newpandas.R;
import com.newpandas.base.BaseActivity;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by 联想 on 2017/7/28.
 */

public class MineActivity extends BaseActivity {
    @BindView(R.id.mine_back)
    ImageButton mineBack;
    @BindView(R.id.titleTv)
    TextView titleTv;
    @BindView(R.id.image1)
    ImageView image1;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.mine_login)
    LinearLayout mineLogin;
    @BindView(R.id.mine_history)
    LinearLayout mineHistory;
    @BindView(R.id.mine_collection)
    LinearLayout mineCollection;
    @BindView(R.id.mine_setting)
    LinearLayout mineSetting;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_mine;
    }

    @Override
    protected void init() {

    }


    @OnClick({R.id.mine_back, R.id.mine_login, R.id.mine_history, R.id.mine_collection, R.id.mine_setting})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.mine_back:
                finish();
                break;
            case R.id.mine_login:
                startActivity(new Intent(this,LoginActivity.class));
                break;
            case R.id.mine_history:
                startActivity(new Intent(this,HistroyActivity.class));
                break;
            case R.id.mine_collection:
                startActivity(new Intent(this,CollectionActivity.class));
                break;
            case R.id.mine_setting:
                startActivity(new Intent(this,SettingActivity.class));
                break;
        }
    }
}
