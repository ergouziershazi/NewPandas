package com.newpandas.activity.hudong;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.view.View;

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
    protected void convert(MyViewHolder holder,final HudongListBean.InteractiveBean interactiveBean) {
        holder.setText(R.id.hudong_title,interactiveBean.getTitle());
        holder.setImage(R.id.hudong_img,interactiveBean.getImage());
        holder.setOnItemclickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickLisenter.setOnWebView(interactiveBean.getUrl(),interactiveBean.getTitle(),interactiveBean.getImage());
            }
        });
    }
    private OnClickLisenter onClickLisenter;

    public void setOnClickLisenter(OnClickLisenter onClickLisenter) {
        this.onClickLisenter = onClickLisenter;
    }

    public interface OnClickLisenter{
        void setOnWebView(String url,String title,String imgurl);
    }
}
