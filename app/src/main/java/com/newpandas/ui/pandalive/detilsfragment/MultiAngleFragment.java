package com.newpandas.ui.pandalive.detilsfragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import com.newpandas.R;
import com.newpandas.base.BaseFragment;
import com.newpandas.model.entity.pandalive.MultipleBean;
import com.newpandas.model.entity.pandalive.PandaEyeTabBean;
import com.newpandas.model.entity.pandalive.SendingBean;
import com.newpandas.ui.pandalive.PandaEyeContract;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by yan on 2017/7/31.
 */

public class MultiAngleFragment extends BaseFragment implements PandaEyeContract.view {

    @BindView(R.id.panda_live_grid)
    GridView pandaLiveGrid;
    Unbinder unbinder;
    PandaEyeContract.persenter persenter;


    @Override
    protected int getLayoutId() {
        return R.layout.pandalive_multiple;
    }

    @Override
    protected void init(View view) {

    }

    @Override
    protected void loadData() {

    }

    @Override
    public void showTabTitles(PandaEyeTabBean pandaEyeTabBean) {

    }

    @Override
    public void setMultipleBean(MultipleBean bean) {

    }

    @Override
    public void setPandaLiveDate(SendingBean bean) {

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
    public void setPresenter(PandaEyeContract.persenter persenter) {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
