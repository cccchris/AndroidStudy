package com.example.wangyong.layoutday03;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {


    private EditText et_user;
    private EditText et_pwd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        et_user = findViewById(R.id.et_user);
        et_pwd = findViewById(R.id.et_pwd);
        TextView tv_login = findViewById(R.id.tv_login);

        tv_login.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        switch (id){
            case R.id.tv_login:

                if (et_user.length() == 0){
                    Log.e("tag","用户名为空!");
                    return;
                }
                String pwd = et_pwd.getText().toString();
                if (TextUtils.isEmpty(pwd)) {
                    Log.e("tag", "密码为空!");
                    return;
                }
                break;
        }
    }
}
