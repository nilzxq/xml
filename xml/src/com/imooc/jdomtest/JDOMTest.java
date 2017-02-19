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
 * @date：2017年2月19日 下午4:50:42
 * @version 1.0
 */
public class JDOMTest {
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
			// 3.通过SAXBuilder的Build方法将输入流加载到saxb中获取dom对象
			Document document = saxBuilder.build(in);
			// 4.通过document对象获取xml文件的根结点
			Element rootElement = document.getRootElement();
			// 5.获取根结点下的子节点的List集合
			List<Element> bookList = rootElement.getChildren();
			// 继续遍历
			for (Element book : bookList) {
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
				}
				// 对book节点的子节点的节点名以及节点值的遍历
				List<Element> bookChilds = book.getChildren();
				for (Element child : bookChilds) {
					System.out.println("节点名:" + child.getName() + "---节点值："
							+ child.getValue());
				}
				System.out.println("====结束解析第" + (bookList.indexOf(book) + 1)
						+ "本书");
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
