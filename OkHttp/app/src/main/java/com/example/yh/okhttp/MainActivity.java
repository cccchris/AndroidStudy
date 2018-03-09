package com.example.yh.okhttp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       // getDataAsync();   //异步
        getDataasync();   //同步
    }

    public void getDataasync() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try{
                    OkHttpClient client = new OkHttpClient();
                    Request request = new  Request.Builder()
                            .url("http://www.k12chn.com/m17/m1717I/m1717I002/schoolId/5068")
                            .build();
                    Response response = null;
                    response = client.newCall(request).execute();

                    if (response.isSuccessful()){
                        Log.d("111","code="+response.code());
                        Log.d("222","message"+response.message());
                        Log.d("333","res"+response.body().string());
                    }else  {
                        Log.d("错误","code="+response.code());
                    }
                } catch (Exception e){

                }
            }
        }).start();
    }

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
                    Log.d("kwwl","获取成功");
                    String json =  response.body().string();
                    Log.d("kwwl", json) ;
                    Log.d("kwwl",response.message()) ;
                }
            }

        });
    }
}
