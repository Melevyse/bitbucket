package com.example.labwork3;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private boolean created = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button showButton = findViewById(R.id.showButton);
        DataBaseLoader dataBaseLoader = new DataBaseLoader(getApplicationContext());
        if (savedInstanceState != null)
            created = savedInstanceState.getBoolean("created");
        if (!created) {
            dataBaseLoader.addFiveStudents();
            created = true;
        }
        showButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startShowActivity();
            }
        });
        Button addButton = findViewById(R.id.addButton);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DataBaseLoader dataBaseLoader = new DataBaseLoader(getApplicationContext());
                dataBaseLoader.addStudent();
            }
        });
        Button changeButton = findViewById(R.id.changeLastButton);
        changeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DataBaseLoader dataBaseLoader = new DataBaseLoader(getApplicationContext());
                dataBaseLoader.changeStudent();
            }
        });
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        created = savedInstanceState.getBoolean("created");
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putBoolean("created", created);
    }

    public void startShowActivity(){
        Intent intent = new Intent(MainActivity.this, ShowStudentsActivity.class);
        startActivity(intent);
    }
}
