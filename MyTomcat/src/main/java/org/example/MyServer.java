package org.example;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class MyServer {
    public static void startServer(int port) throws IOException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        ServerSocket serverSocket = new ServerSocket(port);
        Socket socket = null;

        while(true){
            socket = serverSocket.accept();
            //获取输入流和输出流
            InputStream inputStream = socket.getInputStream();
            OutputStream outputStream = socket.getOutputStream();

            MyRequest request = new MyRequest(inputStream);
            MyResponse response = new MyResponse(outputStream);

            String clazz = MyMapping.getMapping().get(request.getRequestUrl());
            if(clazz!=null){
                Class<MyServlet> myServletClass = (Class<MyServlet>) Class.forName(clazz);
                MyServlet myServlet = myServletClass.newInstance();
                myServlet.service(request,response);
            }
        }
    }

    public static void main(String[] args) throws IOException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        startServer(10086);
    }
}
