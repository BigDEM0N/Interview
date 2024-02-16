package org.example;

import lombok.Data;

import java.util.HashMap;

public class MyMapping {
    public static HashMap<String,String> mapping = new HashMap<>();

    static{
        mapping.put("/mytomcat","org.example.MyServlet");
    }

    public static HashMap<String, String> getMapping() {
        return mapping;
    }

}
