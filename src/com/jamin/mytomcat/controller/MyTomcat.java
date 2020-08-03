package com.jamin.mytomcat.controller;

import com.jamin.mytomcat.entity.MyRequest;
import com.jamin.mytomcat.entity.MyResponse;
import com.jamin.mytomcat.entity.ServletMapping;
import com.jamin.mytomcat.service.AbstractMyServlet;
import com.jamin.mytomcat.util.ServletMappingConfig;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

import static java.lang.Class.forName;

/**
 * @author jamin
 * @date 2020/8/3 11:25
 */
public class MyTomcat {
    private int port = 8080;
    private Map<String, String> hashMap = new HashMap<>();

    public MyTomcat(int port) {
        this.port = port;
    }

    public MyTomcat() {
    }

    public void start() {
        initServletMapping();
        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(port);
            System.out.println("MyTomcat start successful port is " + port);
            while (true) {
                Socket socket = serverSocket.accept();
                InputStream inputStream = socket.getInputStream();
                OutputStream outputStream = socket.getOutputStream();
                MyRequest myRequest = new MyRequest(inputStream);
                MyResponse myResponse = new MyResponse(outputStream);
                dispatch(myRequest, myResponse);
            }


        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    public void initServletMapping() {
        for (ServletMapping servletMapping : ServletMappingConfig.servletMappingList) {
            hashMap.put(servletMapping.getUrl(), servletMapping.getClazz());
        }
    }


    public void dispatch(MyRequest myRequest, MyResponse myResponse) {
        String clazz = hashMap.get(myRequest.getUrl());
        try {
            Class<AbstractMyServlet> myServletClass = (Class<AbstractMyServlet>) forName(clazz);
            AbstractMyServlet abstractMyServlet = myServletClass.newInstance();
            abstractMyServlet.service(myRequest, myResponse);
        } catch (Exception ex) {

        }
    }

    public static void main(String[] args) {
        new MyTomcat().start();
    }
}
