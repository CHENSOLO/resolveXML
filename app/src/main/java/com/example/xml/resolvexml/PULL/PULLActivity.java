package com.example.xml.resolvexml.PULL;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.xml.resolvexml.R;
import com.example.xml.resolvexml.SAX.Person;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/12/6.
 */

public class PULLActivity extends AppCompatActivity implements View.OnClickListener {
    private Button btnreadpull,btnwritepull;
    private ListView list_three;
    private ArrayList<Person> persons;
    private ArrayAdapter<Person> mAdapter;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pull);
        bindviews();
    }

    private void bindviews() {
        btnreadpull = findViewById(R.id.btnreadpull);
        btnwritepull = findViewById(R.id.btnwritepull);
        list_three = findViewById(R.id.list_three);
        btnwritepull.setOnClickListener(this);
        btnreadpull.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnreadpull:
                //获取文件资源建立输入流对象
                try {
                    InputStream is = getAssets().open("person3.xml");
                    persons = pullHelper.getPersons(is);
                    if ( persons.equals(null) ){
                        Toast.makeText(getApplicationContext(),"呵呵",Toast.LENGTH_SHORT).show();
                    }
                    for (Person p1:persons){
                        Log.i("ccsolo",p1.toString());
                    }
                    mAdapter = new ArrayAdapter<Person>(PULLActivity.this,android.R.layout.simple_expandable_list_item_1,persons);
                    list_three.setAdapter(mAdapter);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            case R.id.btnwritepull:
                Context context = getApplicationContext();
                List<Person> persons = new ArrayList<Person>();
                persons.add(new Person(20,"CC",90));
                persons.add(new Person(20,"CC",80));
                persons.add(new Person(20,"CC",70));
                File xmlFile = new File(context.getFilesDir(),"cc.xml");
                FileOutputStream fos;
                try {
                    fos = new FileOutputStream(xmlFile);
                    pullHelper.save(persons,fos);
                    Toast.makeText(context,"文件写入完毕",Toast.LENGTH_SHORT).show();

                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
        }
    }
}
