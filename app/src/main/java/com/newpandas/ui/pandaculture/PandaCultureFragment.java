package com.newpandas.ui.pandaculture;

import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.jcodecraeer.xrecyclerview.ProgressStyle;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.newpandas.R;
import com.newpandas.base.BaseFragment;
import com.newpandas.config.ImageLoaders;
import com.newpandas.model.entity.CultureVideo;
import com.newpandas.model.entity.PandaCulture;
import com.newpandas.uitls.MyLogs;
import com.newpandas.uitls.acache.ACacheUtils;
import com.newpandas.widget.manager.JCPlayVideo;
import com.newpandas.widget.view.CustomDialog;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.listener.OnBannerListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import butterknife.BindView;

/**
 * Created by yan on 2017/7/28.
 */

public class PandaCultureFragment extends BaseFragment implements PandaCultureContract.View {
    @BindView(R.id.culture_xrecyclerview)
    XRecyclerView cultureXrecyclerview;

    private PandaCultureContract.Presenter presenter;
    private ArrayList<PandaCulture.ListBean> dataList;
    private ArrayList<PandaCulture.BigImgBean> bigImgList;
    private ArrayList<String> imgList;
    private PandaCultureAdapter cultureAdapter;
    private TextView pandaCultureCarouselTitle;
    private Banner banner;
    private int pos;

    @Override
    protected int getLayoutId() {
        return R.layout.pandaculture_fragment;
    }

    @Override
    protected void init(View view) {
        presenter.start();
        dataList=new ArrayList<PandaCulture.ListBean>();
        bigImgList=new ArrayList<PandaCulture.BigImgBean>();
        imgList=new ArrayList<String>();

        cultureXrecyclerview.setHasFixedSize(true);
        cultureXrecyclerview.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false));
        cultureXrecyclerview.setLoadingMoreEnabled(false);
        cultureXrecyclerview.setRefreshProgressStyle(ProgressStyle.BallSpinFadeLoader);
        cultureXrecyclerview.setArrowImageView(R.drawable.xlistview_arrow);
        cultureAdapter = new PandaCultureAdapter(dataList,getActivity());
        cultureXrecyclerview.setAdapter(cultureAdapter);
    }

    @Override
    protected void loadData() {
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.pandaculture_carousel_item, null);
        cultureXrecyclerview.addHeaderView(view);
        banner = (Banner) view.findViewById(R.id.pandaculture_banner);
        pandaCultureCarouselTitle = (TextView) view.findViewById(R.id.pandaculture_carousel_title);
        cultureAdapter.setClickListener(new PandaCultureAdapter.onClickListener() {
            @Override
            public void onItemClickListener(int position) {
                pos = position;
                if(position==0){
                    Intent intent=new Intent(getActivity(),PandaCultureVideoActivity.class);
                    intent.putExtra("videoId",dataList.get(position).getId());
                    startActivity(intent);
                }else{
                    presenter.getCultureVideo(dataList.get(position).getId());
                }
            }
        });
    }

    @Override
    public void setResult(final PandaCulture pandaCulture) {
        dataList.addAll(pandaCulture.getList());
        bigImgList.addAll(pandaCulture.getBigImg());

        carousel();

        cultureXrecyclerview.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        dataList.clear();
                        bigImgList.clear();
                        imgList.clear();

                        dataList.addAll(pandaCulture.getList());
                        bigImgList.addAll(pandaCulture.getBigImg());
                        carousel();

                        cultureXrecyclerview.refreshComplete();
                        cultureAdapter.notifyDataSetChanged();
                    }
                });
            }

            @Override
            public void onLoadMore() {

            }
        });
    }

    @Override
    public void setVideoInfo(CultureVideo videoInfo) {
        SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String date = format.format(new Date());
        ACacheUtils.getUtils().setStorage(dataList.get(pos).getImage(),
                dataList.get(pos).getVideoLength(),dataList.get(pos).getTitle(),
                date,videoInfo.getVideo().getChapters().get(0).getUrl(),
                videoInfo.getVideo().getLowChapters().get(0).getUrl());
        JCPlayVideo.startFullscreen(getActivity(),JCPlayVideo.class,
                videoInfo.getVideo().getChapters().get(0).getUrl(),
                dataList.get(pos).getTitle());
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
        MyLogs.d("PandaCultureFragment",msg.toString());
    }

    @Override
    public void setPresenter(PandaCultureContract.Presenter presenter) {
        this.presenter=presenter;
    }

    private void carousel() {
        for (int i = 0; i < bigImgList.size(); i++) {
            imgList.add(bigImgList.get(i).getImage());
        }
        banner.setImageLoader(new ImageLoaders());
        banner.isAutoPlay(true);
        banner.setDelayTime(2000);
        banner.setImages(imgList).setIndicatorGravity(BannerConfig.RIGHT);
        banner.start();

        banner.setOnBannerListener(new OnBannerListener() {
            @Override
            public void OnBannerClick(final int position) {
                Intent intent = new Intent(getActivity(), CarouselWebViewActivity.class);
                intent.putExtra("url", bigImgList.get(position).getUrl());
                startActivity(intent);
            }
        });

        banner.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if (position <= 4 && position != 0) {
                    pandaCultureCarouselTitle.setText(bigImgList.get(position - 1).getTitle());
                } else if (position <= 4 && position <= 0) {
                    position = 0;
                }

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }
}
