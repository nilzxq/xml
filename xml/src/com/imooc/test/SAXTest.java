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
 * @date：2017年2月18日 下午3:11:36
 * @version 1.0
 */
public class SAXTest {
	/**
	 * 解析xml
	 */
	public ArrayList<Book> parseXML() {

		// 通过SAX解析XML文件步骤：
		// 1：获取一个SAXParserFactory实例
		SAXParserFactory factory = SAXParserFactory.newInstance();
		// 2:通过factory的方法获取SAXParser对象
		SAXParserHandler handler = null;
		try {
			SAXParser parser = factory.newSAXParser();
			// 3：创建一个类继承DefaultHandler 并重写其中的一些方法进行业务处理，创建该类对象handler
			// 用父类的startElement方法遍历开始标签，用endElement方法遍历结束标签，
			// startDocument方法标志解析开始，endDocument方法标志解析结束
			handler = new SAXParserHandler();
			// 4：用SAXParser对象的parse方法接收XML文件选择传入String uri 和 Handler对象的parse方法
			parser.parse("books.xml", handler);
			System.out.println("~!　～！　～！共有" + handler.getBookList().size()
					+ "本书");
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
	 * 生成xml
	 */
	public void createXML() {
		ArrayList<Book> bookList = parseXML();
		// 1，创建SAXTransformerFactory对象，工厂类不能直接new对象
		SAXTransformerFactory tff = (SAXTransformerFactory) SAXTransformerFactory
				.newInstance();
		try {
			// 2,创建TransformerHandler对象，用于XML文件节点以及内容的编写
			TransformerHandler handler = tff.newTransformerHandler();
			// 3,创建Transformer对象，用于对生成的XML文件的格式以及编码的设置
			Transformer tr = handler.getTransformer();
			// 3.1,通过Transformer对象对XML文件的编码
			tr.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
			// 3.2,通过Transformer对象对XML文件换行进行设置
			tr.setOutputProperty(OutputKeys.INDENT, "yes");
			// 4,创建一个流对象供result对象使用
			File file = new File("books2.xml");
			if (!file.exists())
				file.createNewFile();
			// 5,创建Result对象，作为XML的文件输出流
			Result result = new StreamResult(new FileOutputStream(file));
			// 6,将输出流对象与编写XML文件内容的TransformerHandler对象联系起来
			handler.setResult(result);
			// 7,利用handler对象进行xml文件内容的编写
			// 打开document
			handler.startDocument();
			AttributesImpl attr = new AttributesImpl();
			handler.startElement("", "", "bookstore", attr);
			for (Book book : bookList) {
				attr.clear();
				attr.addAttribute("", "", "id", "", "1");
				handler.startElement("", "", "book", attr);
				
				//创建name节点
				if(book.getName()!=null&&!book.getName().trim().equals("")){
					attr.clear();
				handler.startElement("", "", "name", attr);
				handler.characters(book.getName().toCharArray(), 0, book
						.getName().length());
				handler.endElement("", "", "name");
				}
				//创建author节点
				if(book.getAuthor()!=null&&!book.getAuthor().trim().equals("")){
				attr.clear();
				handler.startElement("", "", "author", attr);
				handler.characters(book.getAuthor().toCharArray(), 0, book
						.getAuthor().length());
				handler.endElement("", "", "author");
				}
				//创建year节点
				if(book.getYear()!=null&&!book.getYear().trim().equals("")){
				attr.clear();
				handler.startElement("", "", "year", attr);
				handler.characters(book.getYear().toCharArray(), 0, book
						.getYear().length());
				handler.endElement("", "", "year");
				}
				//创建price节点
				if(book.getPrice()!=null&&!book.getPrice().trim().equals("")){
				attr.clear();
				handler.startElement("", "", "price", attr);
				handler.characters(book.getPrice().toCharArray(), 0, book
						.getPrice().length());
				handler.endElement("", "", "price");
				}
				//创建language节点
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
			// 关闭document
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
