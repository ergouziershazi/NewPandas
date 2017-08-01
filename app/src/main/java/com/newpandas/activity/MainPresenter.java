package com.newpandas.activity;

import com.newpandas.model.biz.MainImp;
import com.newpandas.model.biz.MainModel;
import com.newpandas.model.entity.UpDate;
import com.newpandas.net.callback.NetWorkCallBack;

/**
 * Created by Nicky on 2017/8/2.
 */

public class MainPresenter implements MainContract.Presenter {

    private MainContract.View view;
    private MainModel mainModel;

    public MainPresenter(MainContract.View view) {
        this.view = view;
        this.view.setPresenter(this);
        mainModel=new MainImp();
    }

    @Override
    public void start() {
        mainModel.getUpDates(new NetWorkCallBack<UpDate>() {
            @Override
            public void onSuccess(UpDate upDate) {
                view.setResult(upDate);
            }

            @Override
            public void onError(int errorCode, String errorMsg) {
                view.showMessage(errorMsg);
            }
        });
    }
}
