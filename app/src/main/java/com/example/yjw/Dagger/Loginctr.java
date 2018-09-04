package com.example.yjw.Dagger;



//要实例化的类
public class Loginctr {
    private LoginService mloginservice;
    private LoginStore mloginstore;

    public Loginctr(LoginStore loginStore,LoginService loginService){
        this.mloginstore =loginStore;
        this.mloginservice =loginService;
    }


    public void login(String name,String pass){
        //Log.d("login@@", "name"+name+"pass"+pass);
        mloginstore.Login(name,pass);
        mloginservice.Login(name,pass);
    }
}
