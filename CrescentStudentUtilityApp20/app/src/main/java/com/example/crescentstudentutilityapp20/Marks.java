package com.example.crescentstudentutilityapp20;
import com.google.android.material.navigation.NavigationView;



import java.util.ArrayList;

public class Marks {

    private ArrayList<Academic> markList = new ArrayList<>();

    public ArrayList<Academic> getMarkList() {
        return markList;
    }

    public void setMarkList(ArrayList<Academic> markList) {
        this.markList = markList;
    }

    //Calculates the student's average
    public double average(){
      double avg = 0;
      for(int i = 0; i<markList.size();i++)
          //First adds up the value of all the student's marks
          avg+= markList.get(i).getGrade();
      //Then divides this by the number of course (the basic formula for calculating average)
      avg/= markList.size();
      return avg;

    }

    //Method that calculates whether a mark is above or below the student's average (i.e. bringing it up or down)
    //Implementation is that whatever the "UpOrDown" is determines its visual background in the MarksActivity
    //Refer to MarksActivity for this implementation
    public void aboveOrBelowAverage(){
        for(int i = 0; i<markList.size();i++){
            if(markList.get(i).getGrade()<=average())
                markList.get(i).setUpOrDown("Red");
            else
                markList.get(i).setUpOrDown("Green");
        }
    }


}
