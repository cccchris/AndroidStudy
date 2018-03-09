package com.example.yh.recyclerview;

import android.os.Handler;
import android.os.Looper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyc_list;
    private String jsonData;
    private List<String> list;
    private RecAdapter adapter;
    private Handler handler = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyc_list = findViewById(R.id.recyc_list);

        //  list = initData();
        list = new ArrayList<>();
        recyc_list.setLayoutManager(new LinearLayoutManager(this));   // 列表形式
        adapter = new RecAdapter(list,this);
        //recyc_list.setLayoutManager(new GridLayoutManager(this,5)); // 九宫格形式
        recyc_list.setAdapter(adapter);

        getDataAsync();
    }

    public List<String> initData() {
        List<String> list = new ArrayList<>();

        for (int i = 0 ;i <10;i++){
            list.add(String.valueOf(i+1));
        }

        return list;
    }

   // 获取数据
    public void getDataAsync() {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url("http://www.k12chn.com/m17/m1717I/m1717I002/schoolId/5068")
                .build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()) {
                    Log.d("kwwl", "获取成功");
                    jsonData = response.body().string();
                    Log.d("kwwl", response.message());

                    list = parseJSONWithJSONObject(jsonData);

                    if (Looper.getMainLooper().getThread() == Thread.currentThread()) {
                        Log.d("123", "Main Thread");
                    } else {
                        Log.d("123", "Not Main Thread");
                    }

                    MainActivity.this.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Log.d("list",list.toString());
                            adapter.setDatas(list); // 重新设置数据
                            recyc_list.getAdapter().notifyDataSetChanged();   // 刷新页面
                        }
                    });

                }
            }

        });
    }

    //方法一：使用JSONObject
    private List<String> parseJSONWithJSONObject(String JsonData) {
        List<String> list = new ArrayList<>();
        try
             {

                 JSONObject result = new JSONObject(JsonData);//转换为JSONObject

                 JSONArray jsonArray = result.getJSONArray("list");
                 for (int i=0; i < jsonArray.length(); i++)    {
                     JSONObject jsonObject = jsonArray.getJSONObject(i);
                     String name = jsonObject.getString("Title");
                     list.add(name);
                 }
                 return list;
             }
             catch (Exception e)
            {
                     e.printStackTrace();
                return list;
            }

         }
}
