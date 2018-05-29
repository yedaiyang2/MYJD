package com.bawei.Zhangjinfeng.view.hodler;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bawei.Zhangjinfeng.R;


public class TuiJianHolder extends RecyclerView.ViewHolder {

    public ImageView tui_jian_image;
    public TextView tui_jian_item_title;
    public TextView tui_jian_item_price;

    public TuiJianHolder(View itemView) {
        super(itemView);

        tui_jian_image = itemView.findViewById(R.id.tui_jian_item_image);
        tui_jian_item_title = itemView.findViewById(R.id.tui_jian_item_title);
        tui_jian_item_price = itemView.findViewById(R.id.tui_jian_item_price);

    }
}
