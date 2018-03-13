package com.example.yh.sp;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Button bt_save;
    private Button bt_clean;
    private Button bt_show;
    private EditText ed_txt;
    private Context mContext;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mContext = getApplicationContext();
        initView();

    }

    public void initView() {
        bt_save = findViewById(R.id.bt_save);
        bt_clean = findViewById(R.id.bt_clean);
        bt_show = findViewById(R.id.bt_show);
        ed_txt = findViewById(R.id.ed_txt);

        bt_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sp = mContext.getSharedPreferences("mysp",Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sp.edit();
                editor.putString("name", String.valueOf(ed_txt.getText()));
                editor.commit();
                Toast.makeText(mContext,"保存",Toast.LENGTH_SHORT).show();
            }
        });

        bt_clean.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sp = mContext.getSharedPreferences("mysp",Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sp.edit();
                editor.clear();
                editor.commit();
            }
        });

        bt_show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sp = mContext.getSharedPreferences("mysp",Context.MODE_PRIVATE);
                Toast.makeText(mContext,sp.getString("name",""),Toast.LENGTH_SHORT).show();

            }
        });

    }

}
