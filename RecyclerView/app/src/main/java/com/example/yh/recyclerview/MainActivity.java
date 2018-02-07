package com.example.yh.recyclerview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyc_list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyc_list = findViewById(R.id.recyc_list);

        List<String> list = initData();
        RecAdapter adapter = new RecAdapter(list,this);
        //recyc_list.setLayoutManager(new LinearLayoutManager(this));  列表形式
        recyc_list.setLayoutManager(new GridLayoutManager(this,5)); // 九宫格形式

        recyc_list.setAdapter(adapter);

    }

    public List<String> initData() {
        List<String> list = new ArrayList<>();

        for (int i = 0 ;i <10;i++){
            list.add(String.valueOf(i+1));
        }

        return list;
    }
}
