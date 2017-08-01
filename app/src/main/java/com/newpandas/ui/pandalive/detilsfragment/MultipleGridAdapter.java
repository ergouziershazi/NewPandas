package com.newpandas.ui.pandalive.detilsfragment;

import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.newpandas.R;
import com.newpandas.model.entity.pandalive.MultipleBean;

import java.util.ArrayList;

/**
 * Created by yan on 2017/7/31.
 */

public class MultipleGridAdapter extends BaseAdapter {
    ArrayList<MultipleBean.ListBean> multipleList;
    Context context;

    public MultipleGridAdapter(FragmentActivity activity, ArrayList<MultipleBean.ListBean> multipleList) {
        this.multipleList = multipleList;
        this.context = activity;
    }

    @Override
    public int getCount() {
        return multipleList.size();
    }

    @Override
    public Object getItem(int position) {
        return multipleList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.multiple_item, null);
            holder = new ViewHolder();

            holder.multipleItemTitle = (TextView) convertView.findViewById(R.id.multiple_item_title);
            holder.multipleItemIamge = (ImageView) convertView.findViewById(R.id.multiple_item_iamge);

            convertView.setTag(holder);
        }else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.multipleItemTitle.setText(multipleList.get(position).getTitle());
        Glide.with(context).load(multipleList.get(position).getImage()).into(holder.multipleItemIamge);

        return convertView;
    }


    class ViewHolder {
        ImageView multipleItemIamge;
        TextView multipleItemTitle;
    }
}
