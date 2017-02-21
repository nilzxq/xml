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
 * @date：2017年2月19日 下午6:19:02
 * @version 1.0
 */
public class DOM4JTest {
	private static ArrayList<Book> booksList=new ArrayList<Book>();
	
	private void parseXML(){
		// 解析books.xml文件
		// 1、创建一个 SAXReader 对象
		SAXReader reader = new SAXReader();
		// 2、将 xml 文件加载到 SAXReader 中，并获取 document 对象
		Document document;
		try {
			document = reader.read(new File("books.xml"));
			// 3、通过 getRootElement() 获取根节点元素
			Element bookstore = document.getRootElement();
			// 4、通过 elementIterator() 获取子节点元素，返回 Iterator 返回迭代器
			Iterator it = bookstore.elementIterator();
			// 5、通过 while 遍历迭代器
			while (it.hasNext()) {
				Book bookEntity=new Book();
				System.out.println("========开始遍历某本书========");
				Element book = (Element) it.next();
				// 6、获取属性节点类似 JDOM 解析
				List<Attribute> bookAtrrs = book.attributes();
				// 7、获取元素节点名、值
				for (Attribute attr : bookAtrrs) {
					System.out.println("属性名：" + attr.getName() + "----属性值："
							+ attr.getValue());
					if(attr.getName().equals("id"))
						bookEntity.setId(attr.getValue());
				}
				// 8、获取属性节点名、值
				Iterator itt = book.elementIterator();
				while(itt.hasNext()){
					Element bookChild=(Element)itt.next();
					String childName = bookChild.getName();
					String childValue=bookChild.getStringValue();
					System.out.println("节点名："+childName+"----节点值："+childValue);
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
				System.out.println("========结束遍历某本书========");
				booksList.add(bookEntity);
				bookEntity=null;
				//测试，也可以用循环看全面
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
//		1，创建Document对象，代表着整个XML文档
		Document document = DocumentHelper.createDocument();
//		2,创建根节点rss
		Element rss = document.addElement("rss");
//		3,向rss根节点中添加version属性
		rss.addAttribute("version","2.0");
//		4,创建XML文档的输出流
		File file=new File("rssnews.xml");
		 try {
         XMLWriter writer=new XMLWriter(new FileOutputStream(file));
//		5,将程序所写的XML文档内容与输出流联系起来
			writer.write(document);
//		6,关闭资源
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
