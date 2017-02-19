package com.imooc.jdomtest;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import javax.swing.text.AsyncBoxView.ChildLocator;

import org.jdom2.Attribute;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;

import com.imooc.entity.Book;

/**
 * @author nilzxq
 * @date��2017��2��19�� ����4:50:42
 * @version 1.0
 */
public class JDOMTest {
	private static ArrayList<Book> booksList=new ArrayList<Book>();
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// ���ж�books.xml�ļ���JDOM����
		// ׼������
		// 1.����һ��SAXBuilder����
		SAXBuilder saxBuilder = new SAXBuilder();
		// 2.��������������xml�ļ����ص��������У��׳�FileNotFoundException��
		try {
			FileInputStream in = new FileInputStream("books.xml");
			InputStreamReader isr=new InputStreamReader(in,"UTF-8");
			// 3.ͨ��SAXBuilder��Build���������������ص�saxb�л�ȡdom����
			Document document = saxBuilder.build(isr);
			// 4.ͨ��document�����ȡxml�ļ��ĸ����
			Element rootElement = document.getRootElement();
			// 5.��ȡ������µ��ӽڵ��List����
			List<Element> bookList = rootElement.getChildren();
			// ��������
			for (Element book : bookList) {
				Book bookEntity=new Book();
				System.out.println("====��ʼ������" + (bookList.indexOf(book) + 1)
						+ "����");
				// ����book�����Լ���
				List<Attribute> attrList = book.getAttributes();
				// //��֪�ڵ��µ�������ֵ����ýڵ���
				// book.getAttribute("id");
				// ����attrList(��Բ����book�ڵ������Ե����ּ�����)
				for (Attribute attr : attrList) {
					// ��ȡ������
					String attrName = attr.getName();
					// ��ȡ����ֵ
					String attrValue = attr.getValue();
					System.out.println("��������" + attrName + "------����ֵ��"
							+ attrValue);
					if(attrName.equals("id"))
						bookEntity.setId(attrValue);
				}
				// ��book�ڵ���ӽڵ�Ľڵ����Լ��ڵ�ֵ�ı���
				List<Element> bookChilds = book.getChildren();
				for (Element child : bookChilds) {
					String childName = child.getName();
					String childValue=child.getValue();
					System.out.println("�ڵ���:" + childName + "---�ڵ�ֵ��"
							+ childValue);
					if(childName.equals("name"))
						bookEntity.setName(childValue);
					else if(childName.equals("author"))
						bookEntity.setAuthor(childValue);
					else if(childName.equals("year"))
						bookEntity.setYear(childValue);
					else if(childName.equals("price"))
						bookEntity.setPrice(childValue);
					else if(childName.equals("language"))
						bookEntity.setLanguage(childValue);
				}
					
				System.out.println("====����������" + (bookList.indexOf(book) + 1)
						+ "����");
				booksList.add(bookEntity);
				bookEntity=null;
				//���ԣ�Ҳ������ѭ����ȫ��
				System.out.println(booksList.size());
				System.out.println(booksList.get(0).getId());
				System.out.println(booksList.get(0).getName());
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (JDOMException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}
