package com.newpandas.activity.hudong;

import android.content.Intent;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ImageButton;
import android.widget.TextView;

import com.newpandas.R;
import com.newpandas.base.BaseActivity;
import com.newpandas.widget.manager.ToastManager;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by 联想 on 2017/7/30.
 */

public class HuDongWebviewActivity extends BaseActivity {
    @BindView(R.id.hudong_webview_back)
    ImageButton hudongWebviewBack;
    @BindView(R.id.hudong_webview_title)
    TextView hudongWebviewTitle;
    @BindView(R.id.hudong_webview_share)
    ImageButton hudongWebviewShare;
    @BindView(R.id.hudong_webview)
    WebView hudongWebview;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_hudong_webview;
    }

    @Override
    protected void init() {
        Intent intent=getIntent();
        String url=intent.getStringExtra("url");
        String title = intent.getStringExtra("title");
        String imgurl = intent.getStringExtra("imgurl");
        hudongWebviewTitle.setText(title);
        WebSettings settings = hudongWebview.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setUseWideViewPort(true);
        settings.setLoadWithOverviewMode(true);

        hudongWebview.loadUrl(url);
    }


    @OnClick({R.id.hudong_webview_back,R.id.hudong_webview_share})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.hudong_webview_back:
                finish();
                break;
            case R.id.hudong_webview_share:
                ToastManager.showToast("分享成功");
                break;
        }
    }
}
