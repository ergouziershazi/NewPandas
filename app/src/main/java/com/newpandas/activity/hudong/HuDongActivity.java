package com.newpandas.activity.hudong;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageButton;

import com.newpandas.R;
import com.newpandas.base.BaseActivity;
import com.newpandas.model.entity.HudongListBean;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by 联想 on 2017/7/30.
 */

public class HuDongActivity extends BaseActivity implements HudongContract.view,HudongRviewAdapter.OnClickLisenter{
    @BindView(R.id.hudong_back)
    ImageButton hudongBack;
    @BindView(R.id.hudong_recyclerview)
    RecyclerView hudongRecyclerview;
    private HudongContract.presenter presenter;
    private ArrayList<HudongListBean.InteractiveBean> interactiveBeen;
    private HudongRviewAdapter hudongRviewAdapter;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_hudong;
    }

    @Override
    protected void init() {
        new Hudongpresenter(this);
        presenter.start();
        hudongRecyclerview.setLayoutManager(new LinearLayoutManager(this));
        interactiveBeen = new ArrayList<>();
        hudongRviewAdapter = new HudongRviewAdapter(this,R.layout.hudong_item,interactiveBeen);
        hudongRecyclerview.setAdapter(hudongRviewAdapter );
        hudongRviewAdapter.setOnClickLisenter(this);
    }

    @OnClick(R.id.hudong_back)
    public void onViewClicked() {
        finish();
    }

    @Override
    public void showListBean(HudongListBean hudongListBean) {
        if(hudongListBean!=null){
            List<HudongListBean.InteractiveBean> listbean = hudongListBean.getInteractive();
            interactiveBeen.addAll(listbean);
            hudongRviewAdapter.notifyDataSetChanged();
        }
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
    public void setPresenter(HudongContract.presenter presenter) {
        this.presenter=presenter;
    }

    @Override
    public void setOnWebView(String url, String title, String imgurl) {
        Intent intent = new Intent(this, HuDongWebviewActivity.class);
        intent.putExtra("url",url);
        intent.putExtra("title",title);
        intent.putExtra("imgurl",imgurl);
        startActivity(intent);
    }
}
