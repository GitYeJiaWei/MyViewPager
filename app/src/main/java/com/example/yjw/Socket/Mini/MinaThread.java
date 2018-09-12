package com.example.yjw.Socket.Mini;

import android.util.Log;

import org.apache.mina.core.future.ConnectFuture;
import org.apache.mina.core.service.IoConnector;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.codec.textline.LineDelimiter;
import org.apache.mina.filter.codec.textline.TextLineCodecFactory;
import org.apache.mina.transport.socket.nio.NioSocketConnector;

import java.net.InetSocketAddress;
import java.nio.charset.Charset;
import java.util.Date;

public class MinaThread extends Thread{
    private IoSession session = null;
    private IoConnector connector = null;
    public static String TAG ="Socket";

    @Override
    public void run() {
        super.run();
        // TODO Auto-generated method stub]
        Log.d(TAG,"客户端链接开始..."+new Date());
        connector = new NioSocketConnector();
        // 设置链接超时时间
        connector.setConnectTimeoutMillis(10000);
        // 添加过滤器
        // connector.getFilterChain().addLast("codec", new
        // ProtocolCodecFilter(new CharsetCodecFactory()));
        connector.getFilterChain().addLast("codec",
                new ProtocolCodecFilter(new TextLineCodecFactory(Charset.forName("UTF-8"),
                        LineDelimiter.WINDOWS.getValue(), LineDelimiter.WINDOWS.getValue())));
        connector.setHandler(new MinaClientHandler("start"));
        connector.setDefaultRemoteAddress(new InetSocketAddress(ConstantUtil.WEB_MATCH_PATH, ConstantUtil.WEB_MATCH_PORT));
        // 监听客户端是否断线
        connector.addListener(new IoListener() {
            @Override
            public void sessionDestroyed(IoSession arg0) throws Exception {
                // TODO Auto-generated method stub
                super.sessionDestroyed(arg0);

                    int failCount = 0;
                    while (true) {
                        try {
                        Thread.sleep(5000);
                        Log.d(TAG,((InetSocketAddress) connector.getDefaultRemoteAddress()).getAddress()
                                .getHostAddress());
                        ConnectFuture future = connector.connect();
                        Log.d(TAG,"断线2");
                        future.awaitUninterruptibly();// 等待连接创建完成
                         Log.d(TAG,"断线3");
                        session = future.getSession();// 获得session
                         Log.d(TAG,"断线4");
                        if (session != null && session.isConnected()) {
                             Log.d(TAG,"断线5");
                             Log.d(TAG,"断线重连["
                                    + ((InetSocketAddress) session.getRemoteAddress()).getAddress().getHostAddress()
                                    + ":" + ((InetSocketAddress) session.getRemoteAddress()).getPort() + "]成功");
                            session.write("start");
                            break;
                        } else {
                             Log.d(TAG,"断线重连失败---->" + failCount + "次");
                        }
                        } catch (Exception e) {
                            Log.d(TAG,"断线重连失败");
                        }
                    }
            }
        });
        //开始连接
        try {
            ConnectFuture future = connector.connect();
            future.awaitUninterruptibly();// 等待连接创建完成
            session = future.getSession();// 获得session
            if (session != null && session.isConnected()) {
                session.write("start");
            } else {
                 Log.d(TAG,"写数据失败");
            }

        } catch (Exception e) {
             Log.d(TAG,"客户端链接异常...");
        }
        if (session != null && session.isConnected()) {
            session.getCloseFuture().awaitUninterruptibly();// 等待连接断开
             Log.d(TAG,"客户端断开111111...");
            // connector.dispose();//彻底释放Session,退出程序时调用不需要重连的可以调用这句话，也就是短连接不需要重连。长连接不要调用这句话，注释掉就OK。
        }

    }
}
