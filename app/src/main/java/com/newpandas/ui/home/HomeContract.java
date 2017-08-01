package com.newpandas.ui.home;

import com.newpandas.base.BasePresenter;
import com.newpandas.base.BaseView;
import com.newpandas.model.entity.CcTvForBean;
import com.newpandas.model.entity.LightChinaBean;
import com.newpandas.model.entity.PandaEyeBean;
import com.newpandas.model.entity.PandaHome;

/**
 * Created by yan on 2017/7/28.
 */

public class HomeContract {
    interface view extends BaseView<persenter>{
        void showHomeListData(PandaHome pandaHome);
        void showCCTVData(CcTvForBean ccTvForBean);
        void showLightChina(LightChinaBean lightChinaBean);
        void showPandaEye(PandaEyeBean pandaEyeBean);
    }
    interface persenter extends BasePresenter{
     void loadCCTVData(String url);
     void loadLightChina(String url);
     void loadPandaEye(String url);
    }
}
