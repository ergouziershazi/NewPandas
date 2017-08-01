package com.newpandas.ui.home.adapter;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;

import com.newpandas.R;
import com.newpandas.base.MyBaseAdapter;
import com.newpandas.model.entity.PandaHome;

import java.util.List;

/**
 * Created by yan on 2017/7/28.
 */

public class WallLiveAdapter extends MyBaseAdapter<PandaHome.DataBean.WallliveBean.ListBeanX> {

    public WallLiveAdapter(Context context, @LayoutRes int layoutRes, @NonNull List<PandaHome.DataBean.WallliveBean.ListBeanX> list) {
        super(context, layoutRes, list);
    }

    @Override
    protected void convert(MyViewHolder holder, PandaHome.DataBean.WallliveBean.ListBeanX listBeanX) {
        holder.setText(R.id.china_live_text,listBeanX.getTitle());
        holder.setImage(R.id.pandalive_img,listBeanX.getImage());

    }
}
