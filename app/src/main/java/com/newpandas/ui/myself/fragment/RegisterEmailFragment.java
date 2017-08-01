package com.newpandas.ui.myself.fragment;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.newpandas.R;
import com.newpandas.base.BaseFragment;
import com.newpandas.ui.myself.LoginActivity;
import com.newpandas.widget.manager.ToastManager;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

import static com.newpandas.R.id.email_edit_surepwd;


/**
 * Created by 联想 on 2017/7/30.
 */

public class RegisterEmailFragment extends BaseFragment implements EmailContract.View, View.OnTouchListener {

    @BindView(R.id.email_edit_phonenumber)
    EditText emailEditPhonenumber;
    @BindView(R.id.email_check_phone)
    TextView emailCheckPhone;
    @BindView(R.id.email_edit_pwd)
    EditText emailEditPwd;
    @BindView(R.id.email_check_pwd)
    TextView emailCheckPwd;
    @BindView(R.id.email_edit_surepwd)
    EditText emailEditSurepwd;
    @BindView(R.id.email_check_surepwd)
    TextView emailCheckSurepwd;
    @BindView(R.id.email_edit_imgcode)
    EditText emailEditImgcode;
    @BindView(R.id.email_imgcode)
    ImageView emailImgcode;
    @BindView(R.id.email_pact_checkbox)
    CheckBox emailPactCheckbox;
    @BindView(R.id.email_pact_txt)
    TextView emailPactTxt;
    @BindView(R.id.email_register)
    Button emailRegister;
    Unbinder unbinder;
    private EmailContract.Presenter presenter;

    @Override
    protected int getLayoutId() {
        return R.layout.email_register;
    }

    @Override
    protected void init(View view) {

    }

    @Override
    protected void loadData() {
        new EmailPresenter(this);
        presenter.start();
        emailEditPwd.setOnTouchListener(this);
        emailEditSurepwd.setOnTouchListener(this);
        emailEditImgcode.setOnTouchListener(this);
    }


    @OnClick({R.id.email_imgcode, R.id.email_pact_checkbox, R.id.email_pact_txt, R.id.email_register, R.id.email_edit_pwd, email_edit_surepwd, R.id.email_edit_imgcode})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.email_imgcode:
                presenter.start();
                break;
            case R.id.email_pact_checkbox:
                break;
            case R.id.email_pact_txt:
                break;
            case R.id.email_register:
                boolean b = presenter.checkEmail(emailEditPhonenumber.getText().toString().trim());
                if (b) {
                    presenter.register(emailEditPhonenumber.getText().toString().trim(),
                            emailEditPwd.getText().toString().trim(),
                            emailEditImgcode.getText().toString().trim());
                }

                break;
            case R.id.email_edit_pwd:
                presenter.checkEmail(emailEditPhonenumber.getText().toString().trim());
                break;
            case email_edit_surepwd:
                presenter.checkPwd(emailEditPwd.getText().toString().trim());
                break;
            case R.id.email_edit_imgcode:
                presenter.checkSurePwd(emailEditPwd.getText().toString().trim(), emailEditSurepwd.getText().toString().trim());
                break;
        }
    }

    @Override
    public void showEmailTips(String msg) {
        emailCheckPhone.setVisibility(View.VISIBLE);
        emailCheckPhone.setText(msg);
    }

    @Override
    public void hideEmailTips() {
        emailCheckPhone.setVisibility(View.INVISIBLE);
    }

    @Override
    public void showPwdTips(String msg) {
        ToastManager.showToast(msg);
        emailCheckPwd.setVisibility(View.VISIBLE);
        emailCheckPwd.setText(msg);
    }

    @Override
    public void hidePwdTips() {
        emailCheckPwd.setVisibility(View.INVISIBLE);
    }

    @Override
    public void showSurePwdTips(String msg) {
        emailCheckSurepwd.setVisibility(View.VISIBLE);
        emailCheckSurepwd.setText(msg);
    }

    @Override
    public void hideSurePwdTips() {
        emailCheckSurepwd.setVisibility(View.INVISIBLE);
    }

    @Override
    public void showImgCode(Bitmap bitmap) {
        emailImgcode.setImageBitmap(bitmap);
    }

    @Override
    public void toLogin() {
        Intent intent = new Intent(getActivity(), LoginActivity.class);
        startActivity(intent);
    }

    @Override
    public void showProgress() {

    }

    @Override
    public void dimissProgress() {

    }

    @Override
    public void showMessage(String msg) {
        ToastManager.showToast(msg);
    }

    @Override
    public void setPresenter(EmailContract.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        switch (v.getId()) {
            case R.id.email_edit_pwd:
                presenter.checkEmail(emailEditPhonenumber.getText().toString().trim());
                break;
        }
        return false;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
