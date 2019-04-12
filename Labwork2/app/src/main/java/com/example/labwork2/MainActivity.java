package com.example.labwork2;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;

public class MainActivity extends Activity {
    ListView list;
    ListViewAdapter adapter;
    Serializable putElem;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listview_main);
        putElem = getIntent().getSerializableExtra("Downloaded");
        list = (ListView) findViewById(R.id.listview);
        adapter = new ListViewAdapter(MainActivity.this, (ArrayList<HashMap<String, String>>) putElem);
        list.setAdapter(adapter);
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        savedInstanceState.putInt("position", list.getFirstVisiblePosition());
        savedInstanceState.putSerializable("array", putElem);
        super.onSaveInstanceState(savedInstanceState);
    }

    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        putElem = savedInstanceState.getSerializable("array");
        list.setSelection(savedInstanceState.getInt("position"));
    }
}