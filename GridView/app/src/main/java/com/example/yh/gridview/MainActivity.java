package com.example.yh.gridview;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.GridView;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class MainActivity extends AppCompatActivity {


    private Context mContext;
    private GridView gridView;

    private MyAdapter<Icon> myAdapter = null;
    private List<Icon> mData = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mContext = MainActivity.this;
        gridView = (GridView) findViewById(R.id.gv_gridView);
        mData = new ArrayList<Icon>();

        mData.add(new Icon("图标1",R.mipmap.ic_launcher));
        mData.add(new Icon("图标2",R.mipmap.ic_launcher));
        mData.add(new Icon("图标3",R.mipmap.ic_launcher));
        mData.add(new Icon("图标4",R.mipmap.ic_launcher));
        mData.add(new Icon("图标5",R.mipmap.ic_launcher));


        myAdapter = new MyAdapter<Icon>((ArrayList<Icon>) mData,R.layout.itemlayout) {
            @Override
            public void bindView(ViewHolder holder, Icon obj) {
                holder.setTv_context(R.id.tv_name,obj.getName());
                holder.setImg_icon(R.id.img_icon,obj.getIcon());
            }
        };

        gridView.setAdapter(myAdapter);

    }
}
