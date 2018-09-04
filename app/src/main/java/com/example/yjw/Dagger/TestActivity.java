package com.example.yjw.Dagger;

import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.Toast;

import com.example.yjw.common.BaseActivity;
import com.example.yjw.myviewpager.R;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**

 * @Author: YeJW

 * @Date: 2018/6/8 10:33

 * @Description: Dagger 注入，TextInputLayout 的错误提示

 *

 */
public class TestActivity extends BaseActivity {

    @Inject
    Loginctr loginctr;//注入的方式实例化LoginCtrl

    @BindView(R.id.titl_account)
    TextInputLayout titlAccount;
    @BindView(R.id.titl_password)
    TextInputLayout titlPassword;
    @BindView(R.id.edit1_login)
    Button edit1Login;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        ButterKnife.bind(this);

      /*  //初始化注入，inject是LoginComponent接口里面自定义的方法；
        DaggerLoginComponent.create().inject(this);*/

        //当Module需要构造方法传参的时候，使用builder的方式初始化Dagger。
        DaggerLoginComponent.builder()
                .loginModule(new LoginModule(this))//loginModule这个方法是构建之后才有的
                .build()
                .inject(this);

        // 然后就可以调用loginCtrl里面的方法了
        loginctr.login("夏平", "pass");

    }

    /**
     * 显示错误提示，并获取焦点
     *
     * @param textInputLayout
     * @param error
     */
    private void showError(TextInputLayout textInputLayout, String error) {
        textInputLayout.setError(error);
        textInputLayout.getEditText().setFocusable(true);
        textInputLayout.getEditText().setFocusableInTouchMode(true);
        textInputLayout.getEditText().requestFocus();
    }

    /**
     * 验证用户名
     *
     * @param account
     * @return
     */
    private boolean validateAccount(String account) {
        if (TextUtils.isEmpty(account)) {
            showError(titlAccount, "用户名不能为空");
            return false;
        }
        return true;
    }

    /**
     * 验证密码
     *
     * @param password
     * @return
     */
    private boolean validatePassword(String password) {
        if (TextUtils.isEmpty(password)) {
            showError(titlPassword, "密码不能为空");
            return false;
        }

        if (password.length() < 6 || password.length() > 18) {
            showError(titlPassword, "密码长度为6-18位");
            return false;
        }

        return true;
    }



    @OnClick(R.id.edit1_login)
    public void onViewClicked() {

        String account = titlAccount.getEditText().getText().toString();
        String password = titlPassword.getEditText().getText().toString();

        titlAccount.setErrorEnabled(false);//使用这个方法来控制显隐藏
        titlPassword.setErrorEnabled(false);

        //验证用户名和密码
        if (validateAccount(account) && validatePassword(password)) {
            Toast.makeText(TestActivity.this, "登录成功", Toast.LENGTH_LONG).show();
        }else
        {
            Toast.makeText(TestActivity.this, "登录失败", Toast.LENGTH_LONG).show();
        }

    }
}
