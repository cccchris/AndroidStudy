package com.example.yh.wyproject.activity.login;

//
//import cn.tthud.taitian.DemoApplication;
//import cn.tthud.taitian.MainActivity;
import com.example.yh.wyproject.R;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.yh.wyproject.R;
import com.example.yh.wyproject.base.ActivityBase;
import com.example.yh.wyproject.net.FlowAPI;
import com.example.yh.wyproject.net.rxbus.RxBus;
import com.example.yh.wyproject.net.rxbus.RxBusBaseMessage;
import com.example.yh.wyproject.net.rxbus.RxCodeConstants;
import com.example.yh.wyproject.utils.Log;
import com.example.yh.wyproject.utils.RegExpValidator;
import com.example.yh.wyproject.utils.SPUtils;
import com.example.yh.wyproject.xutils.CommonCallbackImp;
import com.example.yh.wyproject.xutils.MXUtils;

import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;


public class LoginActivity extends ActivityBase {

    @ViewInject(R.id.login_phone)
    private EditText login_phone;

    @ViewInject(R.id.login_pwd)
    private EditText login_pwd;

    @ViewInject(R.id.register_btn)
    private TextView register_btn;

    @ViewInject(R.id.forget_pwd)
    private  TextView forget_pwd;

    @ViewInject(R.id.username_xx)
    private ImageView username_xx;

    @ViewInject(R.id.pwd_xx)
    private ImageView pwd_xx;

    @ViewInject(R.id.login_btn)
    private TextView login_btn;

    @ViewInject(R.id.wechat_login_btn)
    private TextView wechat_login_btn;

    private String phone;
    private String pwd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        appendMainBody(this, R.layout.login_activity_main);
        // 监听两个输入框
        initListenter();

    }

    private void initListenter() {
        login_phone.addTextChangedListener(tw);
    }

    TextWatcher tw = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            String content = s.toString(); // 获取当前输入框内容
            if (getCurrentFocus() == null) { // 当前View 是否处于 焦点
                return;
            }

            int current = getCurrentFocus().getId();// 获取当前View焦点的 Id
            if (content.equals("")) {
                if (current == R.id.login_phone){
                    username_xx.setVisibility(View.INVISIBLE); // 如果当前输入框无内容，就隐藏清空按钮
                }else  {
                    pwd_xx.setVisibility(View.INVISIBLE);
                }
            }else  {
                if (current == R.id.login_phone) {
                    username_xx.setVisibility(View.VISIBLE);
                }else  {
                    pwd_xx.setVisibility(View.VISIBLE);
                }
            }


        }

    };

    public static void navToLogin(Context context){
        Intent intent = new Intent(context,LoginActivity.class);
        context.startActivity(intent);
    }

    private void login() {

    }
     //  绑定事件
    @Event(value = {R.id.top_left,R.id.register_btn,R.id.forget_pwd,R.id.login_btn,R.id.wechat_login_btn},type = View.OnClickListener.class)
    private void onEvenOnclick(View view ) {

        int id = view.getId();
        Intent intent;
        switch (id){
            case R.id.top_left:
                finish();
                break;
            case R.id.register_btn:
                Toast.makeText(this, "注册", (int) 1.5);
                break;
            case R.id.forget_pwd:
                Toast.makeText(this, "忘记密码", (int) 1.5);
                break;
            case R.id.login_btn:
                Toast.makeText(this, "登录", (int) 1.5);
                break;
            case R.id.wechat_login_btn:
                Toast.makeText(this, "微信登录", (int) 1.5);
                break;
        }
    }




}
