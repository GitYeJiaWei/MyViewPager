package com.example.yjw.Interface;

public class MyThread extends Thread{
    private Sendmessage sendmessage;

    public void run(){
        super.run();
        if (sendmessage !=null){
            sendmessage.send("123");
        }
    }

    //设置回调接口(监听器)的方法

    public void setSendmessage(Sendmessage sendmessage1){
        this.sendmessage=sendmessage1;
    }

    //回调接口(监听器)
    public interface Sendmessage{
        public abstract void send(String string);
    }
}
