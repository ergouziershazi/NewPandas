package com.newpandas.ui.home.adapter;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.Toast;

import com.newpandas.R;
import com.newpandas.app.App;
import com.newpandas.base.MyBaseAdapter;
import com.newpandas.model.entity.PandaHome;

import java.util.List;

/**
 * Created by yan on 2017/7/28.
 */

public class ChinaLiveAdpter extends MyBaseAdapter <PandaHome.DataBean.ChinaliveBean.ListBeanXX>{

    public ChinaLiveAdpter(Context context, @LayoutRes int layoutRes, @NonNull List<PandaHome.DataBean.ChinaliveBean.ListBeanXX> list) {
        super(context, layoutRes, list);
    }

    @Override
    protected void convert(MyViewHolder holder, PandaHome.DataBean.ChinaliveBean.ListBeanXX listBeanXX) {
        holder.setText(R.id.china_live_text,listBeanXX.getTitle());
        holder.setImage(R.id.pandalive_img,listBeanXX.getImage());
        holder.setOnItemclickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(App.context,"直播中国",Toast.LENGTH_SHORT).show();
            }
        });

    }
}
