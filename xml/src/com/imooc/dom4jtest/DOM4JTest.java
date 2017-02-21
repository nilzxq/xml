package com.imooc.dom4jtest;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

import com.imooc.entity.Book;

/**
 * @author nilzxq
 * @date��2017��2��19�� ����6:19:02
 * @version 1.0
 */
public class DOM4JTest {
	private static ArrayList<Book> booksList=new ArrayList<Book>();
	
	private void parseXML(){
		// ����books.xml�ļ�
		// 1������һ�� SAXReader ����
		SAXReader reader = new SAXReader();
		// 2���� xml �ļ����ص� SAXReader �У�����ȡ document ����
		Document document;
		try {
			document = reader.read(new File("books.xml"));
			// 3��ͨ�� getRootElement() ��ȡ���ڵ�Ԫ��
			Element bookstore = document.getRootElement();
			// 4��ͨ�� elementIterator() ��ȡ�ӽڵ�Ԫ�أ����� Iterator ���ص�����
			Iterator it = bookstore.elementIterator();
			// 5��ͨ�� while ����������
			while (it.hasNext()) {
				Book bookEntity=new Book();
				System.out.println("========��ʼ����ĳ����========");
				Element book = (Element) it.next();
				// 6����ȡ���Խڵ����� JDOM ����
				List<Attribute> bookAtrrs = book.attributes();
				// 7����ȡԪ�ؽڵ�����ֵ
				for (Attribute attr : bookAtrrs) {
					System.out.println("��������" + attr.getName() + "----����ֵ��"
							+ attr.getValue());
					if(attr.getName().equals("id"))
						bookEntity.setId(attr.getValue());
				}
				// 8����ȡ���Խڵ�����ֵ
				Iterator itt = book.elementIterator();
				while(itt.hasNext()){
					Element bookChild=(Element)itt.next();
					String childName = bookChild.getName();
					String childValue=bookChild.getStringValue();
					System.out.println("�ڵ�����"+childName+"----�ڵ�ֵ��"+childValue);
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
				System.out.println("========��������ĳ����========");
				booksList.add(bookEntity);
				bookEntity=null;
				//���ԣ�Ҳ������ѭ����ȫ��
				System.out.println(booksList.size());
				System.out.println(booksList.get(0).getId());
				System.out.println(booksList.get(0).getName());
			}
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void createXML(){
//		1������Document���󣬴���������XML�ĵ�
		Document document = DocumentHelper.createDocument();
//		2,�������ڵ�rss
		Element rss = document.addElement("rss");
//		3,��rss���ڵ������version����
		rss.addAttribute("version","2.0");
//		4,����XML�ĵ��������
		File file=new File("rssnews.xml");
		 try {
         XMLWriter writer=new XMLWriter(new FileOutputStream(file));
//		5,��������д��XML�ĵ��������������ϵ����
			writer.write(document);
//		6,�ر���Դ
			writer.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
	
		new DOM4JTest().createXML();
	}
}
