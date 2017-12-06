package com.example.xml.resolvexml.SAX;

/**
 * Created by Administrator on 2017/12/6.
 */

import android.util.Log;

import java.util.List;

import static android.content.ContentValues.TAG;

/**
 *测试类
 */
public class SAXParseXmlTes {
    public void testSAXgetUsers() throws Exception{
        List<Person> persons = SaxHelper.getPersons();
        for(Person person : persons){
            Log.e(TAG,"id: "+person.getId());
            Log.e(TAG, "name："+person.getName());
            Log.e(TAG, "age："+person.getAge());
        }
    }
}
