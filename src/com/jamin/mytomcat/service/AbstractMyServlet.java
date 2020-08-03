package com.jamin.mytomcat.service;

import com.jamin.mytomcat.entity.MyRequest;
import com.jamin.mytomcat.entity.MyResponse;

/**
 * @author jamin
 * @date 2020/8/3 11:05
 */
    public abstract class AbstractMyServlet {

    /**
     * get请求
     *
     * @author jamin
     * @date 2020/8/3 11:10
     */
    public abstract void doGet(MyRequest myRequest, MyResponse myResponse);

    /**
     * post请求
     *
     * @author jamin
     * @date 2020/8/3 11:10
     */
    public abstract void doPost(MyRequest myRequest, MyResponse myResponse);

    public void service(MyRequest myRequest, MyResponse myResponse) {
        String method = myRequest.getMethod();
        String post = "POST";
        String get = "GET";
        if (post.equalsIgnoreCase(method)) {
            doPost(myRequest, myResponse);
        } else if (get.equalsIgnoreCase(method)) {
            doGet(myRequest, myResponse);
        }
    }
}
