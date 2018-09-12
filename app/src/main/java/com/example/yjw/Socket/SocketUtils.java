package com.example.yjw.Socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;

/**
 * Socket套接字的使用步骤
 */
public class SocketUtils {
    /**
     * 步骤一：创建客户端&服务器的连接
     */
    //创建Socket对象&指定服务器的IP和端口号
    Socket socket;
    {
        try {
            socket = new Socket("192.168.1.1",8080);

            //判断客户端和服务器是否连接成功
            socket.isConnected();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 步骤二：客户端&服务器端 通信
     */
    //通信包括：客户端接受服务器的数据 & 发送数据到服务器端

    /*操作1：接受服务器的数据*/
    //第一：创建输入流对象InputStream
    InputStream is;
    {
        try {
            is = socket.getInputStream();

            //第二：创建输入流读取对象并传入输入流对象
            //该对象作用：获取服务器返回的数据
            InputStreamReader isr = new InputStreamReader(is);
            BufferedReader br = new BufferedReader(isr);

            //第三：通过输入流读取器对象接受服务器发送过来的数据
            br.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /*操作2：发送数据到服务器*/
    //第一：从Socket 获得输出流对象OutputStream
    //该对象作用：发送数据
    OutputStream outputStream;
    {
        try {
            outputStream = socket.getOutputStream();

            //第二：写入需要发送的数据到输出流对象中
            outputStream.write(("Carson-Ho"+"\n").getBytes("utf-8"));
            //特别注意：数据的结尾加上换行符才可让服务器端的readline()停止阻塞

            //第三：发送数据到服务器
            outputStream.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 步骤三：断开客户端&服务器连接
     */
    //outputStream.close();
    //断开 客户端发送到服务器的连接，即关闭输出流对象outputStream

    //br.close();
    //断开 服务器发送到客户端的连接，即关闭输入流读取器对象BufferedReader

    //socket.close();
    //最终关闭整个Socket连接
}
