package com.example.xml.resolvexml;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.xml.resolvexml.PULL.PULLActivity;
import com.example.xml.resolvexml.SAX.SAXActivity;

import com.example.xml.resolvexml.DOM.DOMActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
private Button open_SAX,open_DOM,open_PULL;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bindviews();
    }

    private void bindviews() {
        open_SAX = findViewById(R.id.open_SAX);
        open_DOM = findViewById(R.id.open_DOM);
        open_PULL = findViewById(R.id.open_PULL);

        open_DOM.setOnClickListener(this);
        open_SAX.setOnClickListener(this);
        open_PULL.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.open_SAX:
                Intent i = new Intent();
                i.setClass(MainActivity.this, SAXActivity.class);
                startActivity(i);
                break;
            case R.id.open_DOM:
                Intent o = new Intent();
                o.setClass(MainActivity.this, DOMActivity.class);
                startActivity(o);
                break;
            case R.id.open_PULL:
                Intent p = new Intent();
                p.setClass(MainActivity.this, PULLActivity.class);
                startActivity(p);
                break;
        }
    }
}
