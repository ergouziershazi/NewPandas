package com.newpandas.ui.pandalive.detilsfragment;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import com.newpandas.R;
import com.newpandas.base.BaseFragment;
import com.newpandas.model.entity.pandalive.MultipleBean;
import com.newpandas.model.entity.pandalive.PandaEyeTabBean;
import com.newpandas.model.entity.pandalive.SendingBean;
import com.newpandas.ui.pandalive.PandaEyeContract;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.Unbinder;

/**
 * Created by yan on 2017/7/31.
 */

public class MultiAngleFragment extends BaseFragment implements PandaEyeContract.view {

    @BindView(R.id.panda_live_grid)
    GridView pandaLiveGrid;
    Unbinder unbinder;
    PandaEyeContract.persenter persenter;
    private MultipleGridAdapter adapter;
    BroadcastReceiver receiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            multipleList.clear();
            persenter.start();
            adapter.notifyDataSetChanged();
//            LogUtils.setLog("Multi",multipleList.size()+"::");
        }
    };

    private ArrayList<MultipleBean.ListBean> multipleList;
    @Override
    protected int getLayoutId() {
        return R.layout.pandalive_multiple;
    }

    @Override
    protected void init(View view) {
        multipleList = new ArrayList<>();

        IntentFilter filter = new IntentFilter();
        filter.addAction("can.refresh");
        getActivity().registerReceiver(receiver,filter);

        adapter = new MultipleGridAdapter(getActivity(),multipleList);
        pandaLiveGrid.setAdapter(adapter);

        pandaLiveGrid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent();
                intent.setAction("com.sending_url");
                intent.putExtra("image_url",multipleList.get(position).getImage());
                intent.putExtra("url",multipleList.get(position).getUrl());
                getActivity().sendBroadcast(intent);
            }
        });
    }

    @Override
    protected void loadData() {
        persenter.start();
    }

    @Override
    public void showTabTitles(PandaEyeTabBean pandaEyeTabBean) {

    }

    @Override
    public void setMultipleBean(MultipleBean bean) {
        multipleList.addAll(bean.getList());

        adapter.notifyDataSetChanged();
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
        this.persenter = persenter;
    }

}
