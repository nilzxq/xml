package com.imooc.test;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.SAXException;

/**
 * @author nilzxq
 * @date��2017��2��18��  ����3:11:36
 * @version 1.0
 */
public class SAXTest {
        public static void main(String[] args) {
//        	ͨ��SAX����XML�ļ����裺
//        	1����ȡһ��SAXParserFactoryʵ��
        	SAXParserFactory factory = SAXParserFactory.newInstance();
//        	2:ͨ��factory�ķ�����ȡSAXParser����
        	try {
				SAXParser parser = factory.newSAXParser();
//        	3������һ����̳�DefaultHandler ����д���е�һЩ��������ҵ���������������handler
//        	�ø����startElement����������ʼ��ǩ����endElement��������������ǩ��
//        	startDocument������־������ʼ��endDocument������־��������
				
			} catch (ParserConfigurationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SAXException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

//        	4����SAXParser�����parse��������XML�ļ�ѡ����String uri �� Handler�����parse����
		}
}
