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

public class HomeAreaAdapter extends MyBaseAdapter<PandaHome.DataBean.AreaBean.ListscrollBean> {

    private itemClickListener clickListener;
    public HomeAreaAdapter(Context context, @LayoutRes int layoutRes, @NonNull List<PandaHome.DataBean.AreaBean.ListscrollBean> list) {
        super(context, layoutRes, list);
    }

    @Override
    protected void convert(MyViewHolder holder, PandaHome.DataBean.AreaBean.ListscrollBean listscrollBean) {
        holder.setText(R.id.areaTitle,listscrollBean.getTitle());
        holder.setImage(R.id.areaImg,listscrollBean.getImage());

    }

    public void setClickListener(itemClickListener clickListener) {
        this.clickListener = clickListener;
    }

    public interface itemClickListener{
        void onItemClickListener(int position);
    }

}
