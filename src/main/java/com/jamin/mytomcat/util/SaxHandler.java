package com.jamin.mytomcat.util;

import com.jamin.mytomcat.core.ServletMapping;
import org.apache.commons.lang3.StringUtils;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * @author jamin
 * @date 2020/8/3 14:55
 * 解析xml的监听
 */
public class SaxHandler extends DefaultHandler {
    List<ServletMapping> servletMappings = new ArrayList<>();
    ServletMapping servletMapping = null;
    String tagName = null;
    String tagValue = null;

    public List<ServletMapping> getServletMappings() {
        return servletMappings;
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        // 当前serverMapping开始
        if (qName.equals("serverMapping")) {
            servletMapping = new ServletMapping();
        }
        // 记住标签名称
        this.tagName = qName;
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        // 当前serverMapping结束
        if (qName.equals("serverMapping")) {
            servletMappings.add(servletMapping);
        }
    }
    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        super.characters(ch, start, length);
        tagValue = new String(ch, start, length);
        //获取属性名称
        Field[] fields = ServletMapping.class.getDeclaredFields();
        // 遍历属性名
        for (Field field : fields) {
            field.setAccessible(true);
            //属性名称
            String fieldName = field.getName();
            //如果匹配上
            if (null != servletMapping && StringUtils.isNotBlank(tagValue) && tagName.equals(fieldName)) {
                //获取首字母大写
                String firstLetter = fieldName.substring(0, 1).toUpperCase();
                //拼接set方法名
                String setter = "set" + firstLetter + fieldName.substring(1);
                //反射执行set方法
                try {
                    Method method = ServletMapping.class.getMethod(setter, field.getType());
                    method.invoke(servletMapping, tagValue);
                    break;
                } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
                    e.printStackTrace();
                }
            }

        }
        tagValue = null;
        tagName = null;


    }

}
