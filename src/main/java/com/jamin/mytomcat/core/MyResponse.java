package com.jamin.mytomcat.core;

import java.io.IOException;
import java.io.OutputStream;

/**
 * @author jamin
 * @date 2020/8/3 10:55
 * 封装相应类
 */
public class MyResponse {
    /**
     * 输出流
     */
    private OutputStream outputStream;

    public MyResponse(OutputStream outputStream) {
        this.outputStream = outputStream;
    }
/**
 * 拼接响应
 *
 * @author jamin
 * @date 2020/8/13 8:57
 */
    public void write(String content) throws IOException {
        StringBuilder httpResponse = new StringBuilder();
        httpResponse.append("HTTP 1.1 200 OK \n").append("Content-Type:text/html;charset=UTF-8\n").append("\r\n").append("<html><body>").append(content).append("</body></html>");
        outputStream.write(httpResponse.toString().getBytes());
        outputStream.close();
    }
}
