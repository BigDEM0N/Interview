package org.example;

import lombok.Data;

import java.io.IOException;
import java.io.OutputStream;
@Data
public class MyResponse {
    private OutputStream outputStream;
    public MyResponse(OutputStream outputStream){
        this.outputStream = outputStream;
    }
    public void write(String str) throws IOException {
        StringBuilder builder = new StringBuilder();
        builder.append("HTTP/1.1 200 OK\n")
                .append("Content-Type:text/html\n")
                .append("\r\n")
                .append(("<html>"))
                .append("<body>").append("<h1>").append(str).append("</h1>")
                .append("</body>")
                .append("</html>");
        this.outputStream.write(builder.toString().getBytes());
        this.outputStream.flush();
        this.outputStream.close();
    }
}
