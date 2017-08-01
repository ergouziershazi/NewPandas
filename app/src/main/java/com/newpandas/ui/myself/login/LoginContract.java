package com.newpandas.ui.myself.login;

import com.newpandas.base.BaseView;

/**
 * Created by 联想 on 2017/8/1.
 */

public interface LoginContract {
    interface view extends BaseView<presenter>{
       void showLoginBean(String jsonID, String userID);
        void toSuccess();
    }
    interface presenter {
        void login(String userName, String passWard);
        void getNickname(String jsonID, String userID);
    }
}
