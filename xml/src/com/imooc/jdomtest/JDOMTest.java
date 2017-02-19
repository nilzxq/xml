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
 * @date：2017年2月19日 下午4:50:42
 * @version 1.0
 */
public class JDOMTest {
	private static ArrayList<Book> booksList=new ArrayList<Book>();
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// 进行对books.xml文件的JDOM解析
		// 准备工作
		// 1.创建一个SAXBuilder对象
		SAXBuilder saxBuilder = new SAXBuilder();
		// 2.创建输入流，将xml文件加载到输入流中（抛出FileNotFoundException）
		try {
			FileInputStream in = new FileInputStream("books.xml");
			InputStreamReader isr=new InputStreamReader(in,"UTF-8");
			// 3.通过SAXBuilder的Build方法将输入流加载到saxb中获取dom对象
			Document document = saxBuilder.build(isr);
			// 4.通过document对象获取xml文件的根结点
			Element rootElement = document.getRootElement();
			// 5.获取根结点下的子节点的List集合
			List<Element> bookList = rootElement.getChildren();
			// 继续遍历
			for (Element book : bookList) {
				Book bookEntity=new Book();
				System.out.println("====开始解析第" + (bookList.indexOf(book) + 1)
						+ "本书");
				// 解析book的属性集合
				List<Attribute> attrList = book.getAttributes();
				// //已知节点下的属性名值，获得节点名
				// book.getAttribute("id");
				// 遍历attrList(针对不清楚book节点下属性的名字及数量)
				for (Attribute attr : attrList) {
					// 获取属性名
					String attrName = attr.getName();
					// 获取属性值
					String attrValue = attr.getValue();
					System.out.println("属性名：" + attrName + "------属性值："
							+ attrValue);
					if(attrName.equals("id"))
						bookEntity.setId(attrValue);
				}
				// 对book节点的子节点的节点名以及节点值的遍历
				List<Element> bookChilds = book.getChildren();
				for (Element child : bookChilds) {
					String childName = child.getName();
					String childValue=child.getValue();
					System.out.println("节点名:" + childName + "---节点值："
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
					
				System.out.println("====结束解析第" + (bookList.indexOf(book) + 1)
						+ "本书");
				booksList.add(bookEntity);
				bookEntity=null;
				//测试，也可以用循环看全面
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
