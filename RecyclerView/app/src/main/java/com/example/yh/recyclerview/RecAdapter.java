package com.example.yh.recyclerview;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by yh on 2018/2/3.
 */

public class RecAdapter extends RecyclerView.Adapter {

    private List<String> datas;
    private Context context;

    public RecAdapter(List<String> datas, MainActivity context) {
        this.datas = datas;
        this.context = context;
    }


    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
       View view = LayoutInflater.from(context).inflate(R.layout.item_rcy,parent,false);
       MyViewHolder myViewHolder = new MyViewHolder(view);

        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        MyViewHolder myHolder = (MyViewHolder) holder;

        ((MyViewHolder) holder).tv_name.setText(datas.get(position));

    }

    @Override
    public int getItemCount() {
        return datas.size();
    }


    class MyViewHolder extends RecyclerView.ViewHolder {

        private TextView tv_name;

        public MyViewHolder(View itemView) {
            super(itemView);
            tv_name = itemView.findViewById(R.id.tv_name);

        }
    }
}
