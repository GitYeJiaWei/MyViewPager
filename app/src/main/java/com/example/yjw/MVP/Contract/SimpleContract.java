package com.example.yjw.MVP.Contract;

/**
 * @author YJW
 * @create 2018/6/15
 * @Describe Contract层 负责约定view和 presenter层的接口，view和presenter 实现相应接口
 */
public interface SimpleContract {
    interface View {
       // void showSample(SampleInfo sampleInfo);     //显示sample

        void errorGetSample(String msg);    //显示错误信息
    }

    interface Presenter {
        void getNewestSample(); //获取当前最新的xxx
    }

}
