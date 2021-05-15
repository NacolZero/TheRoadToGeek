package com.nacol.TheRoadToGeek.week_02.socket;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;


public class SocketServer2 {


    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(9989);
        while(true) {
            Socket socket = serverSocket.accept();
            //无脑开启线程，没有管理线程生命周期。开启关闭线程会浪费很多资源资源。
            new Thread(()->
                    BaseService.handle(socket)
            ).start();
        }
    }
}
