package com.nacol.TheRoadToGeek.week_02.socket;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class SocketServer3 {

    public static void main(String[] args) throws IOException {
        ExecutorService executorService = Executors.newFixedThreadPool(
                Runtime.getRuntime().availableProcessors() + 4);
        ServerSocket serverSocket = new ServerSocket(9989);
        while(true) {
            Socket socket = serverSocket.accept();
            executorService.execute(()-> HttpClientSimpeDemo.service(socket));
        }
    }

}
