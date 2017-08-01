package com.newpandas.ui.livechina;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.newpandas.R;

import java.util.ArrayList;


public class DragAdapter extends BaseAdapter {
    private Context context;
    public ArrayList<String> channels;
    public boolean flag=false;

    public DragAdapter(Activity activity, ArrayList<String> channels) {
        context = activity;
        this.channels = channels;
    }

    @Override
    public int getCount() {
        return channels.size();
    }

    @Override
    public Object getItem(int position) {
        return channels.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if (convertView == null) {
            viewHolder = new ViewHolder();
            convertView = LayoutInflater.from(context).inflate(R.layout.category_item, null);
            viewHolder.textView = (TextView) convertView.findViewById(R.id.text_item);
            viewHolder.imgbut= (ImageView) convertView.findViewById(R.id.catefpry_item_image);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.textView.setText(channels.get(position));
        if(flag){
            viewHolder.imgbut.setVisibility(View.VISIBLE);
        }else {
            viewHolder.imgbut.setVisibility(View.GONE);
        }
        return convertView;
    }

    public void swap(int i, int j) {
        if (j < i) {
            String s = channels.get(i);
            channels.add(j, s);
            channels.remove(i + 1);
        } else if (i < j) {
            String s = channels.get(i);
            channels.add(j + 1, s);
            channels.remove(i);
        }
    }

    static class ViewHolder {
        public TextView textView;
        public ImageView imgbut;
    }
}
