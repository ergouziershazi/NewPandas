package com.newpandas.ui.myself;

import android.content.Intent;
import android.content.SharedPreferences;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.newpandas.R;
import com.newpandas.base.BaseActivity;
import com.newpandas.uitls.MyLogs;
import com.newpandas.widget.manager.SharedPreferencesManager;

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
    @BindView(R.id.title_name)
    TextView titleName;
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

    @Override
    protected void onStart() {
        super.onStart();
        SharedPreferences spf = SharedPreferencesManager.sharedPreferences;
        String userName = spf.getString("userName", "点击登录");
        String userIcon = spf.getString("userIcon", "");
        MyLogs.d("TGA", userName + "-----------" + userIcon);
        titleName.setText(userName);
        if (!userIcon.equals("")) {
            Glide.with(this).load(userIcon).into(image1);
        } else {
            image1.setImageResource(R.drawable.personal_login_head);
        }
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
