package com.jamin.mytomcat.bio;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.net.URL;
import java.net.URLDecoder;
import java.util.Enumeration;
import java.util.LinkedHashSet;

/**
 * 基于bio的tomcat
 *
 * @author jamin
 * @date 2020/8/19 8:26
 */
public class HttpServer {


    /**
     * 扫描包下所有带servlet的类
     *
     * @author jamin
     * @date 2020/8/20 8:23
     */
    public static void getServlets(String packageName) throws IOException {
        LinkedHashSet<Class<?>> classes = new LinkedHashSet<>();

        String packageDir = packageName.replace(".", "\\");
        Enumeration<URL> resources = Thread.currentThread().getContextClassLoader().getResources(packageDir);
        while (resources.hasMoreElements()) {
            URL url = resources.nextElement();
            //获取协议
            String protocol = url.getProtocol();
            if ("file".equals(protocol)) {
                //获取文件路径
                String filePath = URLDecoder.decode(url.getFile(), "UTF-8");
                findAndAddClasses(packageName, filePath, classes);
            }
        }
    }

    private static void findAndAddClasses(String packageName, String filePath, LinkedHashSet<Class<?>> classes) {
        File fileDir = new File(filePath);

        File[] listFiles = fileDir.listFiles(new FileFilter() {
            @Override
            public boolean accept(File pathname) {
                return pathname.isDirectory() || packageName.endsWith(".class");
            }
        });
        listFiles.

    }


}


