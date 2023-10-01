package com.example.crescentstudentutilityapp20;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.Switch;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class ScheduleActivity extends AppCompatActivity implements Organizable{

    private ArrayList<Schedule> academicList = new ArrayList<>();
    private ArrayList<Schedule> extracurricularList = new ArrayList<>();
    //ArrayList of ArrayLists - holds the ArrayLists of the above schedule types
    private ArrayList<ArrayList<Schedule>> currentList = new ArrayList<>();

    private ImageButton topLeftArrow, topRightArrow, bottomLeftArrow, bottomRightArrow;

    //Tracker variable for switching between the types of schedules in the calendar
    private final int[] index = {1};

    private TextView date, scheduleType, studentName;

    private final Calendar calendar = Calendar.getInstance();
    private final String[] currentDate = {DateFormat.getDateInstance(DateFormat.MEDIUM).format(calendar.getTime())};
    //Variable that tracks what day of the month is being displayed
    private final int[] dayNumber = {calendar.DAY_OF_MONTH};

    private Switch alphabetical;

    private RecyclerView rv;
    private MyAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.schedule);

        studentName = (TextView)findViewById(R.id.tvStudentName);
        studentName.setText(LoginActivity.name);

        currentList.add(academicList);
        currentList.add(extracurricularList);

        //The value received from the calendar class is the actual day of the month plus one for some reason
        dayNumber[0]--;

        reader(R.raw.day1,"Academic");

        date = findViewById(R.id.tvScheduleDate);
        //https://www.youtube.com/watch?v=Le47R9H3qow - Variable that tracks live date
        date.setText(currentDate[0]);

        listSetUp();

        scheduleType = findViewById(R.id.tvCalendarType);
        scheduleType.setText("Academic");

        topLeftArrow = (ImageButton)findViewById(R.id.bLeftArrowTop);
        topRightArrow = (ImageButton)findViewById(R.id.bRightArrowTop);
        bottomLeftArrow = (ImageButton)findViewById(R.id.bLeftArrowBottom);
        bottomRightArrow = (ImageButton)findViewById(R.id.bRightArrowBottom);

        alphabetical = (Switch)findViewById(R.id.alphabetical);

        System.out.println(dayNumber[0]);


        topLeftArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //https://www.youtube.com/watch?v=2DYavHADmB8 - Gets a future date of a calendar object
                calendar.add(Calendar.DATE,-1);
                currentDate[0] = DateFormat.getDateInstance(DateFormat.MEDIUM).format(calendar.getTime());
                //Displays this future date
                date.setText(currentDate[0]);

                //Shows the day 1 calendar on even-numbered days of the month
                if (--dayNumber[0] % 2 == 0) {
                    reader(R.raw.day1, "Academic");
                }
                //Shows the day 2 calendar on odd-numbered days of the month
                else {
                    reader(R.raw.day2, "Academic");
                }
                //Whether it has read the day 1 or day 2 file based on above condition,
                //this sets up the UI and its values
                listSetUp();
                if(alphabetical.isChecked()){
                    //Takes into consideration whether the "Alphabetical" switch is on or off
                    sort();
                }

            }
        });
        /*SAME DEAL AS ABOVE, BUT VICE VERSA*/
        topRightArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calendar.add(Calendar.DATE,1);
                currentDate[0] = DateFormat.getDateInstance(DateFormat.MEDIUM).format(calendar.getTime());
                date.setText(currentDate[0]);
                if (++dayNumber[0] % 2 == 0) {
                    reader(R.raw.day1, "Academic");
                } else {
                    reader(R.raw.day2, "Academic");
                }
                listSetUp();
                if(alphabetical.isChecked()){
                    sort();
                }
            }
        });

        //Bottom left arrow works only if the index is not the number at the far left
        bottomLeftArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (index[0] != 1) {
                    index[0]--;
                    switchScheduleTypes();
                    if(alphabetical.isChecked()){
                        sort();
                        listSetUp();
                    }
                }
            }
        });
        //Bottom right arrow works only if the index is not the number at the far right
        bottomRightArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (index[0] != 2) {
                    index[0]++;
                    switchScheduleTypes();
                    if(alphabetical.isChecked()){
                        sort();
                        listSetUp();
                    }
                }
            }
        });

        //Listener to see whether the switch is on or off, & acts accordingly
        alphabetical.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(alphabetical.isChecked()){
                    sort();
                    listSetUp();
                }
                else{
                    //Reset
                    startActivity(new Intent(ScheduleActivity.this,ScheduleActivity.class));
                }
            }
        });

    }

    //Method that switches which type of schedule to display (between extracurricular & academic)
    private void switchScheduleTypes(){
        switch (index[0]){
            case 1:
                if(dayNumber[0]%2 == 0)
                    reader(R.raw.day1, "Academic");
                else
                    reader(R.raw.day2, "Academic");
                listSetUp();
                scheduleType.setText("Academic");
                break;
            case 2:
                reader(R.raw.extra_schedule, "Extracurricular");
                listSetUp();
                scheduleType.setText("Extracurricular");
                break;
        }

    }

    @Override
    //Different implementation of the reader method
    public void reader(int id, String type) {
        InputStream stream = getResources().openRawResource(id);
        BufferedReader br;
        String fileDelimiter = ",";
        String[] attributes;
        if(currentList.get(index[0]-1).size()!=0)
            currentList.get(index[0]-1).clear();

        try {
            br = new BufferedReader(
                    new InputStreamReader(stream, Charset.forName("UTF-8"))
            );
            String lines = br.readLine();

            while((lines = br.readLine()) != null) {
                attributes = lines.split(fileDelimiter, -1);
                //How the file is read depends on what type of calendar it is & from what file is called
                if(type.equals("Academic")){
                    //Tracker variable is set to 1 as it is the 'first' of the two pages of types of schedules
                    index[0] = 1;
                    Schedule sched = new Academic(attributes[0], (attributes[1]));
                    //Index number is always index[0] element - 1 --> a little confusing I know
                    currentList.get(index[0]-1).add(sched);
                }
                else{
                    //Tracker variable is set to 2 as it is the 'second' of the two pages of types of schedules
                    index[0] = 2;
                    Schedule sched = new Extracurricular(attributes[0], attributes[1], attributes[2]);
                    currentList.get(index[0]-1).add(sched);
                }
            }
        }
        catch(IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void sort() {
        //Selections sort algorithm - sorts calendar events in alphabetical order
        //(based on lexicographical order as done by the compareTo method)
        int minpos;
        for(int unsortedpos = 0; unsortedpos <currentList.get(index[0]-1).size()-1;unsortedpos++) {
            minpos = unsortedpos;
            for(int a = unsortedpos; a<currentList.get(index[0]-1).size(); a++) {
                if(currentList.get(index[0]-1).get(a).getEvent().compareTo(currentList.get(index[0]-1).get(minpos).getEvent()) < 0)
                    minpos = a;
            }
            swap(minpos,unsortedpos,currentList.get(index[0]-1));
//			System.out.println(Arrays.toString(arr));
        }

    }

    //Similar to swap method in the MarksActivity class
    public void swap(int i, int j, ArrayList<Schedule> a) {
        String tempText = a.get(i).getEvent();
        String tText = a.get(i).getTime();

        a.get(i).setEvent(a.get(j).getEvent());
        a.get(i).setTime(a.get(j).getTime());

        a.get(j).setEvent(tempText);
        a.get(j).setTime(tText);
    }
    public void listSetUp(){
        adapter = new MyAdapter(currentList.get(index[0]-1));

        rv = (RecyclerView)findViewById(R.id.contents_list);
        rv.setAdapter(adapter);
        rv.setLayoutManager(new LinearLayoutManager(this));
    }

}
