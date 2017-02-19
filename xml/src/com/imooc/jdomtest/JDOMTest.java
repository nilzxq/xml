package com.imooc.jdomtest;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;

/**
 * @author nilzxq
 * @date：2017年2月19日  下午4:50:42
 * @version 1.0
 */
public class JDOMTest {
      /**
     * @param args
     */
    public static void main(String[] args) {
	 //进行对books.xml文件的JDOM解析
    //准备工作
//    	  1.创建一个SAXBuilder对象
    	  SAXBuilder saxBuilder=new SAXBuilder();
//    	  2.创建输入流，将xml文件加载到输入流中（抛出FileNotFoundException）
    	  try {
			FileInputStream in = new FileInputStream("books.xml");
//    	  3.通过SAXBuilder的Build方法将输入流加载到saxb中获取dom对象
		      Document document = saxBuilder.build(in);
//    	  4.通过document对象获取xml文件的根结点
		      Element rootElement = document.getRootElement();
//    	  5.获取根结点下的子节点的List集合
		      List<Element> bookList = rootElement.getChildren();
			} catch (FileNotFoundException e) {
			e.printStackTrace();
		}catch (JDOMException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
    	  
}
}
