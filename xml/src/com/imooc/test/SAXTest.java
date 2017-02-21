package com.imooc.test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Handler;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.stream.util.StreamReaderDelegate;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Result;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.sax.SAXTransformerFactory;
import javax.xml.transform.sax.TransformerHandler;
import javax.xml.transform.stream.StreamResult;

import org.dom4j.io.OutputFormat;
import org.xml.sax.SAXException;
import org.xml.sax.ext.Attributes2Impl;
import org.xml.sax.helpers.AttributesImpl;

import com.imooc.entity.Book;
import com.imooc.handler.SAXParserHandler;

/**
 * @author nilzxq
 * @date��2017��2��18�� ����3:11:36
 * @version 1.0
 */
public class SAXTest {
	/**
	 * ����xml
	 */
	public ArrayList<Book> parseXML() {

		// ͨ��SAX����XML�ļ����裺
		// 1����ȡһ��SAXParserFactoryʵ��
		SAXParserFactory factory = SAXParserFactory.newInstance();
		// 2:ͨ��factory�ķ�����ȡSAXParser����
		SAXParserHandler handler = null;
		try {
			SAXParser parser = factory.newSAXParser();
			// 3������һ����̳�DefaultHandler ����д���е�һЩ��������ҵ���������������handler
			// �ø����startElement����������ʼ��ǩ����endElement��������������ǩ��
			// startDocument������־������ʼ��endDocument������־��������
			handler = new SAXParserHandler();
			// 4����SAXParser�����parse��������XML�ļ�ѡ����String uri �� Handler�����parse����
			parser.parse("books.xml", handler);
			System.out.println("~!����������������" + handler.getBookList().size()
					+ "����");
			for (Book book : handler.getBookList()) {
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
		return handler.getBookList();
	}

	/**
	 * ����xml
	 */
	public void createXML() {
		ArrayList<Book> bookList = parseXML();
		// 1������SAXTransformerFactory���󣬹����಻��ֱ��new����
		SAXTransformerFactory tff = (SAXTransformerFactory) SAXTransformerFactory
				.newInstance();
		try {
			// 2,����TransformerHandler��������XML�ļ��ڵ��Լ����ݵı�д
			TransformerHandler handler = tff.newTransformerHandler();
			// 3,����Transformer�������ڶ����ɵ�XML�ļ��ĸ�ʽ�Լ����������
			Transformer tr = handler.getTransformer();
			// 3.1,ͨ��Transformer�����XML�ļ��ı���
			tr.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
			// 3.2,ͨ��Transformer�����XML�ļ����н�������
			tr.setOutputProperty(OutputKeys.INDENT, "yes");
			// 4,����һ��������result����ʹ��
			File file = new File("books2.xml");
			if (!file.exists())
				file.createNewFile();
			// 5,����Result������ΪXML���ļ������
			Result result = new StreamResult(new FileOutputStream(file));
			// 6,��������������дXML�ļ����ݵ�TransformerHandler������ϵ����
			handler.setResult(result);
			// 7,����handler�������xml�ļ����ݵı�д
			// ��document
			handler.startDocument();
			AttributesImpl attr = new AttributesImpl();
			handler.startElement("", "", "bookstore", attr);
			for (Book book : bookList) {
				attr.clear();
				attr.addAttribute("", "", "id", "", "1");
				handler.startElement("", "", "book", attr);
				
				//����name�ڵ�
				if(book.getName()!=null&&!book.getName().trim().equals("")){
					attr.clear();
				handler.startElement("", "", "name", attr);
				handler.characters(book.getName().toCharArray(), 0, book
						.getName().length());
				handler.endElement("", "", "name");
				}
				//����author�ڵ�
				if(book.getAuthor()!=null&&!book.getAuthor().trim().equals("")){
				attr.clear();
				handler.startElement("", "", "author", attr);
				handler.characters(book.getAuthor().toCharArray(), 0, book
						.getAuthor().length());
				handler.endElement("", "", "author");
				}
				//����year�ڵ�
				if(book.getYear()!=null&&!book.getYear().trim().equals("")){
				attr.clear();
				handler.startElement("", "", "year", attr);
				handler.characters(book.getYear().toCharArray(), 0, book
						.getYear().length());
				handler.endElement("", "", "year");
				}
				//����price�ڵ�
				if(book.getPrice()!=null&&!book.getPrice().trim().equals("")){
				attr.clear();
				handler.startElement("", "", "price", attr);
				handler.characters(book.getPrice().toCharArray(), 0, book
						.getPrice().length());
				handler.endElement("", "", "price");
				}
				//����language�ڵ�
				if(book.getLanguage()!=null&&!book.getLanguage().trim().equals("")){
				attr.clear();
				handler.startElement("", "", "language", attr);
				handler.characters(book.getLanguage().toCharArray(), 0, book
						.getLanguage().length());
				handler.endElement("", "", "language");
				}
				handler.endElement("", "", "book");
			}
				handler.endElement("", "", "bookstore");
			// �ر�document
			handler.endDocument();
		} catch (TransformerConfigurationException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {

		SAXTest test = new SAXTest();
		test.createXML();
	}
}
