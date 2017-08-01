package com.newpandas.activity.hudong;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;

import com.newpandas.R;
import com.newpandas.base.MyBaseAdapter;
import com.newpandas.model.entity.HudongListBean;

import java.util.List;

/**
 * Created by 联想 on 2017/7/30.
 */

public class HudongRviewAdapter extends MyBaseAdapter<HudongListBean.InteractiveBean>{
    public HudongRviewAdapter(Context context, @LayoutRes int layoutRes, @NonNull List<HudongListBean.InteractiveBean> list) {
        super(context, layoutRes, list);
    }

    @Override
    protected void convert(MyViewHolder holder, HudongListBean.InteractiveBean interactiveBean) {
        holder.setText(R.id.hudong_title,interactiveBean.getTitle());
        holder.setImage(R.id.hudong_img,interactiveBean.getImage());
    }
}
