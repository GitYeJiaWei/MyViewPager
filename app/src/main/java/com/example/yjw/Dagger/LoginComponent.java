package com.example.yjw.Dagger;

import dagger.Component;

/**
 * @Author: YeJW
 * @Date: 2018/6/7 11:31
 * 创建一个LoginComponent接口，用来告诉Activity你要实例化的东西在这里。
 * @Component的参数提供Module进行联系,
 * 接口里面的方法和activty进行联系。 这样形成了桥梁
 * @Description:
 *
 */
@Component(modules = LoginModule.class)
public interface LoginComponent {
    //要添加方法，方法必须添加参数，参数类型必须和调用时候一致
    void inject(TestActivity activity);
    
}
