package com.newpandas.ui.pandaculture;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.newpandas.R;
import com.newpandas.base.BaseActivity;

import butterknife.BindView;
import butterknife.OnClick;

public class PandaCultureVideoActivity extends BaseActivity {

    @BindView(R.id.pandaculturevideo_finish)
    ImageView pandaculturevideoFinish;
    //@BindView(R.id.pandaculturevideo_play)
    //com.example.pandas.config.JCVideoPlayerStandard pandaculturevideoPlay;
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

    @Override
    protected int getLayoutId() {
        return R.layout.activity_panda_culture_video;
    }

    @Override
    protected void init() {

    }

    @OnClick({R.id.pandaculturevideo_finish, R.id.pandaculturevideo_image, R.id.pandaculturevideo_text, R.id.pandaculturevideo_list, R.id.pandaculturevideo_collect, R.id.pandaculturevideo_share})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.pandaculturevideo_finish:
                finish();
                break;
            case R.id.pandaculturevideo_image:
                break;
            case R.id.pandaculturevideo_text:
                break;
            case R.id.pandaculturevideo_list:
                break;
            case R.id.pandaculturevideo_collect:
                break;
            case R.id.pandaculturevideo_share:
                break;
        }
    }
}
