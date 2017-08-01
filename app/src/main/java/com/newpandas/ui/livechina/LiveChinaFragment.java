package com.newpandas.ui.livechina;

import android.graphics.drawable.BitmapDrawable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.PopupWindow;

import com.newpandas.R;
import com.newpandas.base.BaseFragment;
import com.newpandas.model.entity.LiveChina;
import com.newpandas.model.entity.LiveInfo;
import com.newpandas.widget.manager.ToastManager;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by yan on 2017/7/28.
 */

public class LiveChinaFragment extends BaseFragment implements LiveChinaContract.View, View.OnClickListener {
    @BindView(R.id.livechina_landscape)
    TabLayout livechinaLandscape;
    @BindView(R.id.livechina_addlandscape)
    ImageButton livechinaAddlandscape;
    @BindView(R.id.livechina_viewpager)
    ViewPager livechinaViewpager;

    private LiveChinaContract.Presenter presenter;
    private ArrayList<LiveChina.AlllistBean> allList;
    private ArrayList<LiveChina.AlllistBean> wholeList;
    private ArrayList<BaseFragment> fragmentList;
    private ArrayList<String> urlList;
    private TabListAdapter tabListAdapter;
    private PopupWindow window;
    private View popwindowView;
    private ImageButton popwindowBtn;
    private Button editBtn;
    private GridView popwindowGridView;
    private ArrayList<String> titles;
    private ArrayList<String> strings;
    private DragAdapter dragAdapter;
    private GridView gridView;
    private ColumnAdapter columnAdapter;
    private boolean flag=false;

    @Override
    protected int getLayoutId() {
        return R.layout.livechina_frgament;
    }

    @Override
    protected void init(View view) {
        presenter.start();
        allList=new ArrayList<LiveChina.AlllistBean>();
        fragmentList=new ArrayList<BaseFragment>();
        urlList=new ArrayList<String>();
        titles=new ArrayList<String>();

        tabListAdapter = new TabListAdapter(getActivity().getSupportFragmentManager(),fragmentList,titles);
        livechinaViewpager.setAdapter(tabListAdapter);
        livechinaLandscape.setTabMode(TabLayout.MODE_SCROLLABLE);
        livechinaLandscape.setupWithViewPager(livechinaViewpager);
        livechinaLandscape.setMinimumWidth(200);
        tabListAdapter.notifyDataSetChanged();

        popwindowView = LayoutInflater.from(getActivity()).inflate(R.layout.livechina_popwindow,null);
        window = new PopupWindow(popwindowView, LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT,true);
        window.setTouchable(true);
        window.setFocusable(true);
        window.setBackgroundDrawable(new BitmapDrawable());
        window.setOutsideTouchable(true);
        popwindowBtn = (ImageButton) popwindowView.findViewById(R.id.livechina_popwindow_btn);
        editBtn = (Button) popwindowView.findViewById(R.id.edit_btn);
        setColumnGridView();
        editBtn.setOnClickListener(this);
        popwindowBtn.setOnClickListener(this);

    }

