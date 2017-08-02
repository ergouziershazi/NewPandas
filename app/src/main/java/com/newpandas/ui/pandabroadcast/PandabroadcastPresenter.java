package com.newpandas.ui.pandabroadcast;

import com.newpandas.model.biz.PandaBroadCastModel;
import com.newpandas.model.biz.PandaBroadCastModelImp;
import com.newpandas.model.entity.PandaBroadcastListBean;
import com.newpandas.model.entity.PandabroadcastBean;
import com.newpandas.model.entity.PbVideoBean;
import com.newpandas.net.callback.NetWorkCallBack;

/**
 * Created by 联想 on 2017/7/29.
 */

public class PandabroadcastPresenter implements PandabroadCastContract.persenter {

    private PandabroadCastContract.view pdcview;
    private PandaBroadCastModel pbcmodel;

    public PandabroadcastPresenter(PandabroadCastContract.view pdcview) {
        this.pdcview = pdcview;
        this.pdcview.setPresenter(this);
        pbcmodel = new PandaBroadCastModelImp();
    }

    @Override
    public void initListData(String url) {
        pbcmodel.getpandabroadcastListBean(url, new NetWorkCallBack<PandaBroadcastListBean>() {
            @Override
            public void onSuccess(PandaBroadcastListBean pandaBroadcastListBean) {
                pdcview.showListBean(pandaBroadcastListBean);
                pdcview.dimissProgress();
            }

            @Override
            public void onError(int errorCode, String errorMsg) {
                pdcview.showMessage(errorMsg);
            }
        });
    }

    @Override
    public void loadmore(String url, int page) {
        pbcmodel.getpandabroadcastLoadmoreBean(url, page, new NetWorkCallBack<PandaBroadcastListBean>() {
            @Override
            public void onSuccess(PandaBroadcastListBean pandaBroadcastListBean) {
                pdcview.showLoadMoreBean(pandaBroadcastListBean);
            }

            @Override
            public void onError(int errorCode, String errorMsg) {
                pdcview.showMessage(errorMsg);
            }
        });
    }

    @Override
    public void loadvideo(String url) {
        pbcmodel.getPbVideoBean(url, new NetWorkCallBack<PbVideoBean>() {
            @Override
            public void onSuccess(PbVideoBean pbVideoBean) {
                pdcview.showVideoBean(pbVideoBean);
            }

            @Override
            public void onError(int errorCode, String errorMsg) {
                pdcview.showMessage(errorMsg);
            }
        });
    }

    @Override
    public void start() {
        pdcview.showProgress();
        pbcmodel.getPandabroadcastBean(new NetWorkCallBack<PandabroadcastBean>() {
            @Override
            public void onSuccess(PandabroadcastBean pandabroadcastBean) {
                pdcview.showheaderBean(pandabroadcastBean);
            }

            @Override
            public void onError(int errorCode, String errorMsg) {
                pdcview.showMessage(errorMsg);
            }
        });
    }
}
