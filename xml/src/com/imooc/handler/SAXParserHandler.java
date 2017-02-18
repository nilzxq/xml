package com.imooc.handler;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
 * @author nilzxq
 * @date：2017年2月18日 下午3:26:42
 * @version 1.0
 */
public class SAXParserHandler extends DefaultHandler {
	int bookIndex=0;
	/*
	 * 用来遍历xml文件的开始标签
	 */
	@Override
	public void startElement(String uri, String localName, String qName,
			Attributes attributes) throws SAXException {
      //调用DefaultHanlder类的startElement方法
		super.startElement(uri, localName, qName, attributes);
		if(qName.equals("book")){
			bookIndex++;
			//开始解析book元素的属性
			System.out.println("==============下面开始遍历第"+bookIndex+"本书的内容==============");
//			//已知book元素下的属性名称，根据属性名称或取属性值
//			String value=attributes.getValue("id");
//			System.out.println("book的属性值是："+value);
			//不知道book元素下属性的名称以及个数，如何获取属性名以及属性值
			int num=attributes.getLength();
			for(int i=0;i<num;i++){
				System.out.println("book元素的第"+(i+1)+"个属性名是："+attributes.getQName(i)+"---属性值是："+attributes.getValue(i));
			}
		}else if(!qName.equals("book")&&!qName.equals("bookstore")){
		System.out.print("节点名是："+qName);
		}
}

	/*
	 * 用来遍历xml文件的结束标签
	 */
	@Override
	public void endElement(String uri, String localName, String qName)
			throws SAXException {
		//调用DefaultHanlder类的endElement方法
		super.endElement(uri, localName, qName);
		if(qName.equals("book"))
		System.out.println("==============结束遍历第"+bookIndex+"本书的内容==============");
		
	}
   
	/*
	 * 用来标识解析开始
	 */
	@Override
	public void startDocument() throws SAXException {
		// TODO Auto-generated method stub
		super.startDocument();
		System.out.println("SAX解析开始");
	}

	/*
	 * 用来标识解析结束
	 */
	@Override
	public void endDocument() throws SAXException {
		// TODO Auto-generated method stub
		super.endDocument();
		System.out.println("SAX解析结束");
	}

	@Override
	public void characters(char[] ch, int start, int length)
			throws SAXException {
		// TODO Auto-generated method stub
		super.characters(ch, start, length);
		String value=new String(ch, start, length);
		if(!value.trim().equals(""))
		System.out.println("---节点值是："+value);
	}
}
