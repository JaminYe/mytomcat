package com.jamin.mytomcat.entity;

/**
 * @author jamin
 * @date 2020/8/3 11:16
 * url与class对应
 */
public class ServletMapping {
    /**
     * 名称
     */
    private String servletName;
    /**
     * 地址
     */
    private String url;
    /**
     * 类
     */
    private String clazz;

    public String getServletName() {
        return servletName;
    }

    public void setServletName(String servletName) {
        this.servletName = servletName;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getClazz() {
        return clazz;
    }

    public void setClazz(String clazz) {
        this.clazz = clazz;
    }

    public ServletMapping() {
    }

    public ServletMapping(String servletName, String url, String clazz) {
        this.servletName = servletName;
        this.url = url;
        this.clazz = clazz;
    }
}
