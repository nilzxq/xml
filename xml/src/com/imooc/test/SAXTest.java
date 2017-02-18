package com.imooc.test;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.SAXException;

/**
 * @author nilzxq
 * @date：2017年2月18日  下午3:11:36
 * @version 1.0
 */
public class SAXTest {
        public static void main(String[] args) {
//        	通过SAX解析XML文件步骤：
//        	1：获取一个SAXParserFactory实例
        	SAXParserFactory factory = SAXParserFactory.newInstance();
//        	2:通过factory的方法获取SAXParser对象
        	try {
				SAXParser parser = factory.newSAXParser();
//        	3：创建一个类继承DefaultHandler 并重写其中的一些方法进行业务处理，创建该类对象handler
//        	用父类的startElement方法遍历开始标签，用endElement方法遍历结束标签，
//        	startDocument方法标志解析开始，endDocument方法标志解析结束
				
			} catch (ParserConfigurationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SAXException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

//        	4：用SAXParser对象的parse方法接收XML文件选择传入String uri 和 Handler对象的parse方法
		}
}
