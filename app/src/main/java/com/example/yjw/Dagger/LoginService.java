package com.example.yjw.Dagger;

import android.content.Context;
import android.util.Log;

public class LoginService {
    private Context mcontext;
    public LoginService(Context context){
        this.mcontext =context;
    }

    public void Login(String name,String pass){
        Log.d("login@@", "name"+name+"pass"+pass);

    }

}
