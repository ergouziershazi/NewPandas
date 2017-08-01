package com.newpandas.ui.pandalive.detilsfragment;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.newpandas.R;
import com.newpandas.base.BaseFragment;
import com.newpandas.model.entity.pandalive.MultipleBean;
import com.newpandas.model.entity.pandalive.PandaEyeTabBean;
import com.newpandas.model.entity.pandalive.SendingBean;
import com.newpandas.ui.pandalive.PandaEyeContract;
import com.newpandas.ui.pandalive.PandaEyePresenter;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by yan on 2017/7/31.
 */

public class LiveFragment extends BaseFragment implements PandaEyeContract.view {

    @BindView(R.id.pandaLive_pandaFirst)
    ImageView pandaLivePandaFirst;
    @BindView(R.id.panda_live_mainTitle)
    TextView pandaLiveMainTitle;
    @BindView(R.id.pandaLive_detailDown)
    ImageView pandaLiveDetailDown;
    @BindView(R.id.pandaLive_detailUp)
    ImageView pandaLiveDetailUp;
    @BindView(R.id.pandaLive_showIntroduction)
    RelativeLayout pandaLiveShowIntroduction;
    @BindView(R.id.pandaLive_introduction)
    TextView pandaLiveIntroduction;
    @BindView(R.id.pandaLive_introductionDV)
    View pandaLiveIntroductionDV;
    @BindView(R.id.panda_live_showLinear)
    LinearLayout pandaLiveShowLinear;
    @BindView(R.id.pandaLive_bookMark_tab)
    TabLayout pandaLiveBookMarkTab;
    @BindView(R.id.pandaLive_mainPager)
    ViewPager pandaLiveMainPager;
    @BindView(R.id.live_main_stick)
    ScrollView liveMainStick;
    PandaEyeContract.persenter persenter;
    PandaEyePresenter pandaEyePresenter;
    Unbinder unbinder2;
    private int singleEven = 1;
    private ArrayList<Fragment> mainLiveFragments;
    private MainLiveAdapter adapter;
    private boolean flg = false;
    private ArrayList<String> list;
    BroadcastReceiver receiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String image_url = intent.getStringExtra("image_url");
            String url = intent.getStringExtra("url");

        }
    };

    @Override
    protected int getLayoutId() {
        return R.layout.pandaeye_live_frag;
    }

    @Override
    protected void init(View view) {
        pandaEyePresenter = new PandaEyePresenter(this);
        pandaEyePresenter.start();
        mainLiveFragments = new ArrayList<>();
        list = new ArrayList<>();
    }

    @Override
    protected void loadData() {
        persenter.start();
        adapter = new MainLiveAdapter(getActivity().getSupportFragmentManager(), mainLiveFragments, list);
        pandaLiveMainPager.setAdapter(adapter);
        pandaLiveBookMarkTab.setupWithViewPager(pandaLiveMainPager);
        pandaLiveBookMarkTab.setTabMode(TabLayout.MODE_FIXED);
        pandaLiveMainPager.setOffscreenPageLimit(2);
        pandaLiveBookMarkTab.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                if (tab.getPosition() == 1) {
                    Intent intent = new Intent();
                    intent.setAction("com.pandas.tag");
                    getActivity().sendBroadcast(intent);
                    ViewGroup.LayoutParams layoutParams = pandaLiveMainPager.getLayoutParams();
                    if (flg) {
                        layoutParams.height = 700;
                        //            让上面的view横向   获取焦点 防止点击时跳到下面gridview  里的 第一条
                        pandaLiveShowIntroduction.setFocusable(true);
                        pandaLiveShowIntroduction.setFocusableInTouchMode(true);
                        pandaLiveShowIntroduction.requestFocus();
                    } else {
                        layoutParams.height = 600;
                        //          把上面的tablayout设为焦点 防止 直接显示第一条
                        pandaLiveBookMarkTab.setFocusable(true);
                        pandaLiveBookMarkTab.setFocusableInTouchMode(true);
                        pandaLiveBookMarkTab.requestFocus();
                    }
                    pandaLiveMainPager.setLayoutParams(layoutParams);
                } else if (tab.getPosition() == 0) {
                    ViewGroup.LayoutParams layoutParams = pandaLiveMainPager.getLayoutParams();
                    layoutParams.height = 1800;
                    if (flg) {
                        //            让上面的view横向   获取焦点 防止点击时跳到下面gridview  里的 第一条
                        pandaLiveShowIntroduction.setFocusable(true);
                        pandaLiveShowIntroduction.setFocusableInTouchMode(true);
                        pandaLiveShowIntroduction.requestFocus();

                    } else {
                        //          把上面的tablayout设为焦点 防止 直接显示第一条
                        pandaLiveBookMarkTab.setFocusable(true);
                        pandaLiveBookMarkTab.setFocusableInTouchMode(true);
                        pandaLiveBookMarkTab.requestFocus();
                    }
                    pandaLiveMainPager.setLayoutParams(layoutParams);
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }


    @Override
    public void showTabTitles(PandaEyeTabBean pandaEyeTabBean) {

    }

    @Override
    public void setMultipleBean(MultipleBean bean) {

    }

    @Override
    public void setPandaLiveDate(SendingBean bean) {
        Glide.with(getActivity()).load(bean.getLive().get(0).getImage()).into(pandaLivePandaFirst);
        pandaLiveMainTitle.setText(bean.getLive().get(0).getTitle());
        pandaLiveIntroduction.setText(bean.getLive().get(0).getBrief());

        MultiAngleFragment multiAngleFragment = new MultiAngleFragment();
        new PandaEyePresenter(multiAngleFragment);

        WatchChatFragment watchChatFragment = new WatchChatFragment();

        list.add(bean.getBookmark().getMultiple().get(0).getTitle());
        list.add(bean.getBookmark().getWatchTalk().get(0).getTitle());

        mainLiveFragments.add(multiAngleFragment);
        mainLiveFragments.add(watchChatFragment);

        adapter.notifyDataSetChanged();
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
        this.persenter = persenter;
    }



    @OnClick(R.id.pandaLive_showIntroduction)
    public void onViewClicked() {
        if (singleEven % 2 != 0) {
//            展开详情
            pandaLiveDetailDown.setVisibility(View.GONE);
            pandaLiveDetailUp.setVisibility(View.VISIBLE);
            pandaLiveShowLinear.setVisibility(View.VISIBLE);
            singleEven++;
            flg = true;


        } else {
//            关闭详情
            pandaLiveDetailDown.setVisibility(View.VISIBLE);
            pandaLiveDetailUp.setVisibility(View.GONE);
            pandaLiveShowLinear.setVisibility(View.GONE);
            singleEven++;
            flg = false;

//          把上面的tablayout设为焦点 防止 直接显示第一条
            pandaLiveBookMarkTab.setFocusable(true);
            pandaLiveBookMarkTab.setFocusableInTouchMode(true);
            pandaLiveBookMarkTab.requestFocus();
        }
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        getActivity().unregisterReceiver(receiver);
    }
}
