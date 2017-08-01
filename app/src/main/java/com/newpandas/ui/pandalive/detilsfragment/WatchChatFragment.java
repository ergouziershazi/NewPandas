package com.newpandas.ui.pandalive.detilsfragment;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.jcodecraeer.xrecyclerview.ProgressStyle;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.newpandas.R;
import com.newpandas.base.BaseFragment;
import com.newpandas.model.entity.pandalive.FoolrData;
import com.newpandas.model.entity.pandalive.Watch;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import butterknife.BindView;
import butterknife.Unbinder;

/**
 * Created by yan on 2017/7/31.
 */

public class WatchChatFragment extends BaseFragment {
    @BindView(R.id.watchChat_edit)
    EditText watchChatEdit;
    @BindView(R.id.watchChat_send)
    Button watchChatSend;
    Unbinder unbinder;
    @BindView(R.id.watch_chat_recyler)
    XRecyclerView watchChatRecyler;
    private ArrayList<Watch.Bean> beanArrayList = new ArrayList<>();
    Watch watch;
    private WatchCommentAdapter watchCommentAdapter;
    private ArrayList<FoolrData> strings;
    private int page = 1;
    BroadcastReceiver receiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            beanArrayList.clear();
            strings.clear();
            loadData();
            watchCommentAdapter.notifyDataSetChanged();

//            LogUtils.setLog("WatchChat",beanArrayList.size()+"::"+strings.size());
        }
    };
    @Override
    protected int getLayoutId() {
        return R.layout.pandalive_wachtfragment;
    }

    @Override
    protected void init(View view) {

        strings = new ArrayList<>();

        IntentFilter filter = new IntentFilter();
        filter.addAction("can.refresh");
        getActivity().registerReceiver(receiver,filter);

        watchChatRecyler.setFocusable(true);

        watchChatRecyler.setLayoutManager(new LinearLayoutManager(getContext()));
        watchCommentAdapter = new WatchCommentAdapter(getActivity(),beanArrayList,strings);
        watchChatRecyler.setAdapter(watchCommentAdapter);

        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String format1 = format.format(date);
        View header =   LayoutInflater.from(getContext()).inflate(R.layout.pandalive_watch_header,null);
        TextView dates = (TextView) header.findViewById(R.id.refresh_date);
        dates.setText(format1);
        watchChatRecyler.addHeaderView(header);
        watchChatRecyler.setRefreshProgressStyle(ProgressStyle.BallSpinFadeLoader);
        watchChatRecyler.setLoadingMoreProgressStyle(ProgressStyle.Pacman);
//        TextView times = new TextView(getContext());
//
//        times.setText("最后更新时间："+format1);
//
//        TextView upLoad = new TextView(getContext());
//        upLoad.setText("松开加载更多");
//        pullToRefreshRecyclerView.addHeaderView(times);
//        pullToRefreshRecyclerView.setFootView(upLoad);

        watchChatRecyler.setLoadingMoreEnabled(true);
        watchChatRecyler.setPullRefreshEnabled(true);

        watchChatRecyler.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                watchChatRecyler.refreshComplete();
                beanArrayList.clear();
                strings.clear();
                loadData();
                watchCommentAdapter.notifyDataSetChanged();
            }

            @Override
            public void onLoadMore() {
                watchChatRecyler.loadMoreComplete();
                page++;
                loadData();
                watchCommentAdapter.notifyDataSetChanged();

            }
        });
    }

    @Override
    protected void loadData() {

        Thread thread = new Thread(){
            @Override
            public void run() {
                super.run();
                try {
                    URL url = new URL("http://newcomment.cntv.cn/comment/list?app=ipandaApp&itemid=zhiboye_chat&nature=1&page="+page);
                    HttpURLConnection huc = (HttpURLConnection) url.openConnection();
                    huc.setDoInput(true);

                    InputStream inputStream = huc.getInputStream();
                    StringBuffer buffer = new StringBuffer();
                    int re=0;
                    byte[] byt = new byte[1024];

                    while ((re=inputStream.read(byt))!=-1){
                        buffer.append(new String(byt,0,re));
                    }

                    inputStream.close();
                    huc.disconnect();

                    JSONObject object = new JSONObject(buffer.toString());
//                    LogUtils.setLog("wcf",buffer.toString());

                    watch = new Watch();
                    watch.setTime(object.getInt("time"));
                    final JSONObject data = object.getJSONObject("data");
                    watch.setTotal(data.getString("total"));
//                    LogUtils.setLog("data",data.getString("total")+",,,,,"+watch.getTotal());
                    JSONArray content = data.getJSONArray("content");
                    ArrayList<Watch.Bean> arrayList = new ArrayList<>();
                    for (int i=0;i<content.length();i++){
                        JSONObject jsonObject = content.getJSONObject(i);
                        Watch.Bean bean = new Watch.Bean();

                        bean.setAuthor(jsonObject.getString("author"));
                        bean.setAuthorId(jsonObject.getString("authorid"));
                        bean.setDateline(jsonObject.getString("dateline"));
                        bean.setLocale(jsonObject.getString("locale"));
                        bean.setMessage(jsonObject.getString("message"));
                        bean.setPid(jsonObject.getString("pid"));
                        bean.setTid(jsonObject.getString("tid"));
                        arrayList.add(bean);
                    }
                    watch.setList(arrayList);

                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {

                            int floor = Integer.parseInt(watch.getTotal());
                            FoolrData dates = new FoolrData();
//                            LogUtils.setLog("FFF",floor+"ass");
                            dates.setTotal(floor);
                            dates.setDate(watch.getTime());
                            strings.add(dates);
//                            LogUtils.setLog("FloorDate",dates.toString());
                            beanArrayList.addAll(watch.getList());

                            watchCommentAdapter.notifyDataSetChanged();
                        }
                    });

                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        };
        thread.start();

    }
    }

