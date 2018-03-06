package com.example.yh.okhttp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d("1111111","");
        getDataasync();
        Log.d("222222","msg");

    }

    public void getDataasync() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try{
                    OkHttpClient client = new OkHttpClient();
                    Request request = new  Request.Builder()
                            .url("http://www.baidu.com")
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
}
