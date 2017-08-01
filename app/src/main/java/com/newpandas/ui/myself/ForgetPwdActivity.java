package com.newpandas.ui.myself;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.newpandas.R;
import com.newpandas.base.BaseActivity;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by 联想 on 2017/7/28.
 */

public class ForgetPwdActivity extends BaseActivity {
    @BindView(R.id.forget_back)
    ImageButton forgetBack;
    @BindView(R.id.forget_edit_phone)
    EditText forgetEditPhone;
    @BindView(R.id.forget_check_phone)
    TextView forgetCheckPhone;
    @BindView(R.id.forget_edit_imgcode)
    EditText forgetEditImgcode;
    @BindView(R.id.forget_imgcode)
    TextView forgetImgcode;
    @BindView(R.id.forget_check_imgcode)
    TextView forgetCheckImgcode;
    @BindView(R.id.forget_edit_yanzheng)
    EditText forgetEditYanzheng;
    @BindView(R.id.forget_yanzheng)
    Button forgetYanzheng;
    @BindView(R.id.forget_check_yanzheng)
    TextView forgetCheckYanzheng;
    @BindView(R.id.forget_edit_newpwd)
    EditText forgetEditNewpwd;
    @BindView(R.id.forget_check_newpwd)
    TextView forgetCheckNewpwd;
    @BindView(R.id.Retrieve_pwd)
    Button RetrievePwd;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_forget;
    }

    @Override
    protected void init() {

    }

    @OnClick({R.id.forget_back, R.id.forget_imgcode, R.id.forget_yanzheng, R.id.Retrieve_pwd})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.forget_back:
                finish();
                break;
            case R.id.forget_imgcode:
                break;
            case R.id.forget_yanzheng:
                break;
            case R.id.Retrieve_pwd:
                break;
        }
    }
}
