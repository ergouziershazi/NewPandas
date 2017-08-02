package com.newpandas.ui.home;

import com.newpandas.model.biz.IPandaHomeModel;
import com.newpandas.model.biz.PandaHomeImp;
import com.newpandas.model.entity.CcTvForBean;
import com.newpandas.model.entity.LightChinaBean;
import com.newpandas.model.entity.PandaEyeBean;
import com.newpandas.model.entity.PandaHome;
import com.newpandas.model.entity.VideoInfoBean;
import com.newpandas.net.callback.NetWorkCallBack;

/**
 * Created by yan on 2017/7/28.
 */

public class HomePersenter implements HomeContract.persenter {
   private HomeContract.view homeview;
   private IPandaHomeModel pandaHomeModel;
   public HomePersenter(HomeContract.view homeview){
       this.homeview=homeview;
       homeview.setPresenter(this);
      this.pandaHomeModel=new PandaHomeImp();
   }

    @Override
    public void start() {
    homeview.showProgress();
        pandaHomeModel.loadHomeList(new NetWorkCallBack<PandaHome>() {
            @Override
            public void onSuccess(PandaHome pandaHome) {
                homeview.showHomeListData(pandaHome);
                homeview.dimissProgress();
            }

            @Override
            public void onError(int errorCode, String errorMsg) {
                homeview.showMessage(errorMsg);
                homeview.dimissProgress();
            }
        });
    }

    @Override
    public void loadCCTVData(String url) {
        pandaHomeModel.loadCcTv(url, new NetWorkCallBack<CcTvForBean>() {
            @Override
            public void onSuccess(CcTvForBean ccTvForBean) {
                homeview.showCCTVData(ccTvForBean);
            }

            @Override
            public void onError(int errorCode, String errorMsg) {
                homeview.showMessage(errorMsg);
            }
        });
    }

    @Override
    public void loadLightChina(String url) {
        pandaHomeModel.loadLightChina(url, new NetWorkCallBack<LightChinaBean>() {
            @Override
            public void onSuccess(LightChinaBean lightChinaBean) {
                homeview.showLightChina(lightChinaBean);
            }

            @Override
            public void onError(int errorCode, String errorMsg) {
              homeview.showMessage(errorMsg);
            }
        });
    }

    @Override
    public void loadPandaEye(String url) {
        pandaHomeModel.loadPandaEye(url, new NetWorkCallBack<PandaEyeBean>() {
            @Override
            public void onSuccess(PandaEyeBean pandaEyeBean) {
                homeview.showPandaEye(pandaEyeBean);
            }

            @Override
            public void onError(int errorCode, String errorMsg) {
              homeview.showMessage(errorMsg);
            }
        });
    }

    @Override
    public void rotationResult(String id) {
        pandaHomeModel.getRotationBean(id, new NetWorkCallBack<VideoInfoBean>() {
            @Override
            public void onSuccess(VideoInfoBean videoInfoBean) {
                homeview.setRotationResult(videoInfoBean);
            }

            @Override
            public void onError(int errorCode, String errorMsg) {
              homeview.showMessage(errorMsg);
            }
        });
    }

    @Override
    public void wonderfulResult(String id) {
     pandaHomeModel.getWonderfulBean(id, new NetWorkCallBack<VideoInfoBean>() {
         @Override
         public void onSuccess(VideoInfoBean videoInfoBean) {
             homeview.setWonderfulResult(videoInfoBean);
         }

         @Override
         public void onError(int errorCode, String errorMsg) {
           homeview.showMessage(errorMsg);
         }
     });
    }

    @Override
    public void itemResult(String id) {
     pandaHomeModel.getItemBean(id, new NetWorkCallBack<VideoInfoBean>() {
         @Override
         public void onSuccess(VideoInfoBean videoInfoBean) {
             homeview.setItemResult(videoInfoBean);
         }

         @Override
         public void onError(int errorCode, String errorMsg) {
            homeview.showMessage(errorMsg);
         }
     });
    }

    @Override
    public void pandaWatchResult(String id) {

    }

    @Override
    public void lightChinaVideoInfo(String id) {

    }


}
