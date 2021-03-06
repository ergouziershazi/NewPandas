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
import com.newpandas.model.entity.PandaCulture;

import java.util.ArrayList;

/**
 * Created by Nicky on 2017/7/28.
 */

public class PandaCultureAdapter extends RecyclerView.Adapter {

    private Context context;
    private ArrayList<PandaCulture.ListBean> list;

    public PandaCultureAdapter(ArrayList<PandaCulture.ListBean> list, Context context) {
        this.list = list;
        this.context = context;
    }

    class MyViewHolder extends RecyclerView.ViewHolder{

        private ImageView imageView;
        private TextView time;
        private TextView title;
        private TextView content;

        public MyViewHolder(View itemView) {
            super(itemView);
            imageView= (ImageView) itemView.findViewById(R.id.pandaculture_img);
            time= (TextView) itemView.findViewById(R.id.pandaculture_time);
            title= (TextView) itemView.findViewById(R.id.pandaculture_title);
            content= (TextView) itemView.findViewById(R.id.pandaculture_content);
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.pandaculture_item,null);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {
        MyViewHolder viewHolder= (MyViewHolder) holder;
        Glide.with(context).load(list.get(position).getImage()).into(viewHolder.imageView);
        viewHolder.time.setText(list.get(position).getVideoLength());
        viewHolder.title.setText(list.get(position).getTitle());
        viewHolder.content.setText(list.get(position).getBrief());

        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
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
