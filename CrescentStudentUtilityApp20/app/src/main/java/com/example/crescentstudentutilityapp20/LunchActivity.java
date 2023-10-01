package com.example.crescentstudentutilityapp20;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.res.Resources;
import android.os.Bundle;
import android.widget.TextView;

import java.util.ArrayList;

public class LunchActivity extends AppCompatActivity{

    /*RecyclerViews help with list items, and do not have a fixed size
    (size changes with size of the storage of the data you're reading from)
     */
    private RecyclerView rv;
    private Adapter adapter;

    private ArrayList<String> daysList;

    private TextView studentName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lunch_menu);

        //Student's name based on info from LoginActivity class
        studentName = (TextView)findViewById(R.id.tvStudentName);
        studentName.setText(LoginActivity.name);

        daysList = new ArrayList<>();
        daysList.add("Monday");
        daysList.add("Tuesday");
        daysList.add("Wednesday");
        daysList.add("Thursday");
        daysList.add("Friday");

        setUp();

    }

    public void setUp(){
        adapter = new Adapter(daysList);

        rv = (RecyclerView)findViewById(R.id.list_of_contents);
        rv.setAdapter(adapter);
        rv.setLayoutManager(new LinearLayoutManager(this));
    }
}
