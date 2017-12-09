package com.example.xml.resolvexml.PULL;

import android.util.Xml;

import com.example.xml.resolvexml.SAX.Person;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;
import org.xmlpull.v1.XmlSerializer;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/12/7.
 */

/**
 * 使用PUll解析XML数据的流程
 */
public class pullHelper {
    public static ArrayList<Person> getPersons(InputStream xml) throws Exception {
        ArrayList<Person> persons = null;
        Person person = null;
        //创建一个xml解析的工厂
        XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
        //获得XML解析类的引用
        XmlPullParser parser = factory.newPullParser();
        parser.setInput(xml,"UTF-8");
        //获得事件的类型
        int eventType = parser.getEventType();
                    while (eventType !=XmlPullParser.END_DOCUMENT){
                        switch (eventType){
                            case XmlPullParser.START_DOCUMENT:
                                persons = new ArrayList<Person>();
                    break;
                    case XmlPullParser.START_TAG:
                        if ( "person".equals(parser.getName()) ){
                            person = new Person();
                            //取出属性值
                            int id = Integer.parseInt(parser.getAttributeValue(0));
                            person.setId(id);
                        }else if ( "name".equals(parser.getName()) ){
                            //获取该节点的内容
                            String name = parser.nextText();
                            person.setName(name);
                        }else if ( "age".equals(parser.getName()) ){
                            int age = Integer.parseInt(parser.nextText());
                            person.setAge(age);
                        }
                        break;
                        case XmlPullParser.END_TAG:
                            if ( "person".equals(parser.getName()) ) {
                                persons.add(person);
                                person = null;
                            }
                            break;
            }
            eventType = parser.next();
        }
        return persons;
    }
/**
 *
 * 使用Pull生成xml数据的流程
 */
public static void save(List<Person> persons, OutputStream out) throws Exception {
     //创建XMLSerializer(xml序列化类的实例)
    XmlSerializer serializer = Xml.newSerializer();
    //为XmlSerializer(设置输出流与编码格式)
    serializer.setOutput(out,"UTF-8");
    //为XmlSerializer设置xml的编码格式
    serializer.startDocument("UTF-8",true);
    //设置根元素
    serializer.startTag(null,"persons");
    //使用for循环遍历persons集合中所有的元素，同时一次写入标签与属性
    for (Person p:persons){
        serializer.startTag(null,"person");
        serializer.attribute(null,"id",p.getId()+"");
        serializer.startTag(null,"name");
        serializer.text(p.getName());
        serializer.endTag(null,"name");
        serializer.startTag(null,"age");
        serializer.text(p.getAge()+"");
        serializer.endTag(null,"age");
        serializer.endTag(null,"person");
    }
    //设置跟踪完结元素
    serializer.endTag(null,"persons");
    //结束文档编写
    serializer.endDocument();
    //调用flush（)将内存中的数据写入文件中关闭输出流
    out.flush();
    out.close();
}

}
