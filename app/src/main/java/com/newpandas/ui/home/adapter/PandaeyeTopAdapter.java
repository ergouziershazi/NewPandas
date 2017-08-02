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

public class PandaeyeTopAdapter extends MyBaseAdapter<PandaHome.DataBean.PandaeyeBean.ItemsBean>{

    private itemClickListener clickListener;
    public PandaeyeTopAdapter(Context context, @LayoutRes int layoutRes, @NonNull List<PandaHome.DataBean.PandaeyeBean.ItemsBean> list) {
        super(context, layoutRes, list);
    }

    @Override
    protected void convert(MyViewHolder holder, PandaHome.DataBean.PandaeyeBean.ItemsBean itemsBean) {
       holder.setText(R.id.panda_watch_title,itemsBean.getTitle());

    }
    public void setClickListener(itemClickListener clickListener) {
        this.clickListener = clickListener;
    }

    public interface itemClickListener{
        void onItemClickListener(int position);
    }



}
