package com.example.crescentstudentutilityapp20;
import com.google.android.material.navigation.NavigationView;

public class Extracurricular extends Schedule {

    //Type would be like co-curricular, athletics, outreach, etc..
    private String type;

    public Extracurricular(String theType, String theTime, String theActivity){
        super(theTime, theActivity);
        this.type = theType;
    }

    public Extracurricular(){}
}
