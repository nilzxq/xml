package com.imooc.dmtest.test;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 * @author nilzxq
 * @date��2017��2��17�� ����6:38:54
 * @version 1.0
 */
public class DOMTest {

	public DocumentBuilder getDocumentBuilder()
	{
		// 1.����һ��DocumentBuilderFactory�Ķ���
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		// 2.����һ��DocumentBuilder�Ķ���
		DocumentBuilder db=null;
		try {
			db = dbf.newDocumentBuilder();
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return db;
	}
	/**
	 * ����xml�ļ�
	 */
	public void xmlParser() {
		try {
			
			// 3.����parse��������books.xml�ļ�����ǰ��Ŀ�£�����ֵΪDocument����
			Document document = getDocumentBuilder().parse("books.xml");
			;
			// ��ȡ����book�ڵ�ļ���
			NodeList bookList = document.getElementsByTagName("book");
			// ͨ��NodeList��getlength����������ȡbookList����
			System.out.println("һ����" + bookList.getLength() + "����");
			// ����ÿһ��book�ڵ�
			for (int i = 0; i < bookList.getLength(); i++) {
				System.out.println("==============���濪ʼ������" + (i + 1)
						+ "���������==============");
				// ��ȡһ��book�ڵ� item��index�� nodelist����ֵ��0��ʼ i��������Ϊ0
				Node book = bookList.item(i);
				// ��ȡbook���Լ���
				NamedNodeMap attrs = book.getAttributes();
				System.out.println("��" + (i + 1) + "���鹲��" + attrs.getLength()
						+ "������");
				// ����book������ ��ȡ���Ը���
				for (int j = 0; j < attrs.getLength(); j++) {
					// ͨ��item(index)������ȡbook�ڵ��ĳһ������
					Node attr = attrs.item(j);
					// ��ȡ������ ����ֵ
					System.out.print("��������" + attr.getNodeName());
					System.out.println("--����ֵ��" + attr.getNodeValue());
				}
				// ��֪book�ڵ�����ֻ��1��id����
				// 1��ͨ��document.getElementByTagName("book")�������book�Ľڵ㼯��
				// 2���������� NodeList.getLength()��ü��ϳ���
				// 3��element book=��element��NodeList.item(i);//ǿ��ת��Ϊelement����
				// 4��String attrValue = book.getAttribute("id");//�������ֵ
				// Element book=(Element)bookList.item(i);
				// String attrValue=book.getAttribute("id");
				// System.out.println("id���Ե�����ֵΪ"+attrValue);
				// ����book�ڵ���ӽڵ�
				NodeList childNodes = book.getChildNodes();
				// ����childNodes��ȡÿ���ڵ�Ľڵ����ͽڵ�ֵ
				System.out.println("��" + (i + 1) + "���鹲��"
						+ childNodes.getLength() + "���ӽڵ�");
				for (int k = 0; k < childNodes.getLength(); k++) {
					// ���ֳ�text���͵�node�Լ�element���͵�node
					if (childNodes.item(k).getNodeType() == Node.ELEMENT_NODE) {
						// ��ȡ��element���ͽڵ�Ľڵ���
						System.out.print("��" + (k + 1) + "���ڵ�Ľڵ�����"
								+ childNodes.item(k).getNodeName());
						// ��ȡ��element���ͽڵ�Ľڵ�ֵ
						// �ڵ�.getFirstChild().getNodeValue()��ڵ�.getTextContent()
						// System.out.println("--�ڵ�ֵ��:"+childNodes.item(k).getFirstChild().getNodeValue());
						System.out.println("--�ڵ�ֵ��:"
								+ childNodes.item(k).getTextContent());
					}
				}
				System.out.println("==============����������" + (i + 1)
						+ "���������==============");
			}
		}catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * ����xml
	 */
	public void createXML(){
		DocumentBuilder db =getDocumentBuilder();
		Document document=db.newDocument();
		Element bookstore = document.createElement("bookstore");
		//��bookstore���ڵ�������ӽڵ�book
		Element book=document.createElement("book");
		book.setAttribute("id", "1");
		//��book�ڵ���ӵ�bookstore���ڵ���
		bookstore.appendChild(book);
		//��bookstore�ڵ㣨�Ѿ�������book����ӵ�dom����
		document.appendChild(bookstore);
		
//		��DOM��ת����XML�ļ�
//		1������TransformerFactory����FactoryΪ�����࣬����ֱ��new����ͨ��newInstance��ȡ����
		TransformerFactory tff =TransformerFactory.newInstance();
        try {
//		2,ͨ��tff��ȡTransfomer����
			Transformer  tf=tff.newTransformer();
//		3��ָ�����и�ʽ
			tf.setOutputProperty(OutputKeys.INDENT, "yes");
//		4,��DOM������XML�ļ�,��DOM���Լ��������Ϊ��������
			tf.transform(new DOMSource(document),new StreamResult(new File("books1.xml")));
		} catch (TransformerConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch (TransformerException e) {
        	// TODO Auto-generated catch block
        	e.printStackTrace();
        }
	}
	/**
	 * ����������������
	 * @param args
	 */
	public static void main(String[] args) {
     //����DOMTest����
		DOMTest test=new DOMTest();
		//���ý�������������xml�ļ�
		//test.xmlParser();
		test.createXML();
	}
}
