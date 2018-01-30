package com.example.yh.adapter;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.LinkedList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {


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
        final LinkedList<Animal> mData = new LinkedList<Animal>();

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
        list_animal.setOnItemClickListener(this);

        //实现 接口
        mAdapter.setDeleteItem(new AnimalAdapter.deleteItemListener() {
            @Override
            public void onDeleteClick(int i) {
                mData.remove(i);
                mAdapter.notifyDataSetChanged();//重新刷新页面
            }
        });

    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        System.out.println("点击了第"+ i + "个");
        Log.e(String.valueOf(i),"====");

        String text = (String) ((TextView)view.findViewById(R.id.name)).getText();
        //大多数情况下，position和id相同，并且都从0开始
        String showText = "点击第" + i + "项，文本内容为：" + text + "，ID为：" + l;
        Toast.makeText(this, showText, Toast.LENGTH_LONG).show();

    }
}
