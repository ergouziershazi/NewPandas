package com.newpandas.ui.home;

import com.newpandas.base.BasePresenter;
import com.newpandas.base.BaseView;
import com.newpandas.model.entity.CcTvForBean;
import com.newpandas.model.entity.LightChinaBean;
import com.newpandas.model.entity.PandaEyeBean;
import com.newpandas.model.entity.PandaHome;
import com.newpandas.model.entity.VideoInfoBean;

/**
 * Created by yan on 2017/7/28.
 */

public class HomeContract {
    interface view extends BaseView<persenter>{
        void showHomeListData(PandaHome pandaHome);
        void showCCTVData(CcTvForBean ccTvForBean);
        void showLightChina(LightChinaBean lightChinaBean);
        void showPandaEye(PandaEyeBean pandaEyeBean);
        void setRotationResult(VideoInfoBean videoInfoBean);

        void setWonderfulResult(VideoInfoBean videoInfoBean);

        void setItemResult(VideoInfoBean videoInfoBean);

        void setPandaWatchResult(VideoInfoBean videoInfoBean);

        void setLightChinaVideoInfo(VideoInfoBean videoInfoBean);
    }
    interface persenter extends BasePresenter{
     void loadCCTVData(String url);
     void loadLightChina(String url);
     void loadPandaEye(String url);
        void rotationResult(String id);
        void wonderfulResult(String id);
        void itemResult(String id);
        void pandaWatchResult(String id);
        void lightChinaVideoInfo(String id);
    }
}
