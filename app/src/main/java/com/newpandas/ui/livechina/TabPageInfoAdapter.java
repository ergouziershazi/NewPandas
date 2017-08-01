package com.newpandas.ui.livechina;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.newpandas.R;
import com.newpandas.model.entity.LiveInfo;

import java.util.ArrayList;

/**
 * Created by Nicky on 2017/7/30.
 */

public class TabPageInfoAdapter extends RecyclerView.Adapter {

    private Context context;
    private ArrayList<LiveInfo.LiveBean> list;

    public TabPageInfoAdapter(Context context, ArrayList<LiveInfo.LiveBean> list) {
        this.context = context;
        this.list = list;
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        ImageView livechinaInfoImg;
        TextView livechinaInfoTitle;
        CheckBox livechinaInfoConimag;
        TextView livechinaInfoJianjie;

        public MyViewHolder(View itemView) {
            super(itemView);
            livechinaInfoImg= (ImageView) itemView.findViewById(R.id.livechina_info_img);
            livechinaInfoTitle= (TextView) itemView.findViewById(R.id.livechina_info_title);
            livechinaInfoConimag= (CheckBox) itemView.findViewById(R.id.livechina_info_conimag);
            livechinaInfoJianjie= (TextView) itemView.findViewById(R.id.livechina_info_jianjie);
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.tabpage_info_item, null);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        final MyViewHolder viewHolder= (MyViewHolder) holder;
        viewHolder.livechinaInfoTitle.setText(list.get(position).getTitle());
        viewHolder.livechinaInfoJianjie.setText(list.get(position).getBrief());
        Glide.with(context).load(list.get(position).getImage()).into(viewHolder.livechinaInfoImg);
        viewHolder.livechinaInfoConimag.setChecked(false);
        viewHolder.livechinaInfoConimag.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    viewHolder.livechinaInfoJianjie.setVisibility(View.VISIBLE);
                }else{
                    viewHolder.livechinaInfoJianjie.setVisibility(View.GONE);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
