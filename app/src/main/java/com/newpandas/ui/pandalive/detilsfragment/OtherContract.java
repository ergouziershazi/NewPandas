package com.newpandas.ui.pandalive.detilsfragment;

import com.newpandas.base.BasePresenter;
import com.newpandas.base.BaseView;
import com.newpandas.model.entity.pandalive.OtherDetilsBean;
import com.newpandas.model.entity.pandalive.PlayBean;

/**
 * Created by yan on 2017/7/31.
 */

public interface OtherContract {
    interface view extends BaseView<OtherContract.presenter>{
        void palyvideo(PlayBean playBean);
        void loadMore(OtherDetilsBean otherDetilsBean);
    }
    interface presenter extends BasePresenter{
        void getOtherTab(String vsid,String n,String serviceId,String o,String of,String p);
        void getPlayVideoData(String pid);
    }
}
