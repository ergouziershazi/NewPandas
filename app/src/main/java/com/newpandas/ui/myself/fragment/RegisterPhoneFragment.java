package com.newpandas.ui.myself.fragment;

import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import com.newpandas.R;
import com.newpandas.base.BaseFragment;

import butterknife.BindView;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by 联想 on 2017/7/30.
 */

public class RegisterPhoneFragment extends BaseFragment {
    @BindView(R.id.phone_edit_phonenumber)
    EditText phoneEditPhonenumber;
    @BindView(R.id.phone_check_phone)
    TextView phoneCheckPhone;
    @BindView(R.id.phone_edit_imgcode)
    EditText phoneEditImgcode;
    @BindView(R.id.phone_imgcode)
    TextView phoneImgcode;
    @BindView(R.id.phone_check_imgcode)
    TextView phoneCheckImgcode;
    @BindView(R.id.phone_edit_yanzheng)
    EditText phoneEditYanzheng;
    @BindView(R.id.phone_yanzheng)
    Button phoneYanzheng;
    @BindView(R.id.phone_check_yanzheng)
    TextView phoneCheckYanzheng;
    @BindView(R.id.phone_pwd)
    EditText phonePwd;
    @BindView(R.id.phone_check_pwd)
    TextView phoneCheckPwd;
    @BindView(R.id.pact_checkbox)
    CheckBox pactCheckbox;
    @BindView(R.id.pact_txt)
    TextView pactTxt;
    @BindView(R.id.phone_register)
    Button phoneRegister;


    @Override
    protected int getLayoutId() {
        return R.layout.phone_register;
    }

    @Override
    protected void init(View view) {

    }

    @Override
    protected void loadData() {

    }

    @OnClick({R.id.phone_imgcode, R.id.phone_yanzheng, R.id.pact_checkbox, R.id.pact_txt, R.id.phone_register})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.phone_imgcode:
                break;
            case R.id.phone_yanzheng:
                break;
            case R.id.pact_checkbox:
                break;
            case R.id.pact_txt:
                break;
            case R.id.phone_register:
                break;
        }
    }
}
