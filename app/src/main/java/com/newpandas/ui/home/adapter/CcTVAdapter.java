package com.newpandas.ui.home.adapter;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.Toast;

import com.newpandas.R;
import com.newpandas.app.App;
import com.newpandas.base.MyBaseAdapter;
import com.newpandas.model.entity.CcTvForBean;

import java.util.List;

/**
 * Created by yan on 2017/7/28.
 */

public class CcTVAdapter extends MyBaseAdapter <CcTvForBean.ListBean>{

    public CcTVAdapter(Context context, @LayoutRes int layoutRes, @NonNull List<CcTvForBean.ListBean> list) {
        super(context, layoutRes, list);
    }

    @Override
    protected void convert(MyViewHolder holder, CcTvForBean.ListBean listBean) {
       holder.setText(R.id.china_cctv_bttom,listBean.getTitle());
        holder.setImage(R.id.china_cctv_img,listBean.getImage());
        holder.setOnItemclickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(App.context,"cctv吐司",Toast.LENGTH_SHORT).show();
            }
        });

    }
}
