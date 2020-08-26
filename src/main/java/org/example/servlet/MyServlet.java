package org.example.servlet;

import org.example.MyRequest;
import org.example.MyResponse;

public abstract class MyServlet {

    public abstract void doGet(MyRequest myRequest, MyResponse myResponse);

    public abstract void doPost(MyRequest myRequest, MyResponse myResponse);

    public void service(MyRequest myRequest, MyResponse myResponse) {
        switch (myRequest.getMethod().toLowerCase()) {
            case "get" : {
                doGet(myRequest, myResponse);
                break;
            }
            case "post" : {
                doPost(myRequest, myResponse);
                break;
            }
        }
    }
}
