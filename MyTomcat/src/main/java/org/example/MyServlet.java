package org.example;

import java.io.IOException;

public class MyServlet implements MyHttpServlet{
    @Override
    public void doGet(MyRequest request, MyResponse response) throws IOException {
        response.write("mytomcat");
    }

    @Override
    public void doPost(MyRequest request, MyResponse response) throws IOException {
        response.write("post tomcat");
    }
}
