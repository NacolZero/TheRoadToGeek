package com.nacol.TheRoadToGeek.week_02_nio.socket;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;


public class SocketServer1 {

    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(9989);
        while(true) {
            Socket socket = serverSocket.accept();
            Waiter.service(socket);
        }

    }



}
