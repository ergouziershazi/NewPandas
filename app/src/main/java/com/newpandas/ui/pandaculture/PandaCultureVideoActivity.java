package com.newpandas.ui.pandaculture;

import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.newpandas.R;
import com.newpandas.base.BaseActivity;
import com.newpandas.model.entity.VideoSet;
import com.newpandas.uitls.VideoUtils;
import com.newpandas.widget.manager.JCPlayVideo;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.OnClick;

public class PandaCultureVideoActivity extends BaseActivity implements CultureVideoContract.View {

    @BindView(R.id.pandaculturevideo_finish)
    ImageView pandaculturevideoFinish;
    @BindView(R.id.jcplayvideo)
    JCPlayVideo jcPlayVideo;
    @BindView(R.id.pandaculturevideo_image)
    ImageView pandaculturevideoImage;
    @BindView(R.id.pandaculturevideo_text)
    TextView pandaculturevideoText;
    @BindView(R.id.visibility_linear)
    LinearLayout visibilityLinear;
    @BindView(R.id.pandaculturevideo_list)
    XRecyclerView pandaculturevideoList;
    @BindView(R.id.pandaculturevideo_collect)
    ImageView pandaculturevideoCollect;
    @BindView(R.id.pandaculturevideo_share)
    ImageView pandaculturevideoShare;
    @BindView(R.id.activity_panda_culture_video)
    RelativeLayout activityPandaCultureVideo;

    private CultureVideoContract.Presenter presenter;
    private ArrayList<VideoSet.VideoBean> videoList;
    private CultureVideoAdapter cultureVideoAdapter;
    boolean flags=false;
    private String desc;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_panda_culture_video;
    }

    @Override
    protected void init() {
        new CultureVideoPresenter(this);
        presenter.playVideo(getIntent().getStringExtra("videoId"));
        videoList=new ArrayList<VideoSet.VideoBean>();
        pandaculturevideoList.setHasFixedSize(true);
        pandaculturevideoList.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        pandaculturevideoList.setLoadingMoreEnabled(false);
        cultureVideoAdapter = new CultureVideoAdapter(this,videoList);
        pandaculturevideoList.setAdapter(cultureVideoAdapter);
        cultureVideoAdapter.notifyDataSetChanged();



        cultureVideoAdapter.setClickListener(new CultureVideoAdapter.onClickListener() {
            @Override
            public void onItemClickListener(int position) {
                VideoUtils.getUtils().playVideo(jcPlayVideo,videoList.get(position).getUrl(),
                        videoList.get(position).getT(),videoList.get(position).getImg());
            }
        });
    }

    @OnClick({R.id.pandaculturevideo_finish, R.id.pandaculturevideo_image, R.id.pandaculturevideo_collect, R.id.pandaculturevideo_share})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.pandaculturevideo_finish:
                finish();
                break;
            case R.id.pandaculturevideo_image:
                int i = 0;
                i = visibilityLinear.getVisibility();
                if (i == 8) {
                    visibilityLinear.setVisibility(View.VISIBLE);
                    pandaculturevideoText.setText(desc);
                    pandaculturevideoImage.setImageResource(R.drawable.lpanda_show);
                } else {
                    visibilityLinear.setVisibility(View.GONE);
                    pandaculturevideoImage.setImageResource(R.drawable.lpanda_off);
                }
                break;
            case R.id.pandaculturevideo_collect:
                if (flags) {
                    pandaculturevideoCollect.setImageResource(R.drawable.collect_no);
                    Toast.makeText(PandaCultureVideoActivity.this, "取消收藏", Toast.LENGTH_SHORT).show();
                    flags = false;
                }else {
                    flags=true;
                    pandaculturevideoCollect.setImageResource(R.drawable.collect_yes);
                    Toast.makeText(PandaCultureVideoActivity.this, "收藏成功", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.pandaculturevideo_share:
                break;
        }
    }

    @Override
    public void setResult(final VideoSet videoSet) {
        desc = videoSet.getVideoset().get_$0().getDesc();
        pandaculturevideoText.setText(desc);
        videoList.addAll(videoSet.getVideo());
        cultureVideoAdapter.notifyDataSetChanged();

        VideoUtils.getUtils().playVideo(jcPlayVideo,videoList.get(0).getUrl(),
                videoList.get(0).getT(),videoList.get(0).getImg());

        pandaculturevideoList.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                videoList.clear();
                videoList.addAll(videoSet.getVideo());

                pandaculturevideoList.refreshComplete();
                cultureVideoAdapter.notifyDataSetChanged();
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
    public void setPresenter(CultureVideoContract.Presenter presenter) {
        this.presenter=presenter;
    }
}
