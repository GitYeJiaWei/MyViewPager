package com.example.yjw.Socket.UDPService;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.example.yjw.myviewpager.R;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class UDPActivity extends AppCompatActivity {
    private static int PORT = 5600;
    private String TAG = "Socket2";
    private DatagramSocket socket = null;
    private boolean isRead = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_udpservice);

        threadt1 thread1 = new threadt1();
        thread1.start();
    }

    class threadt1 extends Thread{
        @Override
        public void run() {

            try {
                socket = new DatagramSocket(PORT);//建立 socket，其中 5600 为端口号
            } catch (Exception e) {
                e.printStackTrace();
            }
            while (isRead) {
                byte data[] = new byte[1024];
                DatagramPacket packet = new DatagramPacket(data, data.length);
                try {
                    socket.receive(packet);//阻塞式，接收发送方的 packet
                } catch (IOException e) {
                    e.printStackTrace();
                }
                String result = new String(packet.getData(), packet.getOffset(), packet.getLength());//packet 转换
                Log.d(TAG, "UDP result: " + result);
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (isRead){
            isRead = false;//断开线程，停止socket
            socket.close();
        }
    }
}
