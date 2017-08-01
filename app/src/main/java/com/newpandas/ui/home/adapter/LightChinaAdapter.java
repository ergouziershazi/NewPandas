package com.newpandas.ui.home.adapter;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.Toast;

import com.newpandas.R;
import com.newpandas.app.App;
import com.newpandas.base.MyBaseAdapter;
import com.newpandas.model.entity.LightChinaBean;

import java.util.List;

/**
 * Created by yan on 2017/7/28.
 */

public class LightChinaAdapter extends MyBaseAdapter<LightChinaBean.ListBean> {
    public LightChinaAdapter(Context context, @LayoutRes int layoutRes, @NonNull List<LightChinaBean.ListBean> list) {
        super(context, layoutRes, list);
    }
    @Override
    protected void convert(MyViewHolder holder, LightChinaBean.ListBean listBean) {
     holder.setImage(R.id.light_china_img,listBean.getImage());
        holder.setText(R.id.light_china_title,listBean.getTitle());
        holder.setText(R.id.light_china_time,listBean.getDaytime());
        holder.setText(R.id.light_china_content,listBean.getVideoLength());
        holder.setOnItemclickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(App.context,"光影中国吐司",Toast.LENGTH_SHORT).show();
            }
        });

    }
}
