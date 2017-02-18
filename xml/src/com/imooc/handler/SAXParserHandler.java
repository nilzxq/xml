package com.imooc.handler;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
 * @author nilzxq
 * @date��2017��2��18�� ����3:26:42
 * @version 1.0
 */
public class SAXParserHandler extends DefaultHandler {
	/*
	 * ��������xml�ļ��Ŀ�ʼ��ǩ
	 */
	@Override
	public void startElement(String uri, String localName, String qName,
			Attributes attributes) throws SAXException {
      //����DefaultHanlder���startElement����
		super.startElement(uri, localName, qName, attributes);
		//��ʼ����bookԪ�ص�����
		if(qName.equals("book")){
//			//��֪bookԪ���µ��������ƣ������������ƻ�ȡ����ֵ
//			String value=attributes.getValue("id");
//			System.out.println("book������ֵ�ǣ�"+value);
			//��֪��bookԪ�������Ե������Լ���������λ�ȡ�������Լ�����ֵ
			int num=attributes.getLength();
			for(int i=0;i<num;i++){
				System.out.println("bookԪ�صĵ�"+(i+1)+"���������ǣ�"+attributes.getQName(i)+"---����ֵ�ǣ�"+attributes.getValue(i));
			}
		}
	}

	/*
	 * ��������xml�ļ��Ľ�����ǩ
	 */
	@Override
	public void endElement(String uri, String localName, String qName)
			throws SAXException {
		// TODO Auto-generated method stub
		super.endElement(uri, localName, qName);
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

}
