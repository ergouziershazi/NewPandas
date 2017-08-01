package com.newpandas.ui.myself.fragment;

import android.graphics.Bitmap;

import com.newpandas.base.BasePresenter;
import com.newpandas.base.BaseView;

/**
 * Created by 联想 on 2017/7/31.
 */

public interface EmailContract {
    interface View extends BaseView<Presenter> {

        void showEmailTips(String msg);
        void hideEmailTips();
        void showPwdTips(String msg);
        void hidePwdTips();

        void showSurePwdTips(String msg);
        void hideSurePwdTips();
        void showImgCode(Bitmap bitmap);
        void toLogin();

    }

    interface Presenter extends BasePresenter {
        boolean checkEmail(String emailAddress);
        boolean checkPwd(String pwd);
        boolean checkSurePwd(String pwd, String surepwd);
        boolean checkImgCode(String imgCode);
        void register(String mailAdd, String passWd, String verificationCode);

    }
}
