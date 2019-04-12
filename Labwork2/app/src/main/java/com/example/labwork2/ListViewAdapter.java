package com.example.labwork2;

import java.util.ArrayList;
import java.util.HashMap;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class ListViewAdapter extends BaseAdapter {
    Context context;
    LayoutInflater inflater;
    ArrayList<HashMap<String, String>> data;
    HashMap<String, String> resultp = new HashMap<>();
    public ListViewAdapter(Context context,
                           ArrayList<HashMap<String, String>> arraylist) {
        this.context = context;
        data = arraylist;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        TextView name;
        inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View itemView = inflater.inflate(R.layout.listview_item, parent, false);
        resultp = data.get(position);
        name = (TextView) itemView.findViewById(R.id.name);
        final ImageView graphic = (ImageView) itemView.findViewById(R.id.graphic);
        if(data.get(position).get("graphic") != null)
            Glide.with(context).
                load("https://raw.githubusercontent.com/wesleywerner/ancient-tech/02decf875616dd9692b31658d92e64a20d99f816/src/images/tech/" + data.get(position).
                        get("graphic")).
                into(graphic);
        name.setText(resultp.get("name"));
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                Intent intent = new Intent(context, SingleItemView.class);
                intent.putExtra("array", data);
                intent.putExtra("position", position);
                context.startActivity(intent);
            }
        });
        return itemView;
    }
}
