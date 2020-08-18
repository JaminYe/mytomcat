package com.jamin.mytomcat.core;

import com.sun.org.slf4j.internal.Logger;
import com.sun.org.slf4j.internal.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author jamin
 * @date 2020/7/31 18:03
 * 封装请求类
 */
public class MyRequest {
    public Logger logger = LoggerFactory.getLogger(this.getClass());
    /**
     * url地址
     */
    private String url;
    /**
     * 方法
     */
    private String method;

    /**
     * 从请求中获取url与请求方式
     *
     * @author jamin
     * @date 2020/8/3 14:45
     */
    public MyRequest(InputStream inputStream) throws IOException {
        String httpRequest = "";
        byte[] httpRequestBytes = new byte[1024];
        int length = 0;
        if ((length = inputStream.read(httpRequestBytes)) > 0) {
            httpRequest = new String(httpRequestBytes, 0, length);
        }
        String httpHead = httpRequest.split("\r\n")[0];
        url = httpHead.split("\\s")[1];
        method = httpHead.substring(0, 3);
        System.out.println("接收到" + method + "请求--------路径为" + url);
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }
}
