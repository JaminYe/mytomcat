package com.jamin.mytomcat.core;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;

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
    private final String WEB_ROOT = "E:\\Java\\mytomcat\\src\\main\\resources";
    private final int BUFFER_SIZE = 1024;

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
        String httpResponse = "HTTP 1.1 200 OK \n" + "Content-Type:text/html;charset=UTF-8\n" + "\r\n" + "<html><body>" + content + "</body></html>";
        outputStream.write(httpResponse.getBytes());
        outputStream.close();
    }

    /**
     * 转发到文件
     *
     * @author jamin
     * @date 2020/8/18 16:12
     */
    public void sendRedirect(String fileName) throws IOException {
        if ("html".equals(fileName.split("\\.")[1])) {
            File file = new File(WEB_ROOT, fileName);
            if (file.exists() && !file.isDirectory()) {
                FileInputStream fileInputStream = new FileInputStream(file);
                int ch;
                //每次字节数
                byte[] bytes = new byte[BUFFER_SIZE];
                StringBuilder sb = new StringBuilder(BUFFER_SIZE);
                while ((ch = fileInputStream.read(bytes, 0, bytes.length)) != -1) {
                    sb.append(new String(bytes, 0, ch, StandardCharsets.UTF_8));
                }
                String httpResponse = "HTTP/1.1 200 OK\r\n" + "Content-Type: text/html;charset=UTF-8\r\n" + "Content-Length:" + sb.length() + "\r\n" + "\r\n" + sb;
                outputStream.write(httpResponse.getBytes());
                outputStream.close();
                return;
            }
        }
        String message = "<h1>" + fileName + " Not Fount</h1>";
        String httpResponse = "HTTP/1.1 404 FILE NOT Fount\r\n" + "Content-Type: text/html;charset=UTF-8\r\n" + "Content-Length:" + message.length() + "\r\n" + "\r\n" + message;
        outputStream.write(httpResponse.getBytes());
        outputStream.close();
    }
}
