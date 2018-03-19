package com.example.yh.wyproject;

import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.example.yh.wyproject.activity.login.LoginActivity;
import com.example.yh.wyproject.fragment.DiscoverFragment;
import com.example.yh.wyproject.fragment.HomeFragment;
import com.example.yh.wyproject.fragment.MessageFragment;
import com.example.yh.wyproject.fragment.MineFragment;
import com.example.yh.wyproject.utils.CommonUtils;
import com.example.yh.wyproject.utils.SPUtils;

public class MainActivity extends AppCompatActivity {


    private HomeFragment homeFragment;
    private DiscoverFragment disFragment;
    private MessageFragment messageFragment;
    private MineFragment mineFragment;
    private Fragment[] fragments;

    private LinearLayout main_mask;
    private Button[] mTabs;
    private int index;
    private int currentTabIndex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        DemoApplication.getInstance().addActivity(this);

        initView();
    }

    private void initView() {
        main_mask = findViewById(R.id.main_mask); // 启动图
        mTabs = new Button[4];
        mTabs[0] = (Button) findViewById(R.id.btn_home);
        mTabs[1] = (Button) findViewById(R.id.btn_dis);
        mTabs[2] = (Button) findViewById(R.id.btn_msg);
        mTabs[3] = (Button) findViewById(R.id.btn_mine);

        homeFragment = new HomeFragment();
        disFragment = new DiscoverFragment();
        messageFragment = new MessageFragment();
        mineFragment = new MineFragment();

        fragments = new Fragment[]{homeFragment,disFragment,messageFragment,mineFragment};
        getSupportFragmentManager().beginTransaction()
                .add(R.id.fragment_container,homeFragment)
                .add(R.id.fragment_container,disFragment)
                .add(R.id.fragment_container,messageFragment)
                .add(R.id.fragment_container,mineFragment)
                .hide(homeFragment)
                .hide(disFragment)
                .hide(messageFragment)
                .hide(mineFragment)
                .commit();
        DemoApplication.getInstance().setMainActivity(this);
        // 选择第一个 tab
        int put_extra_index = getIntent().getIntExtra("extra_index",0);
        android.support.v4.app.FragmentTransaction trx = getSupportFragmentManager().beginTransaction();
        trx.show(fragments[put_extra_index]).commit();
        mTabs[put_extra_index].setSelected(true);
        currentTabIndex = put_extra_index;

        new Handler().postDelayed(new Runnable(){

            public void run() {

                main_mask.setVisibility(View.GONE);

            }

        }, 2000);

    }

    public void onTabClicked(View view) {
        switch (view.getId()){
            case R.id.btn_home:
                index = 0;
                break;
            case R.id.btn_dis:
                index = 1;
                break;
            case R.id.btn_msg:
                if (CommonUtils.checkLogin()){
                    if (!SPUtils.getBoolean(SPUtils.ISVST,true)){ // 游客
                        index = 2;
                    }else  {//不支持先绑定手机号
                        // 绑定手机
                    }

                }else  {
                    LoginActivity.navToLogin(this);
                    return;
                }
                break;

            case R.id.btn_mine:
                if (CommonUtils.checkLogin()){
                    index = 3;
                }else {
                    LoginActivity.navToLogin(this);
                    return;
                }
                break;
        }

        if (currentTabIndex != index) {
            android.support.v4.app.FragmentTransaction trx = getSupportFragmentManager().beginTransaction();
            trx.hide(fragments[currentTabIndex]);
            if (!fragments[index].isAdded()) {
                trx.add(R.id.fragment_container,fragments[index]);
            }
            trx.show(fragments[index]).commit();
        }

        mTabs[currentTabIndex].setSelected(false);
        mTabs[index].setSelected(true);
        currentTabIndex = index;
    }
}
