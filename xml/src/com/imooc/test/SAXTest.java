package com.imooc.test;

import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.SAXException;

import com.imooc.entity.Book;
import com.imooc.handler.SAXParserHandler;

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
				SAXParserHandler handler=new SAXParserHandler();
//	        	4����SAXParser�����parse��������XML�ļ�ѡ����String uri �� Handler�����parse����
				parser.parse("books.xml", handler);
				System.out.println("~!����������������"+handler.getBookList().size()+"����");
				for (Book book:handler.getBookList()) {
					System.out.println(book.getId());
					System.out.println(book.getName());
					System.out.println(book.getAuthor());
					System.out.println(book.getYear());
					System.out.println(book.getPrice());
					System.out.println(book.getLanguage());
					System.out.println("---finish---");
				}
			} catch (ParserConfigurationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SAXException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}


		}
}
