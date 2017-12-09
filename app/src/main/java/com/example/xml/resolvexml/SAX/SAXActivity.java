package com.example.xml.resolvexml.SAX;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.example.xml.resolvexml.R;

import java.io.InputStream;
import java.util.ArrayList;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

/**
 * Created by Administrator on 2017/12/6.
 */

public class SAXActivity extends AppCompatActivity implements View.OnClickListener {
    private Button btnsax;
    private ListView list;
    private ArrayList<Person> persons;
    private ArrayAdapter<Person> mAdapter;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       setContentView(R.layout.activity_sax);

      setViews();
    }

    private void setViews() {
        list = findViewById(R.id.list);
        btnsax = findViewById(R.id.btnsax);

        btnsax.setOnClickListener(this);
    }


    private ArrayList<Person> readxmlForSAX() throws Exception {
        //获取文件资源建立输入流对象
        InputStream is = getAssets().open("person.xml");
        //①创建XML解析处理器
        SaxHelper ss = new SaxHelper();
        //②得到SAX解析工厂
        SAXParserFactory factory = SAXParserFactory.newInstance();
        //③创建SAX解析器
        SAXParser parser = factory.newSAXParser();
        //④将xml解析处理器分配给解析器,对文档进行解析,将事件发送给处理器
        parser.parse(is, ss);
        is.close();
        return ss.getPersons();
    }
    @Override
    public void onClick(View v) {
              switch (v.getId()){
                  case R.id.btnsax:
                      try {
                          persons = readxmlForSAX();
                      } catch (Exception e) {
                          e.printStackTrace();
                      }
                      mAdapter =new ArrayAdapter<Person>(SAXActivity.this,
                              android.R.layout.simple_expandable_list_item_1,persons);
                      list.setAdapter(mAdapter);
                      break;
              }
    }

//    @Override
//    public void onClick(View v) {
//        switch (v.getId()){
//            case R.id.btnsax:
//                try {
//                    persons = readxmlForSAX();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//
//                mAdapter = new ArrayAdapter<Person>(SAXActivity.this,
//                        android.R.layout.simple_expandable_list_item_1, persons);
//                list.setAdapter(mAdapter);
//                break;
//        }
//    }
}

