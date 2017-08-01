package com.newpandas.ui.myself.fragment;

import android.graphics.Bitmap;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.newpandas.R;
import com.newpandas.base.BaseFragment;
import com.newpandas.widget.manager.ToastManager;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by 联想 on 2017/7/30.
 */

public class RegisterPhoneFragment extends BaseFragment implements PhoneContract.View{
    @BindView(R.id.phone_edit_phonenumber)
    EditText phoneEditPhonenumber;
    @BindView(R.id.phone_check_phone)
    TextView phoneCheckPhone;
    @BindView(R.id.phone_edit_imgcode)
    EditText phoneEditImgcode;
    @BindView(R.id.phone_imgcode)
    ImageView phoneImgcode;
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
    private PhoneContract.Presenter presenter;


    @Override
    protected int getLayoutId() {
        return R.layout.phone_register;
    }

    @Override
    protected void init(View view) {
        new PhonePresenter(this);
        presenter.start();
    }

    @Override
    protected void loadData() {

    }

    @OnClick({R.id.phone_imgcode, R.id.phone_yanzheng, R.id.pact_checkbox, R.id.pact_txt, R.id.phone_register})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.phone_imgcode:
                presenter.start();
                break;
            case R.id.phone_yanzheng:
                presenter.loadPhoneCode(phoneEditPhonenumber.getText().toString().trim(),
                        phoneEditImgcode.getText().toString().trim());
                break;
            case R.id.pact_checkbox:
                break;
            case R.id.pact_txt:
                break;
            case R.id.phone_register:
                presenter.phoneRegister(phoneEditPhonenumber.getText().toString().trim()
                        ,phonePwd.getText().toString().trim()
                        ,phoneEditYanzheng.getText().toString().trim());
                break;
        }
    }

    @Override
    public void showImgCode(Bitmap bitmap) {
        phoneImgcode.setImageBitmap(bitmap);
    }

    @Override
    public void showPhoneCode() {
        ToastManager.showToast("已发送");
    }

    @Override
    public void toSuccess() {
        ToastManager.showToast("成功");
    }

    @Override
    public void showProgress() {

    }

    @Override
    public void dimissProgress() {

    }

    @Override
    public void showMessage(String msg) {

    }

    @Override
    public void setPresenter(PhoneContract.Presenter presenter) {
        this.presenter=presenter;
    }

}