    private void setColumnGridView() {
        popwindowGridView = (GridView) popwindowView.findViewById(R.id.livechina_popwindow_gridview);
        popwindowGridView.setEnabled(false);
        strings=new ArrayList<String>();
        dragAdapter = new DragAdapter(getActivity(),strings);
        popwindowGridView.setAdapter(dragAdapter);

        wholeList=new ArrayList<LiveChina.AlllistBean>();
        gridView = (GridView) popwindowView.findViewById(R.id.livechina_gridview);
        columnAdapter = new ColumnAdapter(getActivity(),wholeList);
        gridView.setEnabled(false);
        gridView.setAdapter(columnAdapter);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(flag){
                    strings.add(wholeList.get(position).getTitle());
                    urlList.add(wholeList.get(position).getUrl());
                    wholeList.remove(position);
                    columnAdapter.notifyDataSetChanged();
                    dragAdapter.notifyDataSetChanged();
                }
            }
        });

        popwindowGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(flag){
                    if(strings.size()<=4){
                        ToastManager.showToast("栏目区不得少于4个频道");
                    }else{
                        for(int i=0;i<allList.size();i++){
                            if(strings.get(position).equals(allList.get(i).getTitle())){
                                wholeList.add(allList.get(i));
                                columnAdapter.notifyDataSetChanged();
                            }
                        }
                        strings.remove(position);
                        urlList.remove(position);
                        dragAdapter.notifyDataSetChanged();
                    }
                }
            }
        });

    }

    @Override
    protected void loadData() {
        urlList=new ArrayList<String>();
    }


    @OnClick(R.id.livechina_addlandscape)
    public void onViewClicked() {
        window.showAtLocation(popwindowView, Gravity.CENTER,0,0);
    }

    @Override
    public void setResult(LiveChina liveChina) {
        wholeList.addAll(liveChina.getAlllist());
        columnAdapter.notifyDataSetChanged();
        setGridViewHeightBasedOnChildren(gridView);

        List<LiveChina.TablistBean> tablist = liveChina.getTablist();
        for(int i = 0; i < tablist.size(); i++) {
            urlList.add(tablist.get(i).getUrl());
            strings.add(tablist.get(i).getTitle());
            dragAdapter.notifyDataSetChanged();
        }
        for(int i = 0; i < wholeList.size(); i++) {
            for(int j = 0; j < strings.size(); j++) {
                if(wholeList.get(i).getTitle().equals(strings.get(j))){
                    wholeList.remove(wholeList.get(i));
                    columnAdapter.notifyDataSetChanged();
                }
            }
        }

        setTabLayout();
    }

    private void setTabLayout() {
        fragmentList.clear();
        titles.clear();
        for(int i = 0; i < strings.size(); i++) {
            TabPageFragment tabPageFragment=new TabPageFragment();
            tabPageFragment.s=urlList.get(i);
            fragmentList.add(tabPageFragment);
            new LiveChinaPresenter(tabPageFragment);
            titles.add(strings.get(i));
            tabListAdapter.notifyDataSetChanged();
        }
        livechinaViewpager.setOffscreenPageLimit(titles.size());
    }

    /**
     * 动态设置GridView的高度
     */
    public static void setGridViewHeightBasedOnChildren(GridView gridView) {
        if (gridView == null) return;

        ListAdapter listAdapter = gridView.getAdapter();

        if (listAdapter == null) {
            // pre-condition
            return;
        }

        int totalHeight = 0;
        for (int i = 0; i < listAdapter.getCount(); i++) {
            View listItem = listAdapter.getView(i, null, gridView);
            listItem.measure(0, 0);
            totalHeight += listItem.getMeasuredHeight();
        }

        ViewGroup.LayoutParams params = gridView.getLayoutParams();
        params.height = (totalHeight + (gridView.getMeasuredHeight() * (listAdapter.getCount() - 1))) / 3;
        gridView.setLayoutParams(params);
    }

    @Override
    public void setInfoResult(LiveInfo liveInfo) {

    }

    @Override
    public void showProgress() {

    }

    @Override
    public void dimissProgress() {

    }

    @Override
    public void showMessage(String msg) {

    }

    @Override
    public void setPresenter(LiveChinaContract.Presenter presenter) {
        this.presenter=presenter;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.edit_btn:
                if(editBtn.getText().toString().equals("编辑")){
                    editBtn.setText("完成");
                    gridView.setEnabled(true);
                    dragAdapter.flag=true;
                    popwindowGridView.setEnabled(true);
                    flag=true;
                    dragAdapter.notifyDataSetChanged();
                }else{
                    editBtn.setText("编辑");
                    gridView.setEnabled(false);
                    dragAdapter.flag=false;
                    popwindowGridView.setEnabled(true);
                    flag=false;
                    dragAdapter.notifyDataSetChanged();
                }
                break;
            case R.id.livechina_popwindow_btn:
                setTabLayout();
                window.dismiss();
                break;
        }
    }
}
