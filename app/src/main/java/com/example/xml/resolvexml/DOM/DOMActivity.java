package com.example.xml.resolvexml.DOM;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.example.xml.resolvexml.R;
import com.example.xml.resolvexml.SAX.Person;

import java.util.ArrayList;

/**
 * Created by Administrator on 2017/12/6.
 */

public class DOMActivity extends AppCompatActivity {
    private Button btndom;
    private ListView list_two;
    private ArrayList<Person> persons;
    private ArrayAdapter<Person> mAdapter;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dom);
        bindviews();
    }

    private void bindviews() {
        btndom = findViewById(R.id.btndom);
        list_two = findViewById(R.id.list_two);

        btndom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DomHelper ds = new DomHelper();
                persons = ds.queryXML(getApplicationContext());
                mAdapter = new ArrayAdapter<Person>(DOMActivity.this,android.R.layout.simple_expandable_list_item_1,persons);
                list_two.setAdapter(mAdapter);

            }
        });
    }

}
