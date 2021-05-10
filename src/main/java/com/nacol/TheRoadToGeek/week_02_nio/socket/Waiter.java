package com.nacol.TheRoadToGeek.week_02_nio.socket;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

public class Waiter {

    public static void service(Socket socket) {
        try {
            PrintWriter printWriter = new PrintWriter(socket.getOutputStream(), true);
            printWriter.println("HTTP/1.1 200 OK");
            printWriter.println("Content-Type:text/html;charset=utf-8");
            String body = "helle ^@^ NIO";
            printWriter.println("Content-type:" + body.getBytes().length);
            printWriter.println();
            printWriter.write(body);
            printWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
