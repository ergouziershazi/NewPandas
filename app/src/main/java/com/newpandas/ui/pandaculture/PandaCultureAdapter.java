package com.newpandas.ui.pandaculture;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;

import com.newpandas.R;
import com.newpandas.base.MyBaseAdapter;
import com.newpandas.model.entity.PandaCulture;

import java.util.List;

/**
 * Created by Nicky on 2017/7/28.
 */

public class PandaCultureAdapter extends MyBaseAdapter<PandaCulture.ListBean> {

    public PandaCultureAdapter(Context context, @LayoutRes int layoutRes, @NonNull List<PandaCulture.ListBean> list) {
        super(context, layoutRes, list);
    }

    @Override
    protected void convert(MyBaseAdapter.MyViewHolder holder, PandaCulture.ListBean listBean) {
        holder.setImage(R.id.pandaculture_img,listBean.getImage());
        holder.setText(R.id.pandaculture_time,listBean.getVideoLength());
        holder.setText(R.id.pandaculture_title,listBean.getTitle());
        holder.setText(R.id.pandaculture_content,listBean.getBrief());
    }
}
