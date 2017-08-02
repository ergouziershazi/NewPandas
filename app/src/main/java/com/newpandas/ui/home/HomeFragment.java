package com.newpandas.ui.home;

import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.newpandas.R;
import com.newpandas.base.BaseFragment;
import com.newpandas.config.ImageLoaders;
import com.newpandas.model.entity.CcTvForBean;
import com.newpandas.model.entity.LightChinaBean;
import com.newpandas.model.entity.PandaEyeBean;
import com.newpandas.model.entity.PandaHome;
import com.newpandas.model.entity.VideoInfoBean;
import com.newpandas.ui.home.adapter.HomeAdapter;
import com.newpandas.uitls.acache.ACacheUtils;
import com.newpandas.widget.view.CustomDialog;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.Unbinder;

/**
 * Created by yan on 2017/7/28.
 */

public class HomeFragment extends BaseFragment implements HomeContract.view {
    @BindView(R.id.homeRecyclerView)
    XRecyclerView homeRecyclerView;
    Unbinder unbinder;
    List<Object> homeList;
    HomeContract.persenter persenter;
    HomePersenter homePersenter ;
    HomeAdapter adapter;
    List<CcTvForBean.ListBean> cctvlist;
    List<LightChinaBean.ListBean> lightlist;
    List<PandaHome.DataBean.BigImgBean> bigimalist;
    List<PandaEyeBean.ListBean> pandaeyelist;
    ArrayList<String> imagetitelist;
    Banner banner;
    TextView homepageTitle;
    private ArrayList<VideoInfoBean.VideoBean.ChaptersBean> videoList;
    private String duration;
    private int pos,wonderfulPosition,itemPosition,eyePosition,lightChinaPosition;
    @Override
    protected int getLayoutId() {
        return R.layout.home_fragment;
    }
    @Override
    protected void init(View view) {
        homePersenter=new HomePersenter(this);
        homePersenter.start();
        homeList=new ArrayList<>();
        cctvlist=new ArrayList<>();
        lightlist=new ArrayList<>();
        bigimalist=new ArrayList<>();
        imagetitelist=new ArrayList<>();
        pandaeyelist=new ArrayList<>();
        View heardview= LayoutInflater.from(getActivity()).inflate(R.layout.home_page_bigimagebanner,null);
        homeRecyclerView.addHeaderView(heardview);
        banner = (Banner) heardview.findViewById(R.id.home_banner);
        homepageTitle = (TextView) heardview.findViewById(R.id.homepage_title);
    }

    @Override
    protected void loadData() {

    }

    @Override
    public void showHomeListData(final PandaHome pandaHome) {
      CustomDialog.show(getActivity());
      homeList.add(pandaHome.getData().getArea());
      homeList.add(pandaHome.getData().getPandaeye());
      homeList.add(pandaHome.getData().getPandalive());
      homeList.add(pandaHome.getData().getWalllive());
      homeList.add(pandaHome.getData().getChinalive());
      homeList.add(pandaHome.getData().getInteractive());
      homeList.add(pandaHome.getData().getCctv());
      homeList.add(pandaHome.getData().getList());
      bigimalist.addAll(pandaHome.getData().getBigImg());
      homePersenter.loadCCTVData(pandaHome.getData().getCctv().getListurl());
      homePersenter.loadLightChina(pandaHome.getData().getList().get(0).getListUrl());
      homePersenter.loadPandaEye(pandaHome.getData().getPandaeye().getPandaeyelist());
      carousel(pandaHome);
      for(int i=0;i<homeList.size();i++){
        Log.e("homelist",homeList.get(i).toString());
        }
        if(homeList.size()>0){
            adapter=new HomeAdapter(getActivity(),homeList,cctvlist,lightlist,pandaeyelist);
            LinearLayoutManager manager=new LinearLayoutManager(getActivity());
            homeRecyclerView.setLayoutManager(manager);
            manager.setOrientation(LinearLayoutManager.VERTICAL);
            adapter.notifyDataSetChanged();
            homeRecyclerView.setAdapter(adapter);
        }
        adapter.setClickListener(new HomeAdapter.itemClickListener() {
            @Override
            public void onCCTVItemClickListener(int position) {

            }

            @Override
            public void onGreatWallLiveItemClickListener(int position) {

            }

            @Override
            public void onLightChinaItemClickListener(int position) {

            }

            @Override
            public void onLiveInChinaItemClickListener(int position) {

            }

            @Override
            public void onPandaEyeItemClickListener(int position) {
                eyePosition=position;
                homePersenter.pandaWatchResult(pandaeyelist.get(position).getPid());
            }

            @Override
            public void onPandaLiveItemClickListener(int position) {

            }

            @Override
            public void onPandaWatchItemClickListener(int position) {

            }

            @Override
            public void onWonderfulreItemClickListener(int position) {
                wonderfulPosition=position;
                homePersenter.wonderfulResult(pandaHome.getData().getArea().getListscroll().get(wonderfulPosition).getPid());
            }

            @Override
            public void onSpecialPlanItemClickListener(int position) {

            }
        });
    }

