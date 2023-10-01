package com.example.crescentstudentutilityapp20;


//Main parent class we created
public class Schedule {

    private String time;
    private String event;

    public Schedule(){
    }

    public Schedule(String timeStamp, String eventName){
        this.time = timeStamp;
        this.event = eventName;
    }

    public Schedule(String eventName){
        this.event = eventName;
    }

    public String getEvent(){
        return this.event;
    }
    public String getTime(){
        return this.time;
    }

    public void setEvent(String event) {
        this.event = event;
    }

    public void setTime(String time) {
        this.time = time;
    }


}
