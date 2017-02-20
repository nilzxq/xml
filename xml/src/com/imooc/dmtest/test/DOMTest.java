package com.imooc.dmtest.test;

import java.io.IOException;
import java.text.ParseException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 * @author nilzxq
 * @date：2017年2月17日 下午6:38:54
 * @version 1.0
 */
public class DOMTest {

	public DocumentBuilder getDocumentBuilder()
	{
		// 1.创建一个DocumentBuilderFactory的对象
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		// 2.创建一个DocumentBuilder的对象
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
	 * 解析xml文件
	 */
	public void xmlParser() {
		try {
			
			// 3.调用parse方法加载books.xml文件到当前项目下，返回值为Document对象
			Document document = getDocumentBuilder().parse("books.xml");
			;
			// 获取所有book节点的集合
			NodeList bookList = document.getElementsByTagName("book");
			// 通过NodeList的getlength（）方法获取bookList长度
			System.out.println("一共有" + bookList.getLength() + "本书");
			// 遍历每一个book节点
			for (int i = 0; i < bookList.getLength(); i++) {
				System.out.println("==============下面开始遍历第" + (i + 1)
						+ "本书的内容==============");
				// 获取一个book节点 item（index） nodelist索引值从0开始 i条件设置为0
				Node book = bookList.item(i);
				// 获取book属性集合
				NamedNodeMap attrs = book.getAttributes();
				System.out.println("第" + (i + 1) + "本书共有" + attrs.getLength()
						+ "个属性");
				// 遍历book的属性 获取属性个数
				for (int j = 0; j < attrs.getLength(); j++) {
					// 通过item(index)方法获取book节点的某一个属性
					Node attr = attrs.item(j);
					// 获取属性名 属性值
					System.out.print("属性名：" + attr.getNodeName());
					System.out.println("--属性值：" + attr.getNodeValue());
				}
				// 已知book节点有且只有1个id属性
				// 1、通过document.getElementByTagName("book")获得所有book的节点集合
				// 2、遍历集合 NodeList.getLength()获得集合长度
				// 3、element book=（element）NodeList.item(i);//强制转换为element类型
				// 4、String attrValue = book.getAttribute("id");//获得属性值
				// Element book=(Element)bookList.item(i);
				// String attrValue=book.getAttribute("id");
				// System.out.println("id属性的属性值为"+attrValue);
				// 解析book节点的子节点
				NodeList childNodes = book.getChildNodes();
				// 遍历childNodes获取每个节点的节点名和节点值
				System.out.println("第" + (i + 1) + "本书共有"
						+ childNodes.getLength() + "个子节点");
				for (int k = 0; k < childNodes.getLength(); k++) {
					// 区分出text类型的node以及element类型的node
					if (childNodes.item(k).getNodeType() == Node.ELEMENT_NODE) {
						// 获取了element类型节点的节点名
						System.out.print("第" + (k + 1) + "个节点的节点名："
								+ childNodes.item(k).getNodeName());
						// 获取了element类型节点的节点值
						// 节点.getFirstChild().getNodeValue()或节点.getTextContent()
						// System.out.println("--节点值是:"+childNodes.item(k).getFirstChild().getNodeValue());
						System.out.println("--节点值是:"
								+ childNodes.item(k).getTextContent());
					}
				}
				System.out.println("==============结束遍历第" + (i + 1)
						+ "本书的内容==============");
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
	 * 生成xml
	 */
	public void createXML(){
		DocumentBuilder db =getDocumentBuilder();
		Document document=db.newDocument();
		Element bookstore = document.createElement("bookstore");
		//向bookstore根节点中添加子节点book
		Element book=document.createElement("book");
		book.setAttribute("id", "1");
		//将book节点添加到bookstore根节点中
		bookstore.appendChild(book);
		//将bookstore节点（已经包含了book）添加到dom树中
		document.appendChild(bookstore);
	}
	/**
	 * 主方法，程序的入口
	 * @param args
	 */
	public static void main(String[] args) {

	}
}
