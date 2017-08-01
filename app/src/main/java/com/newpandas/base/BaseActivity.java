package com.newpandas.base;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.newpandas.app.App;

import butterknife.ButterKnife;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayer;

public abstract class BaseActivity extends AppCompatActivity {
// 记录上一个显示的fragment
  private BaseFragment lastfragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        App.context=this;
        setContentView(getLayoutId());
        ButterKnife.bind(this);
        init();
    }

    @Override
    protected void onResume() {
        super.onResume();
        App.context=this;
    }

    protected abstract int getLayoutId();
    protected abstract void init();

    @Override
    protected void onDestroy() {
        super.onDestroy();

    }

    @Override
    protected void onPause() {
        super.onPause();
        JCVideoPlayer.releaseAllVideos();
    }

    @Override
    public void onBackPressed() {
        if (JCVideoPlayer.backPress()) {
            return;
        }
        super.onBackPressed();
    }
}
