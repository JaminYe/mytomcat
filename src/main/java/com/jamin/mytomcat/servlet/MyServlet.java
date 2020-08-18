package com.jamin.mytomcat.servlet;

import com.jamin.mytomcat.baseServlet.AbstractMyServlet;
import com.jamin.mytomcat.core.MyRequest;
import com.jamin.mytomcat.core.MyResponse;

import java.io.IOException;

/**
 * 建立一个测试的servlet
 *
 * @author jamin
 * @date 2020/8/3 11:14
 */
public class MyServlet extends AbstractMyServlet {

    @Override
    public void doGet(MyRequest myRequest, MyResponse myResponse) {
        try {
            // myResponse.write("测试get请求");
            myResponse.sendRedirect("html/te1st.html");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void doPost(MyRequest myRequest, MyResponse myResponse) {
        try {
            myResponse.write("测试post请求");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
