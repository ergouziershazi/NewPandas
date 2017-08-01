package com.newpandas.ui.pandalive.detilsfragment;

import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;

import com.jcodecraeer.xrecyclerview.ProgressStyle;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.newpandas.R;
import com.newpandas.base.BaseFragment;
import com.newpandas.model.entity.pandalive.OtherDetilsBean;
import com.newpandas.model.entity.pandalive.PlayBean;
import com.newpandas.model.sqlite.SqlUtils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.Unbinder;

/**
 * Created by yan on 2017/7/31.
 */

public class OtherFragment extends BaseFragment implements OtherContract.view,OtherTabAdapter.OnclickGoPlay{
    @BindView(R.id.other_xrecycler)
    XRecyclerView otherXrecycler;
    Unbinder unbinder;
    OtherContract.presenter presenter;
    OtherPresenter otherPresenter;
    private List<OtherDetilsBean.VideoBean> otherTabDetails;
    private OtherTabAdapter otherTabAdapter;
    private String vsid;
    private int p = 1;
    private String chapters;
    private String lowChapters;

    @Override
    protected int getLayoutId() {
        return R.layout.pandalive_otherfrag;
    }

    @Override
    protected void init(View view) {
     otherPresenter=new OtherPresenter(this);
        otherTabDetails = new ArrayList<>();
        presenter.start();
    }

    @Override
    protected void loadData() {
        vsid = getArguments().getString("vsid");

        presenter.getOtherTab(vsid, "7", "panda", "desc", "time", String.valueOf(p));

        otherXrecycler.setLayoutManager(new LinearLayoutManager(getActivity()));
        otherTabAdapter = new OtherTabAdapter(getActivity(), otherTabDetails);
        otherTabAdapter.setOnclickGoPlay(this);
        otherXrecycler.setAdapter(otherTabAdapter);
        otherXrecycler.setRefreshProgressStyle(ProgressStyle.BallSpinFadeLoader);
        otherXrecycler.setLoadingMoreProgressStyle(ProgressStyle.Pacman);
        View foot = LayoutInflater.from(getActivity()).inflate(R.layout.pandalive_other_footer_item, null);
        otherXrecycler.setFootView(foot);
        otherXrecycler.setPullRefreshEnabled(true);
        otherXrecycler.setLoadingMoreEnabled(true);
        otherXrecycler.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                otherXrecycler.refreshComplete();
                otherTabDetails.clear();
                presenter.getOtherTab(vsid, "7", "panda", "desc", "time", "1");
                otherTabAdapter.notifyDataSetChanged();
            }

            @Override
            public void onLoadMore() {

                otherXrecycler.loadMoreComplete();
                presenter.getOtherTab(vsid, "7", "panda", "desc", "time", String.valueOf(p));
                otherTabAdapter.notifyDataSetChanged();
                p++;
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
    public void setPresenter(OtherContract.presenter presenter) {
        this.presenter=presenter;
    }


    @Override
    public void palyvideo(PlayBean playBean) {

    }

    @Override
    public void loadMore(OtherDetilsBean otherDetilsBean) {
        otherTabDetails.addAll(otherDetilsBean.getVideo());
        otherTabAdapter.notifyDataSetChanged();

    }


    @Override
    public void goPlay(int pos) {
        presenter.getPlayVideoData(otherTabDetails.get(pos).getVid());
//        if (chapters != null) {
//            Intent intent = new Intent(getActivity(), CultureSpActivity.class);
//            intent.putExtra("title", otherTabDetails.get(pos).getT());
//            intent.putExtra("url", chapters);
//            intent.putExtra("imageurl",otherTabDetails.get(pos).getImg());
//            intent.putExtra("movietime",otherTabDetails.get(pos).getLen());
//            intent.putExtra("otherurl",lowChapters);
//            getActivity().startActivity(intent);
//        }
        SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String date=format.format(new Date());
        SqlUtils.getInstance().add(0,otherTabDetails.get(pos).getImg(),otherTabDetails.get(pos).getLen(),otherTabDetails.get(pos).getT(),date,chapters);
    }

}
