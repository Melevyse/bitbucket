package com.example.labwork3;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

public class StudentViewHolder extends RecyclerView.ViewHolder {

    public TextView FullName;

    public StudentViewHolder(@NonNull View itemView) {
        super(itemView);
        FullName = itemView.findViewById(R.id.FullName);
    }
}
