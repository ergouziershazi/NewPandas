package com.newpandas.ui.pandalive;

import com.newpandas.base.BasePresenter;
import com.newpandas.base.BaseView;
import com.newpandas.model.entity.pandalive.MultipleBean;
import com.newpandas.model.entity.pandalive.PandaEyeTabBean;
import com.newpandas.model.entity.pandalive.SendingBean;

/**
 * Created by yan on 2017/7/30.
 */

public interface PandaEyeContract {
    interface view extends BaseView<persenter>{
       void showTabTitles(PandaEyeTabBean pandaEyeTabBean);
        void setMultipleBean(MultipleBean bean);
        void setPandaLiveDate(SendingBean bean);
    }
    interface persenter extends BasePresenter {

    }
}
