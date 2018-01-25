package com.example.yh.adapter;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.LinkedList;
import java.util.List;

public class MainActivity extends AppCompatActivity {


    private Context mContext;
    private AnimalAdapter mAdapter = null;
    private ListView list_animal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        String[] strs ={"1","2","3","4","5"};
//        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,R.layout.support_simple_spinner_dropdown_item,strs);
//
//        ListView list_test = (ListView) findViewById(R.id.list_view);
//        list_test.setAdapter(adapter);

        mContext = MainActivity.this;
        list_animal = (ListView) findViewById(R.id.list_view);
        LinkedList<Animal> mData = new LinkedList<Animal>();

        mData.add(new Animal(
                "狗说","你是狗吗？",R.mipmap.icon03
        ));
        mData.add(new Animal(
                "牛说","你是牛吗？",R.mipmap.icon04
        ));
        mData.add(new Animal(
                "鸭子说","你是鸭子吗？",R.mipmap.icon05
        ));
        mData.add(new Animal(
                "鱼儿说","你是鱼儿吗？",R.mipmap.icon06
        ));
        mData.add(new Animal(
                "马说","你是马吗？",R.mipmap.icon07
        ));
        mAdapter = new AnimalAdapter((LinkedList<Animal>) mData,mContext);

        list_animal.setAdapter(mAdapter);

    }
}
