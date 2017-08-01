package com.newpandas.ui.pandalive.detilsfragment;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.newpandas.R;
import com.newpandas.model.entity.pandalive.OtherDetilsBean;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by yan on 2017/7/31.
 */

public class OtherTabAdapter extends RecyclerView.Adapter {
    List<OtherDetilsBean.VideoBean> otherTabDetails;
    Context context;
    private LayoutInflater inflater;
    OnclickGoPlay onclickGoPlay;

    public OtherTabAdapter(Context activity, List<OtherDetilsBean.VideoBean> otherTabDetails) {
        this.otherTabDetails = otherTabDetails;
        this.context = activity;
        inflater = LayoutInflater.from(activity);

    }

    public void setOnclickGoPlay(OnclickGoPlay onclickGoPlay) {
        this.onclickGoPlay = onclickGoPlay;
    }

    public interface OnclickGoPlay {
        void goPlay(int pos);
    }

    class OtherTabHold extends RecyclerView.ViewHolder {
        @BindView(R.id.pandaLive_itemImg)
        ImageView pandaLiveItemImg;
        @BindView(R.id.pandaLive_itemLongTime)
        TextView pandaLiveItemLongTime;
        @BindView(R.id.pandaLive_itemTitle)
        TextView pandaLiveItemTitle;
        @BindView(R.id.pandaLive_itemTime)
        TextView pandaLiveItemTime;

        public OtherTabHold(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.pandalive_other_item, null);
        return new OtherTabHold(view);

    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        OtherTabHold hold = (OtherTabHold) holder;
        hold.pandaLiveItemTitle.setText(otherTabDetails.get(position).getT());
        hold.pandaLiveItemTime.setText(otherTabDetails.get(position).getPtime());
        hold.pandaLiveItemLongTime.setText(otherTabDetails.get(position).getLen());
        Glide.with(context).load(otherTabDetails.get(position).getImg()).into(hold.pandaLiveItemImg);

        hold.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onclickGoPlay.goPlay(position);

            }
        });
    }

    @Override
    public int getItemCount() {
        return otherTabDetails.size();
    }
}
