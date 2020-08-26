package org.example;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Hello world!
 *
 */
public class MyTomcat {
    public static final int port = 8088;

    public static void main( String[] args ) {
        new MyTomcat().start();
    }

    public void start() {
        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(port);
            System.out.print("My Tomcat is listening on 8088...");

            while(true) {
                Socket socket = serverSocket.accept();

                MyRequest myRequest = new MyRequest(socket.getInputStream());
                MyResponse myResponse = new MyResponse(socket.getOutputStream());

                myResponse.write("hello!");

                socket.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (serverSocket != null) {
                try {
                    serverSocket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
