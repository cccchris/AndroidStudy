package com.example.yh.adapter;

import android.content.Context;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

/**
 * Created by yh on 2018/1/31.
 */

public class TwoAdapter extends BaseAdapter {
    private static final int TYPE_Item1 = 0;
    private static final int TYPE_Item2 = 1;
    private Context mContext;
    private ArrayList<Object> mData = null;

    public TwoAdapter(Context mContext, ArrayList<Object> mData) {
        this.mContext = mContext;
        this.mData = mData;
    }


    @Override
    public int getCount() {
        return mData.size();
    }

    @Override
    public Object getItem(int i) {
        return mData.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public int getItemViewType(int position) {

        if (mData.get(position) instanceof Animal) {
            return TYPE_Item1;
        }else if (mData.get(position) instanceof Name){
            return TYPE_Item2;
        }else  {
            return super.getItemViewType(position);
        }

    }

    @Override
    public int getViewTypeCount() {
        return 2;
    }

    @Override
    public View getView(final int i, View view, ViewGroup viewGroup) {
        int type = getItemViewType(i);
        ViewHolder1 holder1 = null;
        ViewHolder2 holder2 = null;
        if (view == null) {
            switch (type){
                case TYPE_Item1:
                    view = LayoutInflater.from(mContext).inflate(R.layout.listview,viewGroup,false);
                    holder1 = new ViewHolder1();
                    holder1.img_icon =  (ImageView) view.findViewById(R.id.img);
                    holder1.name = (TextView) view.findViewById(R.id.name);
                    holder1.content = (TextView) view.findViewById(R.id.content);
                    holder1.btn = (Button) view.findViewById(R.id.bt_btn);
                    view.setTag(R.id.Tag_Animal,holder1);
                    break;
                case TYPE_Item2:
                    view = LayoutInflater.from(mContext).inflate(R.layout.nameitem,viewGroup,false);
                    holder2 = new ViewHolder2();
                    holder2.name = (TextView) view.findViewById(R.id.tv_name);
                    view.setTag(R.id.Tag_Name,holder2);
                    break;
            }
        }else  {
            switch (type) {
                case TYPE_Item1:
                    holder1 = (ViewHolder1) view.getTag(R.id.Tag_Animal);
                    break;
                case TYPE_Item2:
                    holder2 = (ViewHolder2) view.getTag(R.id.Tag_Name);
                    break;
            }
        }

        Object obj = mData.get(i);
        switch (type){
            case TYPE_Item1:
                Animal animal = (Animal) obj;
                if (animal != null){
                    holder1.img_icon.setBackgroundResource(animal.getaIcon());
                    holder1.name.setText(animal.getaName());
                    holder1.content.setText(animal.getaSpeak());
                    holder1.btn.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            deleteItem.onDeleteClick(i);
                        }
                    });
                }

                break;
            case TYPE_Item2:
                Name n = (Name) obj;
                if (n != null){
                    holder2.name.setText(n.getaName());
                }
                break;
        }
        return view;
    }

    @Nullable
    @Override
    public CharSequence[] getAutofillOptions() {
        return new CharSequence[0];
    }

    //添加接口 向Activity 传递消息
    public interface deleteItemListener {
        void onDeleteClick(int i);
    }

    private AnimalAdapter.deleteItemListener deleteItem;

    public AnimalAdapter.deleteItemListener getDeleteItem() {
        return deleteItem;
    }

    public void setDeleteItem(AnimalAdapter.deleteItemListener deleteItem) {
        this.deleteItem = deleteItem;
    }
}


class ViewHolder1 {
    ImageView img_icon;
    TextView name;
    TextView  content;
    Button btn;
}


class ViewHolder2 {
    TextView name;
}