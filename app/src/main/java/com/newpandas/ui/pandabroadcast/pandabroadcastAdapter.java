package com.newpandas.ui.pandabroadcast;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.view.View;

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
    protected void convert(MyViewHolder holder,final PandaBroadcastListBean.ListBean listBean) {
        holder.setText(R.id.pandaculture_title,listBean.getTitle());
        holder.setImage(R.id.pandaculture_img,listBean.getPicurl());
        holder.setText(R.id.pandaculture_time,listBean.getVideolength());
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        String format = simpleDateFormat.format(listBean.getFocus_date());
        holder.setText(R.id.pandaculture_content,format);
        holder.setOnItemclickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClick.onClickListener(listBean.getGuid());
            }
        });
    }

    private OnClick onClick;

    public void setOnClick(OnClick onClick) {
        this.onClick = onClick;
    }

    public interface OnClick{
        void onClickListener(String pid);
    }
}
