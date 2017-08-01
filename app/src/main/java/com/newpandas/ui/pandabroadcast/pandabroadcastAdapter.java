package com.newpandas.ui.pandabroadcast;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;

import com.newpandas.R;
import com.newpandas.base.MyBaseAdapter;
import com.newpandas.model.entity.PandaBroadcastListBean;

import java.text.SimpleDateFormat;
import java.util.List;

/**
 * Created by 联想 on 2017/7/29.
 */

public class pandabroadcastAdapter extends MyBaseAdapter<PandaBroadcastListBean.ListBean>{

    public pandabroadcastAdapter(Context context, @LayoutRes int layoutRes, @NonNull List list) {
        super(context, layoutRes, list);
    }


    @Override
    protected void convert(MyViewHolder holder, PandaBroadcastListBean.ListBean listBean) {
        holder.setText(R.id.pandaculture_title,listBean.getTitle());
        holder.setImage(R.id.pandaculture_img,listBean.getPicurl());
        holder.setText(R.id.pandaculture_time,listBean.getVideolength());
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        String format = simpleDateFormat.format(listBean.getFocus_date());
        holder.setText(R.id.pandaculture_content,format);
    }
}
