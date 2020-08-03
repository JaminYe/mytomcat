package com.jamin.mytomcat.util;

import com.jamin.mytomcat.entity.ServletMapping;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.IOException;
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

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        super.startElement(uri, localName, qName, attributes);
        if (qName.equals("serverMapping")) {
            servletMapping = new ServletMapping();
        } else if (!qName.equals("server")) {
            System.out.print(qName + "------");
        }


    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        super.endElement(uri, localName, qName);

    }





    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        super.characters(ch, start, length);
        String value = new String(ch, start, length);
        if (!value.equals("")) {
            System.out.println(value);
        }
    }

    public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException {
        SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
        SAXParser saxParser = saxParserFactory.newSAXParser();
        SaxHandler saxHandler = new SaxHandler();
        saxParser.parse("src/com/jamin/mytomcat/test.xml", saxHandler);
    }
}
