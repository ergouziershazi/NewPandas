package com.newpandas.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;

import com.newpandas.R;
import com.newpandas.activity.hudong.HuDongActivity;
import com.newpandas.base.BaseActivity;
import com.newpandas.base.BaseFragment;
import com.newpandas.ui.home.HomeFragment;
import com.newpandas.ui.livechina.LiveChinaContract;
import com.newpandas.ui.livechina.LiveChinaFragment;
import com.newpandas.ui.livechina.LiveChinaPresenter;
import com.newpandas.ui.myself.MineActivity;
import com.newpandas.ui.pandabroadcast.PandaBroadCastFragment;
import com.newpandas.ui.pandaculture.PandaCultureContract;
import com.newpandas.ui.pandaculture.PandaCultureFragment;
import com.newpandas.ui.pandaculture.PandaCulturePresenter;
import com.newpandas.ui.pandalive.PandaEyeContract;
import com.newpandas.ui.pandalive.PandaEyePresenter;
import com.newpandas.ui.pandalive.PandaLiveFragment;
import com.newpandas.widget.manager.ToastManager;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.newpandas.widget.manager.FragmentBiulder.changeFragment;

public class MainActivity extends BaseActivity {

    @BindView(R.id.iconImg)
    ImageView iconImg;
    @BindView(R.id.personImg)
    ImageView personImg;
    @BindView(R.id.titleTv)
    TextView titleTv;
    @BindView(R.id.hudongImg)
    ImageView hudongImg;
    @BindView(R.id.homePage)
    RadioButton homePage;
    @BindView(R.id.homePandaLive)
    RadioButton homePandaLive;
    @BindView(R.id.homeRollVideo)
    RadioButton homeRollVideo;
    @BindView(R.id.homePandaBroadcast)
    RadioButton homePandaBroadcast;
    @BindView(R.id.homeLiveChina)
    RadioButton homeLiveChina;
    private long lastTime;//上一次点击back键的时间毫秒数
    public static final int HOMETYPE = 1;
    FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void init() {

        HomeFragment homeFragment = (HomeFragment) changeFragment(HomeFragment.class, R.id.container, true, null, false);

    }

    @OnClick({R.id.iconImg, R.id.personImg, R.id.titleTv, R.id.hudongImg, R.id.homePage, R.id.homePandaLive, R.id.homeRollVideo, R.id.homePandaBroadcast, R.id.homeLiveChina})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iconImg:
                break;
            case R.id.personImg:
                startActivity(new Intent(this,MineActivity.class));
                break;
            case R.id.titleTv:
                break;
            case R.id.hudongImg:
                startActivity(new Intent(this, HuDongActivity.class));
                break;
            case R.id.homePage:
                showTitle(null, HOMETYPE);
                changeFragment(HomeFragment.class, R.id.container, true, null, false);
                break;
            case R.id.homePandaLive:
                showTitle("熊猫直播", 0);
                BaseFragment pandalivefrag=changeFragment(PandaLiveFragment.class, R.id.container, true, null, false);
                new PandaEyePresenter((PandaEyeContract.view) pandalivefrag);
                break;
            case R.id.homeRollVideo:
                showTitle("熊猫文化", 0);
                BaseFragment pandaCultureFragment = changeFragment(PandaCultureFragment.class, R.id.container, true, null, false);
                new PandaCulturePresenter((PandaCultureContract.View) pandaCultureFragment);
                break;
            case R.id.homePandaBroadcast:
                showTitle("熊猫观察", 0);
                changeFragment(PandaBroadCastFragment.class, R.id.container, true, null, false);
                break;
            case R.id.homeLiveChina:
                showTitle("直播中国", 0);
                BaseFragment LiveChinaFragment = changeFragment(LiveChinaFragment.class, R.id.container, true, null, false);
                new LiveChinaPresenter((LiveChinaContract.View) LiveChinaFragment);
                break;
        }
    }

    private void showTitle(String title, int type) {
        if (type == HOMETYPE) {
            iconImg.setVisibility(View.VISIBLE);
            titleTv.setVisibility(View.GONE);
            hudongImg.setVisibility(View.VISIBLE);
        } else {
            titleTv.setText(title);
            iconImg.setVisibility(View.GONE);
            titleTv.setVisibility(View.VISIBLE);
            hudongImg.setVisibility(View.GONE);
        }
    }

    @Override
    public void onBackPressed() {

        if (System.currentTimeMillis() - lastTime < 2000) {
            finish();
        } else {
            ToastManager.showToast("再按一次退出应用");
            lastTime = System.currentTimeMillis();
        }

    }
}
