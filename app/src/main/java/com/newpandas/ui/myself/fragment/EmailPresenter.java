package com.newpandas.ui.myself.fragment;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;

import com.newpandas.config.Keys;
import com.newpandas.model.biz.EmailRegisterModel;
import com.newpandas.model.biz.EmailRegisterModelImp;
import com.newpandas.model.entity.EmailRegister;
import com.newpandas.net.callback.NetWorkCallBack;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by 联想 on 2017/7/31.
 */

public class EmailPresenter implements EmailContract.Presenter{
    private EmailContract.View emailview;
    private EmailRegisterModel registerModel;
    private String jsessionid;

    public EmailPresenter(EmailContract.View emailview) {
        this.emailview=emailview;
        this.emailview.setPresenter(this);
        registerModel=new EmailRegisterModelImp();
    }

    @Override
    public boolean checkEmail(String emailAddress) {
        if(emailAddress == null || "".equals(emailAddress)){
            emailview.showEmailTips("邮箱不能为空");
            return false;
        }
        String regEx = "^([a-zA-Z0-9_\\-\\.]+)@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.)|(([a-zA-Z0-9\\-]+\\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\\]?)$";
        Pattern pattern = Pattern.compile(regEx);
        // 忽略大小写的写法
        // Pattern pat = Pattern.compile(regEx, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(emailAddress);
        // 字符串是否与正则表达式相匹配
        boolean matches = matcher.matches();
        if(!matches){
            emailview.showEmailTips("邮箱格式不正确");
            return false;
        }

        emailview.hideEmailTips();
        return true;
    }

    @Override
    public boolean checkPwd(String pwd) {
        if(pwd==null|| "".equals(pwd)){
            emailview.showPwdTips("密码不能为空");
            return false;
        }
        if(pwd.length()<6){
            emailview.showPwdTips("密码不能少于6位");
            return false;
        }
        emailview.hidePwdTips();
        return true;
    }

    @Override
    public boolean checkSurePwd(String pwd, String surepwd) {
        if(pwd==null|| "".equals(pwd)||surepwd==null||"".equals(surepwd)){
            emailview.showSurePwdTips("密码不能为空");
            return false;
        }
        if(!pwd.equals(surepwd)){
            emailview.showSurePwdTips("密码不能相同");
            return false;
        }
        emailview.hideSurePwdTips();
        return true;
    }

    @Override
    public boolean checkImgCode(String imgCode) {
        return true;
    }

    @Override
    public void register(String mailAdd, String passWd, String verificationCode) {
        if(!checkEmail(mailAdd))
            return;
        if(!checkPwd(passWd))
            return;
        if(!checkImgCode(verificationCode))
            return;
        registerModel.register(mailAdd, passWd, verificationCode, jsessionid, new NetWorkCallBack<EmailRegister>() {
            @Override
            public void onSuccess(EmailRegister register) {

                String msg = register.getMsg();
                if("成功".equals(msg)){
                    emailview.toLogin();
                }
                emailview.showMessage(msg);
            }

            @Override
            public void onError(int errorCode, String errorMsg) {
                emailview.showMessage(errorMsg);
            }
        });
    }

    @Override
    public void start() {
        registerModel.loadImgCode(new NetWorkCallBack<Bundle>() {
            @Override
            public void onSuccess(Bundle bundle) {
                jsessionid = bundle.getString(Keys.JSESSIONID);
                byte[] byteArray = bundle.getByteArray(Keys.IMGCODE);
                Bitmap bitmap = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);
                emailview.showImgCode(bitmap);
            }

            @Override
            public void onError(int errorCode, String errorMsg) {
                emailview.showMessage(errorMsg);
            }
        });
    }
}
