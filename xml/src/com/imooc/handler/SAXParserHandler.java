package com.imooc.handler;

import java.util.ArrayList;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import com.imooc.entity.Book;

/**
 * @author nilzxq
 * @date��2017��2��18�� ����3:26:42
 * @version 1.0
 */
public class SAXParserHandler extends DefaultHandler {
	int bookIndex=0;
	Book book=null;
	String value=null;
	private ArrayList<Book> bookList=new ArrayList<>();
	public ArrayList<Book> getBookList() {
		return bookList;
	}

	/*
	 * ��������xml�ļ��Ŀ�ʼ��ǩ
	 */
	@Override
	public void startElement(String uri, String localName, String qName,
			Attributes attributes) throws SAXException {
      //����DefaultHanlder���startElement����
		super.startElement(uri, localName, qName, attributes);
		if(qName.equals("book")){
			//����һ��book����
			book=new Book();
			bookIndex++;
			//��ʼ����bookԪ�ص�����
			System.out.println("==============���濪ʼ������"+bookIndex+"���������==============");
//			//��֪bookԪ���µ��������ƣ������������ƻ�ȡ����ֵ
//			String value=attributes.getValue("id");
//			System.out.println("book������ֵ�ǣ�"+value);
			//��֪��bookԪ�������Ե������Լ���������λ�ȡ�������Լ�����ֵ
			int num=attributes.getLength();
			for(int i=0;i<num;i++){
				System.out.println("bookԪ�صĵ�"+(i+1)+"���������ǣ�"+attributes.getQName(i)+"---����ֵ�ǣ�"+attributes.getValue(i));
			if(attributes.getQName(i).equals("id"))
				book.setId(attributes.getValue(i));
			}
		}else if(!qName.equals("book")&&!qName.equals("bookstore")){
		System.out.print("�ڵ����ǣ�"+qName);
		}
}

	/*
	 * ��������xml�ļ��Ľ�����ǩ
	 */
	@Override
	public void endElement(String uri, String localName, String qName)
			throws SAXException {
		//����DefaultHanlder���endElement����
		super.endElement(uri, localName, qName);
		if(qName.equals("book")){
			bookList.add(book);
		book=null;
		System.out.println("==============����������"+bookIndex+"���������==============");
		}else if(qName.equals("name")){
			book.setName(value);
		}else if(qName.equals("author"))
			book.setAuthor(value);
		else if(qName.equals("year"))
			book.setYear(value);
		else if(qName.equals("price"))
			book.setPrice(value);
		else if(qName.equals("language"))
			book.setLanguage(value);
	}
   
	/*
	 * ������ʶ������ʼ
	 */
	@Override
	public void startDocument() throws SAXException {
		// TODO Auto-generated method stub
		super.startDocument();
		System.out.println("SAX������ʼ");
	}

	/*
	 * ������ʶ��������
	 */
	@Override
	public void endDocument() throws SAXException {
		// TODO Auto-generated method stub
		super.endDocument();
		System.out.println("SAX��������");
	}

	@Override
	public void characters(char[] ch, int start, int length)
			throws SAXException {
		// TODO Auto-generated method stub
		super.characters(ch, start, length);
		value=new String(ch, start, length);
		if(!value.trim().equals(""))
		System.out.println("---�ڵ�ֵ�ǣ�"+value);
	}
}
