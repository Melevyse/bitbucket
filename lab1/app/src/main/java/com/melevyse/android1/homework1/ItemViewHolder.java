package com.melevyse.android1.homework1;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.melevyse.android1.homework1.R;

public class ItemViewHolder extends RecyclerView.ViewHolder {
    private TextView mItemText;

    public ItemViewHolder(View itemView) {
        super(itemView);
        mItemText = (TextView) itemView.findViewById(R.id.item_text);
    }

    public void setItemText(String text) {
        mItemText.setText(text);
    }
}
