package com.newpandas.ui.livechina;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.newpandas.R;
import com.newpandas.model.entity.LiveChina;

import java.util.ArrayList;

/**
 * Created by Nicky on 2017/7/31.
 */

public class ColumnAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<LiveChina.AlllistBean> list;

    public ColumnAdapter(Context context, ArrayList<LiveChina.AlllistBean> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if(convertView==null){
            holder=new ViewHolder();
            convertView= LayoutInflater.from(context).inflate(R.layout.live_china_gridview_item,null);
            holder.title= (TextView) convertView.findViewById(R.id.grid_tv);

            convertView.setTag(holder);
        }else{
            holder= (ViewHolder) convertView.getTag();
        }

        holder.title.setText(list.get(position).getTitle());
        return convertView;
    }

    class ViewHolder{
        TextView title;
    }
}
