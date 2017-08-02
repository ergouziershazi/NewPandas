package com.newpandas.ui.pandabroadcast;

import com.newpandas.base.BasePresenter;
import com.newpandas.base.BaseView;
import com.newpandas.model.entity.PandaBroadcastListBean;
import com.newpandas.model.entity.PandabroadcastBean;
import com.newpandas.model.entity.PbVideoBean;

/**
 * Created by 联想 on 2017/7/29.
 */

public interface PandabroadCastContract {
    interface view extends BaseView<persenter> {
        void showheaderBean(PandabroadcastBean pandabroadcaseBean);
        void showListBean(PandaBroadcastListBean pandaBroadcastListBean);
        void showLoadMoreBean(PandaBroadcastListBean pandaBroadcastListBean);
        void showVideoBean(PbVideoBean pbVideoBean);
    }
    interface persenter extends BasePresenter {
        void initListData(String url);
        void loadmore(String url, int page);
        void loadvideo(String url);
    }
}
