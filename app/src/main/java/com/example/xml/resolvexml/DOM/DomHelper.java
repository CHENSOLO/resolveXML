package com.example.xml.resolvexml.DOM;

import android.content.Context;

import com.example.xml.resolvexml.SAX.Person;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

/**
 * Created by Administrator on 2017/12/7.
 */

public class DomHelper {
public static  ArrayList<Person> queryXML(Context context){
  ArrayList<Person> Persons = new ArrayList<Person>();
    try {
        //获得DOM解析器的工厂示例
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        //从Dom工厂中获得dom解析器
        DocumentBuilder dbBuilder =dbFactory.newDocumentBuilder();
        //把要解析的xml文件读入dom解析器
        Document doc = dbBuilder.parse(context.getAssets().open("person2.xml"));
        System.out.println("处理该文档的DomImplemention对象="+doc.getImplementation());
        //得到文档中名称问person的元素结点列表
        NodeList nList = doc.getElementsByTagName("person");
        //遍历该集合，显示集合中的元素及子元素的名字
        for (int i = 0; i <nList.getLength() ; i++) {
            //先从Person元素开始解析
            Element personElement = (Element) nList.item(i);
            Person p = new Person();
            p.setId(Integer.valueOf(personElement.getAttribute("id")));
            //获取person下的name和age的Note集合
            NodeList childNoList = personElement.getChildNodes();
            for (int j = 0; j < childNoList.getLength(); j++) {
                Node childNode = childNoList.item(j);
                //判断子note类型是否为元素Note
                if ( childNode.getNodeType()== Node.ENTITY_NODE )
                {
                    Element childElement = (Element) childNode;
                    if ( "name".equals(childElement.getNodeName()) )p.setName(childElement.getFirstChild().getNodeValue());
                    else if ( "age".equals(childElement.getNodeName()))p.setAge(Integer.valueOf(childElement.getFirstChild().getNodeValue()));
                }
            }
            Persons.add(p);
        }
    } catch (Exception e) {
        e.printStackTrace();
    }

    return Persons;
}

}
