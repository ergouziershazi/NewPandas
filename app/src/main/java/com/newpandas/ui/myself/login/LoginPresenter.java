package com.newpandas.ui.myself.login;

import com.newpandas.config.Urls;
import com.newpandas.model.biz.LoginModel;
import com.newpandas.model.biz.LoginModelImp;
import com.newpandas.model.entity.LoginBean;
import com.newpandas.model.entity.NickNameBean;
import com.newpandas.net.callback.NetCallBack;
import com.newpandas.net.callback.NetWorkCallBack;

import java.net.URLEncoder;
import java.util.HashMap;

/**
 * Created by 联想 on 2017/8/1.
 */

public class LoginPresenter implements LoginContract.presenter{
    private LoginContract.view loginview;
    private LoginModel loginModel;
    public LoginPresenter(LoginContract.view loginview) {
        this.loginview=loginview;
        this.loginview.setPresenter(this);
        loginModel=new LoginModelImp();
    }

    @Override
    public void login(String userName, String passWard) {
        HashMap<String, String> params = new HashMap<>();
        HashMap<String, String> headers = new HashMap<>();
        try {
            params.put("username",URLEncoder.encode(userName,"UTF-8"));
            params.put("password",passWard);
            params.put("service","client_transaction");
            params.put("from",URLEncoder.encode(Urls.FORM, "UTF-8"));

            headers.put("Referer",URLEncoder.encode(Urls.FORM, "UTF-8"));
            headers.put("User-Agent",URLEncoder.encode("CNTV_APP_CLIENT_CYNTV_MOBILE", "UTF-8"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        loginModel.getLoginBean(params, headers, new NetCallBack<LoginBean>() {
            @Override
            public void onSuccess(String jsonid, LoginBean loginBean) {
                String user_seq_id = loginBean.getUser_seq_id();
                loginview.toSuccess();
            }

            @Override
            public void onError(int errorCode, String errorMsg) {
                loginview.showMessage(errorMsg);
            }
        });

    }

    @Override
    public void getNickname(String jsonID, String userID) {
        HashMap<String, String> params = new HashMap<>();
        HashMap<String, String> headers = new HashMap<>();
        String form = "http://cbox_mobile.regclientuser.cntv.cn";
        try {
            params.put("client","cbox_mobile");
            params.put("method","user.getNickName");
            params.put("userid",userID);

            headers.put("Referer",URLEncoder.encode(form, "UTF-8"));
            headers.put("User-Agent",URLEncoder.encode("CNTV_APP_CLIENT_CBOX_MOBILE", "UTF-8"));
            headers.put("Cookie",jsonID);

        } catch (Exception e) {
            e.printStackTrace();
        }

        loginModel.getNickNameBean(params, headers, new NetWorkCallBack<NickNameBean>() {
            @Override
            public void onSuccess(NickNameBean nickNameBean) {
                loginview.toSuccess();
            }

            @Override
            public void onError(int errorCode, String errorMsg) {
                loginview.showMessage(errorMsg);
            }
        });
    }

}
