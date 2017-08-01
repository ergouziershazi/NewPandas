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

public class HomeAreaAdapter extends MyBaseAdapter<PandaHome.DataBean.AreaBean.ListscrollBean> {


    public HomeAreaAdapter(Context context, @LayoutRes int layoutRes, @NonNull List<PandaHome.DataBean.AreaBean.ListscrollBean> list) {
        super(context, layoutRes, list);
    }

    @Override
    protected void convert(MyViewHolder holder, PandaHome.DataBean.AreaBean.ListscrollBean listscrollBean) {
        holder.setText(R.id.areaTitle,listscrollBean.getTitle());
        holder.setImage(R.id.areaImg,listscrollBean.getImage());
        holder.setOnItemclickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(App.context,"精彩推荐",Toast.LENGTH_SHORT).show();
            }
        });

    }
}
