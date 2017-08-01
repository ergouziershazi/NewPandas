package com.newpandas.ui.pandaculture;

import android.content.Intent;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ImageView;
import com.newpandas.R;
import com.newpandas.base.BaseActivity;

import butterknife.BindView;
import butterknife.OnClick;

public class CarouselWebViewActivity extends BaseActivity {

    @BindView(R.id.culture_finish)
    ImageView cultureFinish;
    @BindView(R.id.culture_webview)
    WebView cultureWebview;
    private WebSettings settings;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_carousel_web_view;
    }

    @Override
    protected void init() {

        Intent intent=getIntent();
        String url=intent.getStringExtra("url");

        settings = cultureWebview.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setUseWideViewPort(true);
        settings.setLoadWithOverviewMode(true);

        cultureWebview.loadUrl(url);
    }

    @OnClick(R.id.culture_finish)
    public void onViewClicked() {
        finish();
    }
}
