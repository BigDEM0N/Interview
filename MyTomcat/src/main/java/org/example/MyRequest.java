package org.example;

import lombok.Data;

import java.io.IOException;
import java.io.InputStream;

@Data
public class MyRequest {
    //请求方法
    private String requestMethod;
    //请求地址
    private String requestUrl;
    public MyRequest(InputStream inputStream) throws IOException {
        //缓冲
        byte[] buffer = new byte[1024];
        //读取数据的长度
        int len = 0;
        String str = null;
        if((len = inputStream.read(buffer))>0){
            str = new String(buffer,0,len);
        }
        String data = str.split("\n")[0];
        String[] params = data.split( " ");
        this.requestMethod = params[0];
        this.requestUrl = params[1];
    }
}
