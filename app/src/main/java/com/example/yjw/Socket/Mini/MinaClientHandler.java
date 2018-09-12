package com.example.yjw.Socket.Mini;

import android.util.Log;

import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IoSession;

public class MinaClientHandler extends IoHandlerAdapter {
    String value;
    public MinaClientHandler(String value){
        this.value = value;
    }

    @Override
    public void sessionOpened(IoSession session) throws Exception {
        super.sessionOpened(session);
        Log.i("Socket", "向服务端发送数据"+value);
        //session.write(value);
    }

    @Override
    public void exceptionCaught(IoSession session, Throwable cause) throws Exception {
        Log.i("Socket", "客户端链接发生异常");
        super.exceptionCaught(session, cause);
    }

    @Override
    public void messageReceived(IoSession session, Object message) throws Exception {
        String msg = message.toString();
        Log.i("Socket", "i客户端接收到的信息为:" + msg);
        super.messageReceived(session, message);
    }

    @Override
    public void messageSent(IoSession session, Object message) throws Exception {
        // TODO Auto-generated method stub
        super.messageSent(session, message);
    }
}

