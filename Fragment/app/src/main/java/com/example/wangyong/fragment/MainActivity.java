package com.example.wangyong.fragment;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.FrameLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private TextView txt_topbar;
    private TextView txt_channel;
    private TextView txt_message;
    private TextView txt_better;
    private TextView txt_setting;
    private FrameLayout ly_content;

    //Fragment
    private Wyfragment fg1,fg2,fg3,fg4;
    private FragmentManager fManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //requestWindowFeature(Window.FEATURE_NO_TITLE); // 好像是隐藏导航栏的

        fManager = getFragmentManager();
        bindView();
        txt_message.performClick();
    }

    private void bindView() {
        txt_better = findViewById(R.id.txt_better);
        txt_setting = findViewById(R.id.txt_setting);
        txt_channel = findViewById(R.id.txt_channel);
        txt_message = findViewById(R.id.txt_message);
        txt_topbar = findViewById(R.id.txt_top_bar);
        ly_content = findViewById(R.id.ly_content);

        txt_setting.setOnClickListener(this);
        txt_channel.setOnClickListener(this);
        txt_better.setOnClickListener(this);
        txt_message.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        FragmentTransaction fTransaction = fManager.beginTransaction();
        hideAllFragment(fTransaction);
        switch (v.getId()){
            case R.id.txt_better:
                setSelected();

                txt_better.setSelected(true);
                if (fg2 == null){
                    fg2 = new Wyfragment("第二个Fragment");
                    fTransaction.add(R.id.ly_content,fg2);
                }else  {
                    fTransaction.show(fg2);
                }
                break;
            case R.id.txt_channel:
                setSelected();

                txt_channel.setSelected(true);
                if (fg4 == null){
                    fg4 = new Wyfragment("第四个Fragment");
                    fTransaction.add(R.id.ly_content,fg4);
                }else  {
                    fTransaction.show(fg4);
                }
                break;
            case R.id.txt_message:
                setSelected();
                txt_message.setSelected(true);
                if (fg1 == null){
                    fg1 = new Wyfragment("第一个Fragment");
                    fTransaction.add(R.id.ly_content,fg1);
                }else  {
                    fTransaction.show(fg1);
                }
                break;
            case R.id.txt_setting:
                setSelected();
                txt_setting.setSelected(true);
                if (fg3 == null){
                    fg3 = new Wyfragment("第三个Fragment");
                    fTransaction.add(R.id.ly_content,fg3);
                }else  {
                    fTransaction.show(fg3);
                }
                break;
        }
        fTransaction.commitAllowingStateLoss();
    }

    private void setSelected() {
        txt_message.setSelected(false);
        txt_setting.setSelected(false);
        txt_channel.setSelected(false);
        txt_better.setSelected(false);
    }

    private void hideAllFragment(FragmentTransaction fTransaction) {
        if (fg1 !=null) fTransaction.hide(fg1);
        if (fg2 !=null) fTransaction.hide(fg2);
        if (fg3 !=null) fTransaction.hide(fg3);
        if (fg4 !=null) fTransaction.hide(fg4);
    }
}
