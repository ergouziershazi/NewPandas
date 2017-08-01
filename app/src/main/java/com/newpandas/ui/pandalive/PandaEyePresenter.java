package com.newpandas.ui.pandalive;

import com.newpandas.model.biz.PandaEyeModel;
import com.newpandas.model.biz.PandaEyeModelImp;
import com.newpandas.model.entity.pandalive.MultipleBean;
import com.newpandas.model.entity.pandalive.PandaEyeTabBean;
import com.newpandas.model.entity.pandalive.SendingBean;
import com.newpandas.net.callback.NetWorkCallBack;

/**
 * Created by yan on 2017/7/30.
 */

public class PandaEyePresenter implements PandaEyeContract.persenter {
    PandaEyeContract.view pandayeyeview;
    PandaEyeModel pandaEyeModel;
    public PandaEyePresenter(PandaEyeContract.view pandayeyeview){
        this.pandayeyeview=pandayeyeview;
        pandayeyeview.setPresenter(this);
        this.pandaEyeModel=new PandaEyeModelImp();

    }
    @Override
    public void start() {
        pandaEyeModel.getSendingBean(new NetWorkCallBack<SendingBean>() {

            @Override
            public void onSuccess(SendingBean sendingBean) {
              pandayeyeview.setPandaLiveDate(sendingBean);
            }

            @Override
            public void onError(int errorCode, String errorMsg) {
              pandayeyeview.showMessage(errorMsg);
            }
        });

        pandaEyeModel.getTabtitleData(new NetWorkCallBack<PandaEyeTabBean>() {
          @Override
          public void onSuccess(PandaEyeTabBean pandaEyeTabBean) {
              pandayeyeview.showTabTitles(pandaEyeTabBean);
          }

          @Override
          public void onError(int errorCode, String errorMsg) {
              pandayeyeview.showMessage(errorMsg);
          }
      });
        pandaEyeModel.getMultipleBean(new NetWorkCallBack<MultipleBean>() {
            @Override
            public void onSuccess(MultipleBean multipleBean) {
                pandayeyeview.setMultipleBean(multipleBean);
            }

            @Override
            public void onError(int errorCode, String errorMsg) {
              pandayeyeview.showMessage(errorMsg);
            }
        });
    }



}
