package com.newpandas.ui.livechina;

import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.newpandas.R;
import com.newpandas.base.BaseFragment;
import com.newpandas.model.entity.LiveChina;
import com.newpandas.model.entity.LiveInfo;

import java.util.ArrayList;

import butterknife.BindView;

/**
 * Created by Nicky on 2017/7/30.
 */

public class TabPageFragment extends BaseFragment implements LiveChinaContract.View {
    @BindView(R.id.livechina_info_xrecyclerview)
    XRecyclerView livechinaInfoXrecyclerview;

    private LiveChinaContract.Presenter presenter;
    String s;
    private ArrayList<LiveInfo.LiveBean> liveList;
    private TabPageInfoAdapter tabPageInfoAdapter;

    @Override
    protected int getLayoutId() {
        return R.layout.livechina_info_fragment;
    }

    @Override
    protected void init(View view) {
        liveList=new ArrayList<LiveInfo.LiveBean>();
        livechinaInfoXrecyclerview.setHasFixedSize(true);
        livechinaInfoXrecyclerview.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false));
        tabPageInfoAdapter=new TabPageInfoAdapter(getActivity(),liveList);
        livechinaInfoXrecyclerview.setLoadingMoreEnabled(false);
        livechinaInfoXrecyclerview.setAdapter(tabPageInfoAdapter);
        tabPageInfoAdapter.notifyDataSetChanged();
    }

    @Override
    protected void loadData() {
        presenter.onLoadData(s);
    }

    @Override
    public void setResult(LiveChina liveChina) {

    }

    @Override
    public void setInfoResult(LiveInfo liveInfo) {
        liveList.addAll(liveInfo.getLive());
        tabPageInfoAdapter.notifyDataSetChanged();
        livechinaInfoXrecyclerview.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                livechinaInfoXrecyclerview.refreshComplete();
                tabPageInfoAdapter.notifyDataSetChanged();
            }

            @Override
            public void onLoadMore() {

            }
        });
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
    public void setPresenter(LiveChinaContract.Presenter presenter) {
        this.presenter=presenter;
    }
}
