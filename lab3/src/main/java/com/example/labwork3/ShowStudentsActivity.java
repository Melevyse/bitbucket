package com.example.labwork3;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

public class ShowStudentsActivity extends AppCompatActivity {

    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_student);
        DataBaseLoader dataBaseLoader = new DataBaseLoader(getApplicationContext());
        ArrayList<Student> students = dataBaseLoader.readAll();
        recyclerView = findViewById(R.id.showRecyclerView);
        StudentsRecyclerAdapter studentsRecyclerAdapter = new StudentsRecyclerAdapter(students);
        recyclerView.setAdapter(studentsRecyclerAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getBaseContext()));
    }
}
