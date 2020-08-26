package org.example;

import org.example.servlet.MyServlet;
import org.example.servlet.ServletMapping;
import org.example.servlet.ServletMappingConfig;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

/**
 * Hello world!
 *
 */
public class MyTomcat {

    private static final int port = 8088;
    private static final Map<String, String> urlServletMap = new HashMap<>();

    public static void main( String[] args ) {
        new MyTomcat().start();
    }

    public void start() {
        initServletMapping();

        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(port);
            System.out.print("My Tomcat is listening on 8088...");

            while(true) {
                Socket socket = serverSocket.accept();

                MyRequest myRequest = new MyRequest(socket.getInputStream());
                MyResponse myResponse = new MyResponse(socket.getOutputStream());

                dispatch(myRequest, myResponse);

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

    private void initServletMapping() {
        for (ServletMapping servletMapping : ServletMappingConfig.servletMappings) {
            urlServletMap.put(servletMapping.getUrl(), servletMapping.getClazz());
        }
    }

    private void dispatch(MyRequest myRequest, MyResponse myResponse) {
        String clazz = urlServletMap.get(myRequest.getUrl());

        if (clazz == null) {
            try {
                myResponse.write("not found");
            } catch (IOException e) {
                e.printStackTrace();
            }
            return;
        }

        try {
            Class<MyServlet> myServletClass = (Class<MyServlet>) Class.forName(clazz);
            MyServlet myServlet = myServletClass.newInstance();

            myServlet.service(myRequest, myResponse);
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException e) {
            e.printStackTrace();
        }
    }
}
