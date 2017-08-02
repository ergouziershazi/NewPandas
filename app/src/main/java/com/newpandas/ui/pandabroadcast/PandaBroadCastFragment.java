package com.newpandas.ui.pandabroadcast;

import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.jcodecraeer.xrecyclerview.ProgressStyle;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.newpandas.R;
import com.newpandas.base.BaseFragment;
import com.newpandas.model.entity.PandaBroadcastListBean;
import com.newpandas.model.entity.PandabroadcastBean;
import com.newpandas.model.entity.PbVideoBean;
import com.newpandas.net.HttpFactory;
import com.newpandas.widget.manager.JCPlayVideo;
import com.newpandas.widget.manager.ToastManager;
import com.newpandas.widget.view.CustomDialog;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.Unbinder;

/**
 * Created by yan on 2017/7/28.
 */

public class PandaBroadCastFragment extends BaseFragment implements PandabroadCastContract.view ,pandabroadcastAdapter.OnClick{
    @BindView(R.id.pb_xRecyclerview)
    XRecyclerView pbXRecyclerview;
    Unbinder unbinder;
    private PandabroadCastContract.persenter persenter;
    private ImageView headerImg;
    private TextView headertitle;
    private com.newpandas.ui.pandabroadcast.pandabroadcastAdapter pandabroadcastAdapter;
    private List<PandaBroadcastListBean.ListBean> listbean;
    private String listurl;
    private int page=1;

    @Override
    protected int getLayoutId() {
        return R.layout.pandabroadcast_fragment;
    }

    @Override
    protected void init(View view) {
        new PandabroadcastPresenter(this);
        persenter.start();
    }

    @Override
    protected void loadData() {
        View headerview = View.inflate(getActivity(), R.layout.pandabroadcast_header, null);
        headerImg = (ImageView) headerview.findViewById(R.id.pdb_header_image);
        headertitle = (TextView) headerview.findViewById(R.id.pdb_header_title);
        pbXRecyclerview.addHeaderView(headerview);
        listbean = new ArrayList<PandaBroadcastListBean.ListBean>();

        pandabroadcastAdapter = new pandabroadcastAdapter(getActivity(), R.layout.pandaculture_item, listbean);
        pandabroadcastAdapter.setOnClick(this);
        pbXRecyclerview.setLayoutManager(new LinearLayoutManager(getActivity()));
        pbXRecyclerview.setAdapter(pandabroadcastAdapter);
        pbXRecyclerview.setHasFixedSize(true);
//        pbXRecyclerview.setNoMore(true);
        pbXRecyclerview.setLoadingMoreEnabled(true);
        pbXRecyclerview.setLoadingMoreProgressStyle(ProgressStyle.SquareSpin);
        pbXRecyclerview.setArrowImageView(R.drawable.xlistview_arrow);
        pbXRecyclerview.setRefreshProgressStyle(ProgressStyle.BallSpinFadeLoader);
        pbXRecyclerview.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                persenter.start();
                pbXRecyclerview.refreshComplete();
            }

            @Override
            public void onLoadMore() {
                page++;
                persenter.loadmore(listurl,page);
//                pbXRecyclerview.setLoadingMoreEnabled(true);


            }
        });
    }

    @Override
    public void showheaderBean(PandabroadcastBean pandabroadcaseBean) {
        if (pandabroadcaseBean != null) {
            listurl=pandabroadcaseBean.getData().getListurl();
            persenter.initListData(listurl);
            headertitle.setText(pandabroadcaseBean.getData().getBigImg().get(0).getTitle());
            HttpFactory.create().loadImage(pandabroadcaseBean.getData().getBigImg().get(0).getImage(),headerImg);
        }

    }

    @Override
    public void showListBean(PandaBroadcastListBean pandaBroadcastListBean) {
        listbean.clear();
        if(pandaBroadcastListBean!=null){
            List<PandaBroadcastListBean.ListBean> list = pandaBroadcastListBean.getList();
            listbean.addAll(list);
            pandabroadcastAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void showLoadMoreBean(PandaBroadcastListBean pandaBroadcastListBean) {
        if(pandaBroadcastListBean!=null){
            List<PandaBroadcastListBean.ListBean> list = pandaBroadcastListBean.getList();
            listbean.addAll(list);
            pandabroadcastAdapter.notifyDataSetChanged();
        }
        pbXRecyclerview.loadMoreComplete();
    }

    @Override
    public void showVideoBean(PbVideoBean pbVideoBean) {
        String url = pbVideoBean.getVideo().getChapters().get(0).getUrl();
        JCPlayVideo.startFullscreen(getActivity(),JCPlayVideo.class,url,pbVideoBean.getTitle());
    }

    @Override
    public void showProgress() {
        CustomDialog.show(getActivity());
    }

    @Override
    public void dimissProgress() {
        CustomDialog.dimiss();
    }

    @Override
    public void showMessage(String msg) {
        ToastManager.showToast(msg);
    }

    @Override
    public void setPresenter(PandabroadCastContract.persenter persenter) {
        this.persenter = persenter;
    }

    @Override
    public void onClickListener(String pid) {
        persenter.loadvideo(pid);
    }
}
