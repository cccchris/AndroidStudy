package com.example.yh.gridview;

import android.content.Context;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.LinkedList;

/**
 * Created by yh on 2018/1/31.
 */

public  abstract class MyAdapter<T> extends BaseAdapter {

    public abstract void bindView(ViewHolder holder, T obj);

    private ArrayList<T> mData;
    private int layoutRes;


    public MyAdapter(ArrayList<T> mData, int layoutRes) {
        this.mData = mData;
        this.layoutRes = layoutRes;
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
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder = ViewHolder.bind(viewGroup.getContext(),view,viewGroup,layoutRes,i);
        bindView(holder,(T) getItem(i));

        return holder.getItemView();
    }
}

class ViewHolder {
    private SparseArray<View> mViews;
    private View item;
    private int position;
    private Context context;

    private ViewHolder(Context context,ViewGroup parent,int layoutRes) {
        mViews = new SparseArray<>();
        this.context = context;
        View converView = LayoutInflater.from(context).inflate(layoutRes,parent,false);
        converView.setTag(this);
        item = converView;
    }

    public static ViewHolder bind(Context context,View convertView,ViewGroup parent,int layoutRes,int position){
        ViewHolder holder;
        if (convertView ==null){
            holder = new ViewHolder(context,parent,layoutRes);
        }else  {
            holder = (ViewHolder) convertView.getTag();
            holder.item = convertView;
        }
        holder.position = position;
        return holder;
    }


    public <T extends View> T getView(int id) {
        T t = (T) mViews.get(id);
        if (t==null) {
            t = (T) item.findViewById(id);
            mViews.put(id,t);
        }
        return t;
    }

    ImageView img_icon;
    TextView tv_context;

    public ImageView getImg_icon() {
        return img_icon;
    }

    public ViewHolder setImg_icon(int id , int drawableRes) {
        View view = getView(id);
        if (view instanceof ImageView) {
            ((ImageView) view).setImageResource(drawableRes);
        }else  {
            view.setBackgroundResource(drawableRes);
        }
        return this;
    }

    public TextView getTv_context() {
        return tv_context;
    }

    public ViewHolder setTv_context(int id, String text) {
        View view = getView(id);
        if (view instanceof TextView) {
            ((TextView) view).setText(text);
        }
        return this;
    }

    /**
     * 获取当前条目
     */
    public View getItemView() {
        return item;
    }

    /**
     * 获取条目位置
     */
    public int getItemPosition() {
        return position;
    }

    /**
     * 设置点击监听
     */
    public ViewHolder setOnClickListener(int id, View.OnClickListener listener) {
        getView(id).setOnClickListener(listener);
        return this;
    }

    /**
     * 设置可见
     */
    public ViewHolder setVisibility(int id, int visible) {
        getView(id).setVisibility(visible);
        return this;
    }

    /**
     * 设置标签
     */
    public ViewHolder setTag(int id, Object obj) {
        getView(id).setTag(obj);
        return this;
    }

}