package com.newpandas.ui.home.adapter;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;

import com.newpandas.R;
import com.newpandas.base.MyBaseAdapter;
import com.newpandas.model.entity.PandaEyeBean;

import java.util.List;

/**
 * Created by yan on 2017/7/30.
 */

public class PandaEyEBottom extends MyBaseAdapter<PandaEyeBean.ListBean> {


    public PandaEyEBottom(Context context, @LayoutRes int layoutRes, @NonNull List<PandaEyeBean.ListBean> list) {
        super(context, layoutRes, list);
    }

    @Override
    protected void convert(MyViewHolder holder, PandaEyeBean.ListBean listBean) {
        holder.setImage(R.id.light_china_img,listBean.getImage());
        holder.setText(R.id.light_china_title,listBean.getTitle());
        holder.setText(R.id.light_china_time,listBean.getDaytime());
        holder.setText(R.id.light_china_content,listBean.getVideoLength());
    }
}
