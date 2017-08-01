package com.newpandas.ui.pandalive.detilsfragment;

import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.newpandas.R;
import com.newpandas.model.entity.pandalive.FoolrData;
import com.newpandas.model.entity.pandalive.Watch;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by yan on 2017/7/31.
 */

public class WatchCommentAdapter extends RecyclerView.Adapter {
    Context con;
    ArrayList<Watch.Bean> contentBeanArrayList;
    ArrayList<FoolrData> list;
    int floors;


    public WatchCommentAdapter(FragmentActivity activity, ArrayList<Watch.Bean> contentBeanArrayList, ArrayList<FoolrData> list) {
        this.con = activity;
        this.contentBeanArrayList = contentBeanArrayList;
        this.list = list;

    }

    class WatchCommentHold extends RecyclerView.ViewHolder {
        @BindView(R.id.watchChat_author)
        TextView watchChatAuthor;
        @BindView(R.id.watchChat_total)
        TextView watchChatTotal;
        @BindView(R.id.watchChat_message)
        TextView watchChatMessage;
        @BindView(R.id.watchChat_date)
        TextView watchChatDate;

        public WatchCommentHold(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(con).inflate(R.layout.pandalive_watch_item, null);
        return new WatchCommentHold(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        WatchCommentHold hold = (WatchCommentHold) holder;
        hold.watchChatAuthor.setText(contentBeanArrayList.get(position).getAuthor());
        hold.watchChatMessage.setText(contentBeanArrayList.get(position).getMessage());
        int pos = 0;
        int total = list.get(0).getTotal();
        total = total - position;
//        LogUtils.setLog("TOTALPOS", total + ",,pos:" + pos);
        hold.watchChatTotal.setText(total + "楼");

        Date date1 = new Date(list.get(0).getDate());
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String format1 = format.format(date1);
        hold.watchChatDate.setText(format1);

//        LogUtils.setLog("watch", contentBeanArrayList.get(position).getAuthor());
    }

    @Override
    public int getItemCount() {
        return contentBeanArrayList.size();
    }
}
