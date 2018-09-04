package com.example.yjw.Dagger;

import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/*在这个类里面 真正的实例化Loginctr类。创建一个LoginModule类，类使用@Module修饰，
 然后里面添加方法intLoginModel (方法名随便)，返回类型为LoginCtrl，使用@Provides修饰方法名*/
@Module
public class LoginModule {
    private Context mcontext;

    public LoginModule(Context context){
        this.mcontext=context;
    }

    /**
     * 为provideLoginCtrl方法的store参数提供实例化
     * @return
     */
    @Provides
    public LoginStore intLoginStore(){
        return new LoginStore(mcontext);
    }

    /**
     * 为provideLoginCtrl方法的service参数提供实例化
     * @return
     */
    @Provides
    public LoginService intLoginService(){
        return  new LoginService(mcontext);
    }

    /**
     * 实例化Loginctr
     * @param loginStore
     * @param loginService
     * @return
     */
    @Provides Loginctr intLoginModel(LoginStore loginStore,LoginService loginService){
        return new Loginctr(loginStore,loginService);
    }
}
