package com.example.crescentstudentutilityapp20;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.Buffer;
import java.nio.charset.Charset;
import java.util.ArrayList;

public class MarksActivity extends AppCompatActivity implements Organizable {

    private Marks marks = new Marks();

    private TextView courseOne, courseTwo, courseThree, courseFour, courseFive, courseSix, courseSeven, courseEight;

    private TextView markOne, markTwo, markThree, markFour, markFive, markSix, markSeven, markEight;

    private TextView average, studentName;

    private ArrayList<String> colours = new ArrayList<>();

    private Switch numerical;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.marks);

        studentName = (TextView)findViewById(R.id.tvStudentName);
        studentName.setText(LoginActivity.name);

        reader(R.raw.marks, "Marks");

        double overallAverage = marks.average();
        double overallAverageRounded = Math.round(overallAverage * 10.0)/10.0;

        /*A checker to see if all the elements were properly read by the file*/
//        for(int i = 0;i<marks.getMarkList().size();i++){
//            System.out.println(marks.getMarkList().get(i));
//        }

        createMarkBook();

        average = (TextView)findViewById(R.id.tvAverage);
        average.setText((CharSequence)String.valueOf(overallAverageRounded));

        colourCoder();

        numerical = (Switch)findViewById(R.id.bNumerical);

        numerical.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(numerical.isChecked()) {
                    sort();
                    //Reassigns all the element values after the above sort is performed
                    courseOne = (TextView) findViewById(R.id.tvCourse1);
                    courseOne.setText((CharSequence) marks.getMarkList().get(0).getEvent());
                    markOne = (TextView) findViewById(R.id.tvMark1);
                    markOne.setText((CharSequence) String.valueOf(marks.getMarkList().get(0).getGrade()));

                    courseTwo = (TextView) findViewById(R.id.tvCourse2);
                    courseTwo.setText((CharSequence) marks.getMarkList().get(1).getEvent());
                    markTwo = (TextView) findViewById(R.id.tvMark2);
                    markTwo.setText((CharSequence) String.valueOf(marks.getMarkList().get(1).getGrade()));

                    courseThree = (TextView) findViewById(R.id.tvCourse3);
                    courseThree.setText((CharSequence) marks.getMarkList().get(2).getEvent());
                    markThree = (TextView) findViewById(R.id.tvMark3);
                    markThree.setText((CharSequence) String.valueOf(marks.getMarkList().get(2).getGrade()));

                    courseFour = (TextView) findViewById(R.id.tvCourse4);
                    courseFour.setText((CharSequence) marks.getMarkList().get(3).getEvent());
                    markFour = (TextView) findViewById(R.id.tvMark4);
                    markFour.setText((CharSequence) String.valueOf(marks.getMarkList().get(3).getGrade()));

                    courseFive = (TextView) findViewById(R.id.tvCourse5);
                    courseFive.setText((CharSequence) marks.getMarkList().get(4).getEvent());
                    markFive = (TextView) findViewById(R.id.tvMark5);
                    markFive.setText((CharSequence) String.valueOf(marks.getMarkList().get(4).getGrade()));

                    courseSix = (TextView) findViewById(R.id.tvCourse6);
                    courseSix.setText((CharSequence) marks.getMarkList().get(5).getEvent());
                    markSix = (TextView) findViewById(R.id.tvMark6);
                    markSix.setText((CharSequence) String.valueOf(marks.getMarkList().get(5).getGrade()));

                    courseSeven = (TextView) findViewById(R.id.tvCourse7);
                    courseSeven.setText((CharSequence) marks.getMarkList().get(6).getEvent());
                    markSeven = (TextView) findViewById(R.id.tvMark7);
                    markSeven.setText((CharSequence) String.valueOf(marks.getMarkList().get(6).getGrade()));

                    //Re-colour codes all the elements
                    colourCoder();
                }
                else{
                    //Essentially a reset
                    Intent intent = new Intent(MarksActivity.this, MarksActivity.class);
                    startActivity(intent);
                }

            }
        });

        }


    /*Credit to - http://java-buddy.blogspot.com/2016/06/read-csv-file-display-in-javafx.html*/
    @Override
    public void reader(int id, String type) {
        InputStream stream = getResources().openRawResource(R.raw.marks);
        BufferedReader br;
        String fileDelimiter = ",";
        String[] attributes;

            try {
                br = new BufferedReader(
                        new InputStreamReader(stream, Charset.forName("UTF-8"))
                );
                String lines = br.readLine();

                while((lines = br.readLine()) != null) {
                    attributes = lines.split(fileDelimiter, -1);
                    Academic course = new Academic(attributes[0], Double.parseDouble(attributes[1]));
                    marks.getMarkList().add(course);
                }
            }
            catch(IOException e) {
                e.printStackTrace();
            }
    }

    //Actually sets the visual background colour of each of the mark slots based on whether they're above or below student's average
    public void colourCoder(){
        marks.aboveOrBelowAverage();
        for(int i = 0; i<marks.getMarkList().size();i++){
            if(marks.getMarkList().get(i).getUpOrDown().equals("Red")){
                colours.add(i, "#F44336");
            }
            else{
                colours.add(i,"#4CAF50");
            }
        }
        createColorCoderBook();
    }


    @Override
    public void sort() {
        //My implementation of a selections sort algorithm - sorts grades from lowest mark to highest mark
        int minpos;
        for(int unsortedpos = 0; unsortedpos <marks.getMarkList().size()-1;unsortedpos++) {
            minpos = unsortedpos;
            for(int a = unsortedpos; a<marks.getMarkList().size(); a++) {
                if(marks.getMarkList().get(a).getGrade()<marks.getMarkList().get(minpos).getGrade())
                    minpos = a;
            }
            swap(minpos,unsortedpos,marks.getMarkList());
        }

    }
    //Swap for the selections sort algorithm
    public void swap(int i, int j, ArrayList<Academic> a) {
        //Swaps the grade and course (shown elements)
        double tempNum = a.get(i).getGrade();
        String tempText = a.get(i).getEvent();

        a.get(i).setEvent(a.get(j).getEvent());
        a.get(i).setGrade(a.get(j).getGrade());

        a.get(j).setEvent(tempText);
        a.get(j).setGrade(tempNum);

    }

    //Bottomline, depending on the amount of courses one takes, is the number of TextView boxes that'll be worked with
    //With the standard being 8 courses, a student that takes less than that will just have their spaces filled with spare, to a
    //minimum of 5 courses (3 spares)
    //This method is mainly for the visual
    public void createMarkBook(){
        switch(marks.getMarkList().size()){
            case 5:
                courseOne = (TextView) findViewById(R.id.tvCourse1);
                courseOne.setText((CharSequence) marks.getMarkList().get(0).getEvent());
                markOne = (TextView) findViewById(R.id.tvMark1);
                markOne.setText((CharSequence)String.valueOf(marks.getMarkList().get(0).getGrade()));

                courseTwo = (TextView) findViewById(R.id.tvCourse2);
                courseTwo.setText((CharSequence) marks.getMarkList().get(1).getEvent());
                markTwo = (TextView) findViewById(R.id.tvMark2);
                markTwo.setText((CharSequence)String.valueOf(marks.getMarkList().get(1).getGrade()));

                courseThree = (TextView) findViewById(R.id.tvCourse3);
                courseThree.setText((CharSequence) marks.getMarkList().get(2).getEvent());
                markThree = (TextView) findViewById(R.id.tvMark3);
                markThree.setText((CharSequence)String.valueOf(marks.getMarkList().get(2).getGrade()));

                courseFour = (TextView) findViewById(R.id.tvCourse4);
                courseFour.setText((CharSequence) marks.getMarkList().get(3).getEvent());
                markFour = (TextView) findViewById(R.id.tvMark4);
                markFour.setText((CharSequence)String.valueOf(marks.getMarkList().get(3).getGrade()));

                courseFive = (TextView) findViewById(R.id.tvCourse5);
                courseFive.setText((CharSequence) marks.getMarkList().get(4).getEvent());
                markFive = (TextView) findViewById(R.id.tvMark5);
                markFive.setText((CharSequence)String.valueOf(marks.getMarkList().get(4).getGrade()));

                courseSix = (TextView) findViewById(R.id.tvCourse6);
                courseSix.setText("SPARE");
                markSix = (TextView) findViewById(R.id.tvMark6);
                markSix.setText("SPARE");

                courseSeven = (TextView) findViewById(R.id.tvCourse7);
                courseSeven.setText("SPARE");
                markSeven = (TextView) findViewById(R.id.tvMark7);
                markSeven.setText("SPARE");

                courseEight = (TextView) findViewById(R.id.tvCourse8);
                courseEight.setText("SPARE");
                markEight = (TextView) findViewById(R.id.tvMark8);
                markEight.setText("SPARE");
                break;
            case 6:
                courseOne = (TextView) findViewById(R.id.tvCourse1);
                courseOne.setText((CharSequence) marks.getMarkList().get(0).getEvent());
                markOne = (TextView) findViewById(R.id.tvMark1);
                markOne.setText((CharSequence)String.valueOf(marks.getMarkList().get(0).getGrade()));

                courseTwo = (TextView) findViewById(R.id.tvCourse2);
                courseTwo.setText((CharSequence) marks.getMarkList().get(1).getEvent());
                markTwo = (TextView) findViewById(R.id.tvMark2);
                markTwo.setText((CharSequence)String.valueOf(marks.getMarkList().get(1).getGrade()));

                courseThree = (TextView) findViewById(R.id.tvCourse3);
                courseThree.setText((CharSequence) marks.getMarkList().get(2).getEvent());
                markThree = (TextView) findViewById(R.id.tvMark3);
                markThree.setText((CharSequence)String.valueOf(marks.getMarkList().get(2).getGrade()));

                courseFour = (TextView) findViewById(R.id.tvCourse4);
                courseFour.setText((CharSequence) marks.getMarkList().get(3).getEvent());
                markFour = (TextView) findViewById(R.id.tvMark4);
                markFour.setText((CharSequence)String.valueOf(marks.getMarkList().get(3).getGrade()));

                courseFive = (TextView) findViewById(R.id.tvCourse5);
                courseFive.setText((CharSequence) marks.getMarkList().get(4).getEvent());
                markFive = (TextView) findViewById(R.id.tvMark5);
                markFive.setText((CharSequence)String.valueOf(marks.getMarkList().get(4).getGrade()));

                courseSix = (TextView) findViewById(R.id.tvCourse6);
                courseSix.setText((CharSequence) marks.getMarkList().get(5).getEvent());
                markSix = (TextView) findViewById(R.id.tvMark6);
                markSix.setText((CharSequence)String.valueOf(marks.getMarkList().get(5).getGrade()));

                courseSeven = (TextView) findViewById(R.id.tvCourse7);
                courseSeven.setText("SPARE");
                markSeven = (TextView) findViewById(R.id.tvMark7);
                markSeven.setText("SPARE");

                courseEight = (TextView) findViewById(R.id.tvCourse8);
                courseEight.setText("SPARE");
                markEight = (TextView) findViewById(R.id.tvMark8);
                markEight.setText("SPARE");
                break;
            case 7:
                courseOne = (TextView) findViewById(R.id.tvCourse1);
                courseOne.setText((CharSequence) marks.getMarkList().get(0).getEvent());
                markOne = (TextView) findViewById(R.id.tvMark1);
                markOne.setText((CharSequence)String.valueOf(marks.getMarkList().get(0).getGrade()));

                courseTwo = (TextView) findViewById(R.id.tvCourse2);
                courseTwo.setText((CharSequence) marks.getMarkList().get(1).getEvent());
                markTwo = (TextView) findViewById(R.id.tvMark2);
                markTwo.setText((CharSequence)String.valueOf(marks.getMarkList().get(1).getGrade()));

                courseThree = (TextView) findViewById(R.id.tvCourse3);
                courseThree.setText((CharSequence) marks.getMarkList().get(2).getEvent());
                markThree = (TextView) findViewById(R.id.tvMark3);
                markThree.setText((CharSequence)String.valueOf(marks.getMarkList().get(2).getGrade()));

                courseFour = (TextView) findViewById(R.id.tvCourse4);
                courseFour.setText((CharSequence) marks.getMarkList().get(3).getEvent());
                markFour = (TextView) findViewById(R.id.tvMark4);
                markFour.setText((CharSequence)String.valueOf(marks.getMarkList().get(3).getGrade()));

                courseFive = (TextView) findViewById(R.id.tvCourse5);
                courseFive.setText((CharSequence) marks.getMarkList().get(4).getEvent());
                markFive = (TextView) findViewById(R.id.tvMark5);
                markFive.setText((CharSequence)String.valueOf(marks.getMarkList().get(4).getGrade()));

                courseSix = (TextView) findViewById(R.id.tvCourse6);
                courseSix.setText((CharSequence) marks.getMarkList().get(5).getEvent());
                markSix = (TextView) findViewById(R.id.tvMark6);
                markSix.setText((CharSequence)String.valueOf(marks.getMarkList().get(5).getGrade()));

                courseSeven = (TextView) findViewById(R.id.tvCourse7);
                courseSeven.setText((CharSequence) marks.getMarkList().get(6).getEvent());
                markSeven = (TextView) findViewById(R.id.tvMark7);
                markSeven.setText((CharSequence)String.valueOf(marks.getMarkList().get(6).getGrade()));

                courseEight = (TextView) findViewById(R.id.tvCourse8);
                courseEight.setText("SPARE");
                markEight = (TextView) findViewById(R.id.tvMark8);
                markEight.setText("SPARE");
                break;
            case 8:
                courseOne = (TextView) findViewById(R.id.tvCourse1);
                courseOne.setText((CharSequence) marks.getMarkList().get(0).getEvent());
                markOne = (TextView) findViewById(R.id.tvMark1);
                markOne.setText((CharSequence)String.valueOf(marks.getMarkList().get(0).getGrade()));

                courseTwo = (TextView) findViewById(R.id.tvCourse2);
                courseTwo.setText((CharSequence) marks.getMarkList().get(1).getEvent());
                markTwo = (TextView) findViewById(R.id.tvMark2);
                markTwo.setText((CharSequence)String.valueOf(marks.getMarkList().get(1).getGrade()));

                courseThree = (TextView) findViewById(R.id.tvCourse3);
                courseThree.setText((CharSequence) marks.getMarkList().get(2).getEvent());
                markThree = (TextView) findViewById(R.id.tvMark3);
                markThree.setText((CharSequence)String.valueOf(marks.getMarkList().get(2).getGrade()));

                courseFour = (TextView) findViewById(R.id.tvCourse4);
                courseFour.setText((CharSequence) marks.getMarkList().get(3).getEvent());
                markFour = (TextView) findViewById(R.id.tvMark4);
                markFour.setText((CharSequence)String.valueOf(marks.getMarkList().get(3).getGrade()));

                courseFive = (TextView) findViewById(R.id.tvCourse5);
                courseFive.setText((CharSequence) marks.getMarkList().get(4).getEvent());
                markFive = (TextView) findViewById(R.id.tvMark5);
                markFive.setText((CharSequence)String.valueOf(marks.getMarkList().get(4).getGrade()));

                courseSix = (TextView) findViewById(R.id.tvCourse6);
                courseSix.setText((CharSequence) marks.getMarkList().get(5).getEvent());
                markSix = (TextView) findViewById(R.id.tvMark6);
                markSix.setText((CharSequence)String.valueOf(marks.getMarkList().get(5).getGrade()));

                courseSeven = (TextView) findViewById(R.id.tvCourse7);
                courseSeven.setText((CharSequence) marks.getMarkList().get(6).getEvent());
                markSeven = (TextView) findViewById(R.id.tvMark7);
                markSeven.setText((CharSequence)String.valueOf(marks.getMarkList().get(6).getGrade()));

                courseEight = (TextView) findViewById(R.id.tvCourse8);
                courseEight.setText((CharSequence) marks.getMarkList().get(7).getEvent());
                markEight = (TextView) findViewById(R.id.tvMark8);
                markEight.setText((CharSequence)String.valueOf(marks.getMarkList().get(7).getGrade()));
                break;
        }
    }

    //Similar to the above method, this just deals with the number of TextView boxes that'll be colour coded
    //With the standard being 8 courses, a student that takes less than that will just have their spaces filled with spare, to a
    //minimum of 5 courses (3 spares)
    //This method is mainly for the visual
    public void createColorCoderBook(){
        switch(marks.getMarkList().size()){
            case 5:
                courseOne.setBackgroundColor(Color.parseColor(colours.get(0)));
                markOne.setBackgroundColor(Color.parseColor(colours.get(0)));

                courseTwo.setBackgroundColor(Color.parseColor(colours.get(1)));
                markTwo.setBackgroundColor(Color.parseColor(colours.get(1)));

                courseThree.setBackgroundColor(Color.parseColor(colours.get(2)));
                markThree.setBackgroundColor(Color.parseColor(colours.get(2)));

                courseFour.setBackgroundColor(Color.parseColor(colours.get(3)));
                markFour.setBackgroundColor(Color.parseColor(colours.get(3)));

                courseFive.setBackgroundColor(Color.parseColor(colours.get(4)));
                markFive.setBackgroundColor(Color.parseColor(colours.get(4)));
                break;
            case 6:
                courseOne.setBackgroundColor(Color.parseColor(colours.get(0)));
                markOne.setBackgroundColor(Color.parseColor(colours.get(0)));

                courseTwo.setBackgroundColor(Color.parseColor(colours.get(1)));
                markTwo.setBackgroundColor(Color.parseColor(colours.get(1)));

                courseThree.setBackgroundColor(Color.parseColor(colours.get(2)));
                markThree.setBackgroundColor(Color.parseColor(colours.get(2)));

                courseFour.setBackgroundColor(Color.parseColor(colours.get(3)));
                markFour.setBackgroundColor(Color.parseColor(colours.get(3)));

                courseFive.setBackgroundColor(Color.parseColor(colours.get(4)));
                markFive.setBackgroundColor(Color.parseColor(colours.get(4)));

                courseSix.setBackgroundColor(Color.parseColor(colours.get(5)));
                markSix.setBackgroundColor(Color.parseColor(colours.get(5)));
                break;
            case 7:
                courseOne.setBackgroundColor(Color.parseColor(colours.get(0)));
                markOne.setBackgroundColor(Color.parseColor(colours.get(0)));

                courseTwo.setBackgroundColor(Color.parseColor(colours.get(1)));
                markTwo.setBackgroundColor(Color.parseColor(colours.get(1)));

                courseThree.setBackgroundColor(Color.parseColor(colours.get(2)));
                markThree.setBackgroundColor(Color.parseColor(colours.get(2)));

                courseFour.setBackgroundColor(Color.parseColor(colours.get(3)));
                markFour.setBackgroundColor(Color.parseColor(colours.get(3)));

                courseFive.setBackgroundColor(Color.parseColor(colours.get(4)));
                markFive.setBackgroundColor(Color.parseColor(colours.get(4)));

                courseSix.setBackgroundColor(Color.parseColor(colours.get(5)));
                markSix.setBackgroundColor(Color.parseColor(colours.get(5)));

                courseSeven.setBackgroundColor(Color.parseColor(colours.get(6)));
                markSeven.setBackgroundColor(Color.parseColor(colours.get(6)));
                break;
            case 8:
                courseOne.setBackgroundColor(Color.parseColor(colours.get(0)));
                markOne.setBackgroundColor(Color.parseColor(colours.get(0)));

                courseTwo.setBackgroundColor(Color.parseColor(colours.get(1)));
                markTwo.setBackgroundColor(Color.parseColor(colours.get(1)));

                courseThree.setBackgroundColor(Color.parseColor(colours.get(2)));
                markThree.setBackgroundColor(Color.parseColor(colours.get(2)));

                courseFour.setBackgroundColor(Color.parseColor(colours.get(3)));
                markFour.setBackgroundColor(Color.parseColor(colours.get(3)));

                courseFive.setBackgroundColor(Color.parseColor(colours.get(4)));
                markFive.setBackgroundColor(Color.parseColor(colours.get(4)));

                courseSix.setBackgroundColor(Color.parseColor(colours.get(5)));
                markSix.setBackgroundColor(Color.parseColor(colours.get(5)));

                courseSeven.setBackgroundColor(Color.parseColor(colours.get(6)));
                markSeven.setBackgroundColor(Color.parseColor(colours.get(6)));

                courseEight.setBackgroundColor(Color.parseColor(colours.get(7)));
                markEight.setBackgroundColor(Color.parseColor(colours.get(7)));
        }

    }


}