    private void carousel(final PandaHome netBean) {

        for (int i = 0; i < netBean.getData().getBigImg().size(); i++) {
            imagetitelist.add(netBean.getData().getBigImg().get(i).getImage());
        }
        if(imagetitelist.size()>0){
            CustomDialog.dimiss();
        }
        banner.setImageLoader(new ImageLoaders());
        banner.isAutoPlay(true);
        banner.setDelayTime(2000);
        banner.setImages(imagetitelist).setIndicatorGravity(BannerConfig.RIGHT);
        banner.start();

//        banner.setOnBannerListener(new OnBannerListener() {
//            @Override
//            public void OnBannerClick(final int position) {
//                if (position == 0) {
//                    Intent intent = new Intent(getActivity(), InteractiveInfoActivity.class);
//                    intent.putParcelableArrayListExtra("bigImgList", bigImgList);
//                    intent.putExtra("position", position);
//                    startActivity(intent);
//                } else {
//                    pos = position;
//                    presenter.rotationResult(netBean.getData().getBigImg().get(position).getPid());
//                }
//            }
//        });

        banner.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if (position <= 4 && position != 0) {
                    homepageTitle.setText(bigimalist.get(position - 1).getTitle());
                } else if (position <= 4 && position <= 0) {
                    position = 0;
                }
            }
            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }



    @Override
    public void showCCTVData(CcTvForBean ccTvForBean) {
      cctvlist.addAll(ccTvForBean.getList());
    }

    @Override
    public void showLightChina(LightChinaBean lightChinaBean) {
        lightlist.addAll(lightChinaBean.getList());

    }

    @Override
    public void showPandaEye(PandaEyeBean pandaEyeBean) {
        pandaeyelist.addAll(pandaEyeBean.getList());
    }

    @Override
    public void setRotationResult(VideoInfoBean videoInfoBean) {
        videoList.addAll(videoInfoBean.getVideo().getChapters());
        duration=videoList.get(0).getDuration();
        StringBuffer buffer=new StringBuffer();
        if(Integer.valueOf(duration)<60){
            buffer.append("00:").append(duration);
        }else{
            duration=(Integer.valueOf(duration)%60)+"";
            buffer.append("01:").append(duration);
        }

//        Intent intent = new Intent(getActivity(), CultureSpActivity.class);
//        intent.putExtra("url", videoList.get(0).getUrl());
//        intent.putExtra("otherurl",videoInfoBean.getVideo().getLowChapters().get(0).getUrl());
//        intent.putExtra("imageurl",list.get(0).getBigImg().get(pos).getImage());
//        intent.putExtra("movietime",duration);
//        intent.putExtra("title", list.get(0).getBigImg().get(pos).getTitle());
//
        SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String date=format.format(new Date());

//        SqlUtils.getInstance()
//                .add(0,list.get(0).getBigImg().get(pos).getImage(),buffer.toString(),list.get(0).getBigImg().get(pos).getTitle(),date,videoList.get(0).getUrl());
//        startActivity(intent);
        ACacheUtils.getUtils().setStorage(videoList.get(0).getUrl(),duration,bigimalist.get(pos).getTitle(),date,videoList.get(0).getUrl(),null);
        videoList.clear();
    }

    @Override
    public void setWonderfulResult(VideoInfoBean videoInfoBean) {
        videoList.addAll(videoInfoBean.getVideo().getChapters());

        duration=videoList.get(0).getDuration();
        StringBuffer buffer=new StringBuffer();
        if(Integer.valueOf(duration)<60){
            buffer.append("00:").append(duration);
        }else{
            duration=(Integer.valueOf(duration)%60)+"";
            buffer.append("01:").append(duration);
        }

//        Intent intent=new Intent(getActivity(), CultureSpActivity.class);
//        intent.putExtra("url",videoList.get(0).getUrl());
//        intent.putExtra("title",scrollList.get(wonderfulPosition).getTitle());
//        intent.putExtra("otherurl",videoInfoBean.getVideo().getLowChapters().get(0).getUrl());
//        intent.putExtra("imageurl",scrollList.get(wonderfulPosition).getImage());
//        intent.putExtra("movietime",duration);
//        SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        String date=format.format(new Date());
//
//        SqlUtils.getInstance()
//                .add(0,scrollList.get(wonderfulPosition).getImage(),buffer.toString(),scrollList.get(wonderfulPosition).getTitle(),date,videoList.get(0).getUrl());
//        getActivity().startActivity(intent);

        SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String date=format.format(new Date());
        ACacheUtils.getUtils().setStorage(videoList.get(0).getUrl(),duration,bigimalist.get(pos).getTitle(),date,videoList.get(0).getUrl(),null);

        videoList.clear();
    }

    @Override
    public void setItemResult(VideoInfoBean videoInfoBean) {
        videoList.addAll(videoInfoBean.getVideo().getChapters());
        duration=videoList.get(0).getDuration();
        StringBuffer buffer=new StringBuffer();
        if(Integer.valueOf(duration)<60){
            buffer.append("00:").append(duration);
        }else{
            duration=(Integer.valueOf(duration)%60)+"";
            buffer.append("01:").append(duration);
        }

//        Intent intent=new Intent(getActivity(), CultureSpActivity.class);
//        intent.putExtra("url",videoList.get(0).getUrl());
//        intent.putExtra("title",itemsList.get(itemPosition).getTitle());
//        intent.putExtra("otherurl",videoInfoBean.getVideo().getLowChapters().get(0).getUrl());
//        intent.putExtra("imageurl","");
//        intent.putExtra("movietime",duration);
        SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String date=format.format(new Date());
//
//        SqlUtils.getInstance()
//                .add(0,itemsList.get(itemPosition).getUrl(),buffer.toString(),itemsList.get(itemPosition).getTitle(),date,videoList.get(0).getUrl());
//        getActivity().startActivity(intent);

        ACacheUtils.getUtils().setStorage(videoList.get(0).getUrl(),duration,bigimalist.get(pos).getTitle(),date,videoList.get(0).getUrl(),null);
        videoList.clear();


    }

    @Override
    public void setPandaWatchResult(VideoInfoBean videoInfoBean) {

    }

    @Override
    public void setLightChinaVideoInfo(VideoInfoBean videoInfoBean) {

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

    public void setPresenter(HomeContract.persenter presenter) {
      this.persenter=presenter;
    }
}
