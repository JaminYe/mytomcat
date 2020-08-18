package com.jamin.mytomcat.util;

import com.jamin.mytomcat.core.ServletMapping;

import java.util.ArrayList;
import java.util.List;

/**
 * servletMapping的配置文件
 * 在list中配置
 *
 * @author jamin
 * @date 2020/8/3 11:19
 */
public class ServletMappingConfig {

    public static List<ServletMapping> servletMappingList = new ArrayList<>();
    static {
        servletMappingList.add(new ServletMapping("test", "/test", "com.jamin.mytomcat.util.MyServletImpl"));
    }
}
