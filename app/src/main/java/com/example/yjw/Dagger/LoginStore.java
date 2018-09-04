package com.example.yjw.Dagger;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

/**

 * @Author: YeJW

 * @Date: 2018/6/7 11:59
 * @Description: LoginStore.java类，看作为本地保存登录信息的类

 *

 */
public class LoginStore {
    private Context mcontext;
    public LoginStore(Context context){
        this.mcontext=context;
    }

    public void Login(String name,String pass){
        Log.d("login@@", "name"+name+"pass"+pass);
        SharedPreferences.Editor editor =mcontext.getSharedPreferences("xiaohua",Context.MODE_PRIVATE).edit();
        editor.putString("name","123455");
        editor.putString("pass","pass");
        editor.apply();
    }
}
