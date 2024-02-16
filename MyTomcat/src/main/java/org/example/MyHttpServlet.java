package org.example;

import java.io.IOException;

public interface MyHttpServlet {
    static final String METHOD_GET = "GET";
    static final String METHOD_POST = "POST";
    void doGet(MyRequest request,MyResponse response) throws IOException;
    void doPost(MyRequest request,MyResponse response) throws IOException;

    default void service(MyRequest request,MyResponse response) throws IOException {
        if(METHOD_GET.equals(request.getRequestMethod())){
            doGet(request,response);
        } else if (METHOD_POST.equals(request.getRequestMethod())) {
            doPost(request,response);
        }
    }
}
