package com.imooc.jdomtest;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import org.jdom2.Attribute;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;

/**
 * @author nilzxq
 * @date��2017��2��19�� ����4:50:42
 * @version 1.0
 */
public class JDOMTest {
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
			// 3.ͨ��SAXBuilder��Build���������������ص�saxb�л�ȡdom����
			Document document = saxBuilder.build(in);
			// 4.ͨ��document�����ȡxml�ļ��ĸ����
			Element rootElement = document.getRootElement();
			// 5.��ȡ������µ��ӽڵ��List����
			List<Element> bookList = rootElement.getChildren();
			// ��������
			for (Element book : bookList) {
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
				}
				// ��book�ڵ���ӽڵ�Ľڵ����Լ��ڵ�ֵ�ı���
				List<Element> bookChilds = book.getChildren();
				for (Element child : bookChilds) {
					System.out.println("�ڵ���:" + child.getName() + "---�ڵ�ֵ��"
							+ child.getValue());
				}
				System.out.println("====����������" + (bookList.indexOf(book) + 1)
						+ "����");
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
