package com.example.yh.layoutday2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    TextView tv_texts ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView tv_text1 = findViewById(R.id.tv_text1);
        tv_text1.setOnClickListener(this);

        TextView tv_text2 = findViewById(R.id.tv_text2);
        tv_text2.setOnClickListener(this);

        TextView tv_text3 = findViewById(R.id.tv_text3);
        tv_text3.setOnClickListener(this);

        TextView tv_text4 = findViewById(R.id.tv_text4);
        tv_text4.setOnClickListener(this);

        tv_texts = findViewById(R.id.tv_texts);

    }


    @Override
    public void onClick(View view) {
        int id = view.getId();
        switch (id){
            case R.id.tv_text1:
                Intent intent = new Intent(MainActivity.this,SecondActivity.class);
                startActivity(intent);
                System.out.println("点击第一个");
                break;
            case R.id.tv_text2:
                tv_texts.setVisibility(View.VISIBLE);//显示
                System.out.println("点击第二个");
                break;
            case R.id.tv_text3:
                tv_texts.setVisibility(View.INVISIBLE);//隐藏 不自动调整高度
                System.out.println("点击第三个");
                break;
            case R.id.tv_text4:
                tv_texts.setVisibility(View.GONE);//显示  自动调整高度
                System.out.println("点击第四个");
                break;
            default:
                break;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        System.out.println("页面重新激活");
    }



}
