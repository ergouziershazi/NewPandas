package com.newpandas.ui.pandaculture;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.newpandas.R;
import com.newpandas.model.entity.VideoSet;

import java.util.ArrayList;

/**
 * Created by Nicky on 2017/8/2.
 */

public class CultureVideoAdapter extends RecyclerView.Adapter {

    private Context context;
    private ArrayList<VideoSet.VideoBean> list;

    public CultureVideoAdapter(Context context, ArrayList<VideoSet.VideoBean> list) {
        this.context = context;
        this.list = list;
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        private ImageView img;
        private TextView time;
        private TextView title;

        public MyViewHolder(View itemView) {
            super(itemView);
            img= (ImageView) itemView.findViewById(R.id.panda_culture_detial_item_image);
            time= (TextView) itemView.findViewById(R.id.panda_culture_detial_item_sp_time);
            title= (TextView) itemView.findViewById(R.id.panda_culture_detial_item_title);
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.detilpullto_item,null);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        MyViewHolder viewHolder= (MyViewHolder) holder;
        Glide.with(context).load(list.get(position).getImg()).into(viewHolder.img);
        viewHolder.time.setText(list.get(position).getLen());
        viewHolder.title.setText(list.get(position).getT());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickListener.onItemClickListener(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public onClickListener clickListener;

    public void setClickListener(onClickListener clickListener) {
        this.clickListener = clickListener;
    }

    public interface onClickListener{
        void onItemClickListener(int position);
    }
}
