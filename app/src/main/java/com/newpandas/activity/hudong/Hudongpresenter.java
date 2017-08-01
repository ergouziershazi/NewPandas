package com.newpandas.activity.hudong;

import com.newpandas.model.biz.HudongModel;
import com.newpandas.model.biz.HudongModelImp;
import com.newpandas.model.entity.HudongListBean;
import com.newpandas.net.callback.NetWorkCallBack;

/**
 * Created by 联想 on 2017/7/30.
 */

public class Hudongpresenter implements HudongContract.presenter{
    private HudongContract.view hudongview;
    private HudongModel hudongModel;
    public Hudongpresenter(HudongContract.view hudongview) {
        this.hudongview=hudongview;
        this.hudongview.setPresenter(this);
        hudongModel=new HudongModelImp();
    }

    @Override
    public void start() {
        hudongModel.getHudonglistBean(new NetWorkCallBack<HudongListBean>() {
            @Override
            public void onSuccess(HudongListBean hudongListBean) {
                hudongview.showListBean(hudongListBean);
            }

            @Override
            public void onError(int errorCode, String errorMsg) {
                hudongview.showMessage(errorMsg);
            }
        });
    }
}
