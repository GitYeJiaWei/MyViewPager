package com.example.yjw.MVP.Presenter;

import android.os.Handler;
import android.os.Looper;

import com.example.yjw.MVP.Model.IUser;
import com.example.yjw.MVP.Model.UserModel;
import com.example.yjw.MVP.View.ILoginView;


public class LoginPresenterCompl implements ILoginPresenter {
    ILoginView iLoginView;
    IUser iUser;
    Handler handler;

    public LoginPresenterCompl(ILoginView iLoginView){
        this.iLoginView = iLoginView;
        initUser();
        handler = new Handler(Looper.getMainLooper());
    }

    private void initUser(){
        iUser = new UserModel("mvp","mvp");
    }

    @Override
    public void clear() {
        iLoginView.onClearText();
    }

    @Override
    public void doLogin(String name, String passwd) {
        Boolean isLoginSuccess = true;
        final int code = iUser.checkUserValidity(name,passwd);
        if (code!=0) isLoginSuccess = false;
        final Boolean result = isLoginSuccess;
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                iLoginView.onLoginResult(result, code);
            }
        }, 3000);

    }

    @Override
    public void setProgressBarVisiblity(int visiblity) {
        iLoginView.onSetProgressBarVisibility(visiblity);
    }
}
