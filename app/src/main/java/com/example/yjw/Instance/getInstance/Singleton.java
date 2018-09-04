package com.example.yjw.Instance.getInstance;

/**
 * @author YJW
 * @create 2018/6/25
 * @Describe 懒汉式单例类.在第一次调用的时候实例化自己
 */
public class Singleton {
    private static Singleton single;
    //静态工厂方法,线程不安全
    public static Singleton getInstance(){
        if (single==null){
            single =new Singleton();
        }
        return single;
    }

   /* //在getInstance方法上加同步,线程安全
    public static synchronized Singleton getInstance() {
        if (single == null) {
            single = new Singleton();
        }
        return single;
    }*/

    /*//双重检查锁定,线程安全，最实用，应用于需要主动销毁的时候
    public static Singleton getInstance(){
        if (single ==null){
            synchronized (Singleton.class){
                if (single ==null){

                }
            }
        }
        return single;
    }*/
}
