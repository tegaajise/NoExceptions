package com.example.crescentstudentutilityapp20;

public class Academic extends Schedule {

    private double mark;
    private String upOrDown;

    public Academic(){

    }

    public Academic (String course, double grade){
        super(course);
        this.mark = grade;
    }

    public Academic(String s, String t){
        super(s,t);
    }


    public double getGrade() {
        return mark;
    }

    public void setGrade(double grade) {
        this.mark = grade;
    }

    public void setUpOrDown(String i){
        this.upOrDown = i;
    }

    public String getUpOrDown(){return this.upOrDown;}

    //To help programmer read the contents of an Academic object, after it reads from a file
    @Override
    public String toString() {
        return "Academic{" +
                "mark=" + mark +
                ", upOrDown='" + upOrDown + ", class=" + this.getEvent()+ '\'' +
                '}';
    }
}
