package com.newpandas.base;

import android.content.Context;
import android.support.annotation.IdRes;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

/**
 * Created by yan on 2017/7/28.
 */

public abstract class MyBaseAdapter<T> extends RecyclerView.Adapter<MyBaseAdapter.MyViewHolder> {
    private Context context;
    private List<T> mlist;
    private int mLayoutRes;

    public MyBaseAdapter(Context context, @LayoutRes int layoutRes, @NonNull List<T> list){
        this.context = context;
        this.mLayoutRes = layoutRes;
        this.mlist = list;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return MyViewHolder.get(context, mLayoutRes, parent);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        convert(holder, mlist.get(position));
    }

    protected abstract void convert(MyViewHolder holder, T t);

    @Override
    public int getItemCount() {
        if (mlist == null) {
            return 0;
        }
        return mlist.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{
        private SparseArray<View> mViews;
        private Context mContext;

        public MyViewHolder(View itemView) {
            super(itemView);
            mViews = new SparseArray<>();
        }
        public MyViewHolder(View itemView, Context context) {
            this(itemView);
            mContext = context;
        }
        public static MyViewHolder get(Context context, @LayoutRes int layoutRes, ViewGroup parent) {
            View view = LayoutInflater.from(context).inflate(layoutRes, parent, false);
            return new MyViewHolder(view, context);
        }
        public <T extends View> T getView(@IdRes int viewId) {
            View view = mViews.get(viewId);
            if (view == null) {
                view = itemView.findViewById(viewId);
                mViews.put(viewId, view);
            }
            return (T) view;
        }
        public View getItemView() {
            return itemView;
        }
//条目的点击事件
public MyViewHolder setOnItemclickListener(View.OnClickListener listener) {
    itemView.setOnClickListener(listener);
    return this;
}
//        条目的触摸事件
public MyViewHolder setOnTouchListener(View.OnTouchListener listener) {
    itemView.setOnTouchListener(listener);
    return this;
}
//给textview赋值

        public MyViewHolder setText(@IdRes int viewId, String text) {
            TextView view = getView(viewId);
            view.setText(text);
            return this;
        }

//加载图片
public MyViewHolder setImage(@IdRes int viewId,String url) {

    ImageView imageview=getView(viewId);
    Glide.with(mContext).load(url).into(imageview);
    return this;
}

    }
}
