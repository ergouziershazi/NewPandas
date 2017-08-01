package com.newpandas.ui.myself;

import android.widget.ImageButton;

import com.newpandas.R;
import com.newpandas.base.BaseActivity;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by 联想 on 2017/7/29.
 */

public class CollectionActivity extends BaseActivity {
    @BindView(R.id.collection_back)
    ImageButton collectionBack;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_collection;
    }

    @Override
    protected void init() {

    }


    @OnClick(R.id.collection_back)
    public void onViewClicked() {
        finish();
    }
}
