package com.jamin.mytomcat.singleThread;

import com.jamin.mytomcat.baseServlet.AbstractMyServlet;
import com.jamin.mytomcat.core.MyRequest;
import com.jamin.mytomcat.core.MyResponse;
import com.jamin.mytomcat.core.ServletMapping;
import com.jamin.mytomcat.util.SaxHandler;
import com.sun.org.slf4j.internal.Logger;
import com.sun.org.slf4j.internal.LoggerFactory;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.lang.Class.forName;

/**
 * @author jamin
 * @date 2020/8/3 11:25
 */
public class MyTomcat {
    Logger logger = LoggerFactory.getLogger(this.getClass());
    /**
     * 默认8080端口
     */
    private int port = 8080;

    private Map<String, String> hashMap = new HashMap<>();

    public MyTomcat(int port) {
        this.port = port;
    }

    public MyTomcat() {
    }

    /**
     * 启动
     *
     * @author jamin
     * @date 2020/8/13 8:56
     */
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

    /**
     * 获取集合遍历放入hashmap中
     *
     * @author jamin
     * @date 2020/8/13 8:55
     */
    public void initServletMapping() {

        // 在手动配置servletMapping到list
       /* for (ServletMapping servletMapping : ServletMappingConfig.servletMappingList) {
            hashMap.put(servletMapping.getUrl(), servletMapping.getClazz());
        }*/
        //使用解析xml的方式
        SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
        SAXParser saxParser = null;
        try {
            saxParser = saxParserFactory.newSAXParser();
            SaxHandler saxHandler = new SaxHandler();
            saxParser.parse("src/main/resources/servlet.xml", saxHandler);
            List<ServletMapping> servletMappings = saxHandler.getServletMappings();
            for (ServletMapping servletMapping : servletMappings) {
                hashMap.put(servletMapping.getUrl(), servletMapping.getClazz());
            }
        } catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
        }


    }

    /**
     * 分发请求   从请求投头中获取url并在集合中获取找到指定的类并执行方法返回
     *
     * @author jamin
     * @date 2020/8/13 8:54
     */
    public void dispatch(MyRequest myRequest, MyResponse myResponse) {
        String clazz = hashMap.get(myRequest.getUrl());
        try {
            Class<AbstractMyServlet> myServletClass = (Class<AbstractMyServlet>) forName(clazz);
            AbstractMyServlet abstractMyServlet = myServletClass.newInstance();
            abstractMyServlet.service(myRequest, myResponse);
            System.out.println("处理完成" + myRequest.getUrl());
        } catch (Exception ex) {

        }
    }

    public static void main(String[] args) {
        new MyTomcat().start();
    }
}
