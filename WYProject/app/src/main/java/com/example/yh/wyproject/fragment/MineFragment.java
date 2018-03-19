package com.example.yh.wyproject.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.yh.wyproject.R;
import com.example.yh.wyproject.base.FragmentBase;

/**
 * Created by yh on 2018/3/19.
 */

public class MineFragment extends FragmentBase {
    private View view;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        if (view == null) {
            view = super.onCreateView(inflater,container,savedInstanceState);
            appendMainBody(this, R.layout.mine_fragment);
            // ((ImageButton) view.findViewById(R.id.top_left)).setVisibility(View.INVISIBLE);
            setTopBarTitle("我的");
            loadData();
        }
        return view;
    }

    private void loadData() {

    }
}
