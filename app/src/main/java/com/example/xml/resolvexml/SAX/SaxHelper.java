package com.example.xml.resolvexml.SAX;

import android.util.Log;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;

/**
 * Created by Administrator on 2017/12/6.
 */

/**
 * SAX解析类
 */
class SaxHelper extends DefaultHandler {
    private Person person;
    private ArrayList<Person> persons;
    //当前解析的元素标签
    private String tagName = null;

    /**
     * 当前取到文档开始标志是触发，通常在这里完成一些初始化操作
     *
     * @return
     */
    @Override
    public void startDocument() throws SAXException {
        this.persons = new ArrayList<Person>();
        Log.i("SAX", "读取到文档头，开始解析xml");
    }

    /**
     * 读到一个开始标签时间调用，第二个参数为标签名，最后一个参数为属性数组
     *
     * @return
     */
    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        if ( localName.equals("person") ) {
            person = new Person();
            person.setId(Integer.parseInt(attributes.getValue("id")));
            Log.i("SAX", "开始处理person元素");
        }
        this.tagName = localName;
    }

    /**
     * 读到内容，第一个参数为字符串内容，后面依次为起始位置与长度
     *
     * @return
     */
    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        //判断当前标签是否有效
        if ( this.tagName != null ) {
            String data = new String(ch, start, length);
            //读取标签中的内容
            if ( this.tagName.equals("name") ) {
                this.person.setName(data);
                Log.i("SAX", "处理name元素内容");
            } else if ( this.tagName.equals("age") ) {
                this.person.setAge(Integer.parseInt(data));
                Log.i("SAX", "处理age元素内容");
            }
        }
    }


    /**
     * 处理元素结束时触发，这里将对象添加到结合中
     */
    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        if ( localName.equals("person") ) {
            this.persons.add(person);
            person = null;
            Log.i("SAX", "处理person元素结束");
        }
        this.tagName = null;
    }


    @Override

    /**
     *读取到文档结尾时触发
     */
    public void endDocument() throws SAXException {
        super.endDocument();
        Log.i("SAX", "读取到文档尾,xml解析结束");
    }

//获取person集合
    public ArrayList<Person> getPersons() {
        return persons;
    }
}
