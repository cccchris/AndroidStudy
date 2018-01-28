package com.example.yh.adapter;

import android.content.Context;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.LinkedList;

/**
 * Created by wangyong on 2018/1/26.
 */

public class AnimalAdapter extends BaseAdapter {

    private LinkedList<Animal> mData;
    private Context mComtext;

    public AnimalAdapter(LinkedList<Animal> mData, Context mComtext) {
        this.mData = mData;
        this.mComtext = mComtext;
    }

    @Override
    public int getCount() {
        return mData.size();
    }

    @Override
    public Object getItem(int i) {
        return i;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder = null;

        if (view == null) {
            view = LayoutInflater.from(mComtext).inflate(R.layout.listview,viewGroup,false);
            viewHolder = new ViewHolder();
            viewHolder.img_icon =  (ImageView) view.findViewById(R.id.img);
            viewHolder.name = (TextView) view.findViewById(R.id.name);
            viewHolder.content = (TextView) view.findViewById(R.id.content);
            view.setTag(viewHolder);
        }else  {
            viewHolder = (ViewHolder) view.getTag();
        }

        viewHolder.img_icon.setBackgroundResource(mData.get(i).getaIcon());
        viewHolder.name.setText(mData.get(i).getaName());
        viewHolder.content.setText(mData.get(i).getaSpeak());
        return view;
    }

    @Nullable
    @Override
    public CharSequence[] getAutofillOptions() {
        return new CharSequence[0];
    }

}

class ViewHolder {
    ImageView img_icon;
    TextView  name;
    TextView  content;

}