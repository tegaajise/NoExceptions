package com.example.crescentstudentutilityapp20;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.crescentstudentutilityapp20.MainActivity;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.ArrayList;


public class LoginActivity extends AppCompatActivity implements Organizable{

    //declaration of all elements in the page
    private EditText usernameEditText;
    private EditText passwordEditText;
    private Button loginButton ;
    private ProgressBar loadingProgressBar;

    private ArrayList<Student> studentList = new ArrayList<>();

    public static String name;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //visual elements get initialized by code
        usernameEditText = findViewById(R.id.username);
        passwordEditText = findViewById(R.id.password);
        loginButton = findViewById(R.id.login);
        loadingProgressBar = findViewById(R.id.loading);

        reader(R.raw.login_info, "Student");

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(validate()) {
                    loadingProgressBar.setVisibility(View.VISIBLE);
                    //Intent is a function from the manifest that tries to carry out a switch in activities in the app
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    //Switches pages (like a new scene in javafx)
                    startActivity(intent);

                }
            }
        });


    }

    //Method that validates login credentials, and gives user appropriate feedback as to whether credentials were correct or not
    private boolean validate(){

        boolean result=false;

        String name, pass;

        //Variables initialized by user's input
        name = usernameEditText.getText().toString();
        pass = passwordEditText.getText().toString();

        int spaceIndex;

        //Fragment of code does two things:
        //1) Creates username of student by removing space in full name, concatenating first and lastname, & making everything lower case
        //2) Grabs contents of the input by the user and checks if they both match both the password and the username
        for(int i = 0;i<studentList.size();i++){
            //1) - this gets where the space is in the full name
            spaceIndex = studentList.get(i).getFullName().indexOf(" ");
            //1) - this is where the firstname and lastname are appended
            studentList.get(i).setUsername(studentList.get(i).getFullName().substring(0,spaceIndex).toLowerCase() +
                    studentList.get(i).getFullName().substring(spaceIndex+1).toLowerCase());
            //2) - this is where actual validation occurs
            if(name.equals(studentList.get(i).getUsername()) && pass.equals(studentList.get(i).getPassword())){
                result = true;
                //Sets static variable of student's name to the full name pulled from the file
                //This will be called in all the other pages
                this.name = studentList.get(i).getFullName();
                break;
            }
        }
        if(result == false){
            //A toast is like a pop-up message that shows up on the UI
            //Code throws a toast if the user enters the wrong credentials
            Toast.makeText(LoginActivity.this,"Please enter the right Login",Toast.LENGTH_SHORT).show();

        }
        return result;
    }

    @Override
    /*Credit to - http://java-buddy.blogspot.com/2016/06/read-csv-file-display-in-javafx.html*/
    public void reader(int id, String type) {
        //Variable that pulls in the csv file (being a "raw" file)
        InputStream stream = getResources().openRawResource(id);
        BufferedReader br;
        String fileDelimiter = ",";
        String[] attributes;

        try {
            br = new BufferedReader(
                    new InputStreamReader(stream, Charset.forName("UTF-8"))
            );
            String lines = br.readLine();

            //loops through all the lines of the csv file
            //array indices are separated by commas (fileDelimiter) and tells the computer that this is a new column/array element
            while((lines = br.readLine()) != null) {
                attributes = lines.split(fileDelimiter, -1);
                Student stu = new Student(attributes[0], attributes[1]);
                studentList.add(stu);
            }
        }
        catch(IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void sort() {

    }
}
