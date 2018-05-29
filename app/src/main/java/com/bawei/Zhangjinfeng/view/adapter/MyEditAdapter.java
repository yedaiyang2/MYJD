package com.bawei.Zhangjinfeng.view.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.bawei.Zhangjinfeng.R;
import com.bawei.Zhangjinfeng.model.bean.User;

import java.util.List;

/**
 * Created by sky on 2018/05/03.
 */

public class MyEditAdapter extends BaseAdapter {
    private Context context;
    private List<User> list;

    public MyEditAdapter(Context context, List<User> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        viewHolder holder;
        if(convertView==null){
            holder = new viewHolder();
            convertView=View.inflate(context, R.layout.main_edit_layout,null);
            holder.textView = (TextView) convertView.findViewById(R.id.textView);
            convertView.setTag(holder);
        }else{
            holder= (viewHolder) convertView.getTag();
        }
        holder.textView.setText(list.get(position).getTitle());
        return convertView;
    }
    class viewHolder{
        TextView textView;
    }
}
