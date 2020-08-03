package com.jamin.mytomcat.service;

import com.jamin.mytomcat.entity.MyRequest;
import com.jamin.mytomcat.entity.MyResponse;

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
            myResponse.write("测试get请求");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void doPost(MyRequest myRequest, MyResponse myResponse) {
        try {
            myResponse.write("测试get请求");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
