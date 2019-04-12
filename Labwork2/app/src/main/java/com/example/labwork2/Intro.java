package com.example.labwork2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class Intro extends AppCompatActivity {

    static final String IsResumed = "is resumed";
    static final String IsOnBackPressed = "is on back pressed";

    private boolean IsResumedActive = false;
    private volatile boolean IsOnBackPressedActive = false;

    public ArrayList<HashMap<String, String>> ListElem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);

        if(savedInstanceState != null) {
            IsResumedActive = savedInstanceState.getBoolean(IsResumed);
            IsOnBackPressedActive = savedInstanceState.getBoolean(IsOnBackPressed);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (!IsResumedActive) {
            Thread thread = new Thread(new Runnable() {
                public void run() {
                    try {
                        DownloadJSON();

                        if (!IsOnBackPressedActive) {
                            Intent intent = new Intent(Intro.this, MainActivity.class);
                            intent.putExtra("Downloaded", ListElem);
                            startActivity(intent);
                            finish();
                        }
                    } catch (
                            Exception e) {
                        e.printStackTrace();
                    }
                    IsResumedActive = true;
                }
            });
            thread.start();
        }
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        savedInstanceState.putBoolean(IsResumed, IsResumedActive);
        savedInstanceState.putBoolean(IsOnBackPressed, IsOnBackPressedActive);
        super.onSaveInstanceState(savedInstanceState);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        IsOnBackPressedActive = true;
    }

    void DownloadJSON() throws JSONException {
        ListElem = new ArrayList<>();
        String jsonstr = JSONfunctions
                .getJSONStringfromURL("https://raw.githubusercontent.com/wesleywerner/ancient-tech/02decf875616dd9692b31658d92e64a20d99f816/src/data/techs.ruleset.json");
        JSONArray jsonarray;
        JSONObject jsonobject;
        jsonarray = new JSONArray(jsonstr);
        for (int i = 0; i < jsonarray.length(); i++) {
            HashMap<String, String> technologies = new HashMap<>();
            jsonobject = jsonarray.getJSONObject(i);
            if (jsonobject.has("name"))
                technologies.put("name", jsonobject.getString("name"));
            if (jsonobject.has("helptext"))
                technologies.put("helptext", jsonobject.getString("helptext"));
            if (jsonobject.has("graphic"))
                technologies.put("graphic", jsonobject.getString("graphic"));
            if ((jsonobject != null) && jsonobject.has("name"))
                ListElem.add(technologies);
        }
    }
}