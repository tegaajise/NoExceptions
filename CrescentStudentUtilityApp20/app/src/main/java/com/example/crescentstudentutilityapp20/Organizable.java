package com.example.crescentstudentutilityapp20;
//Interface deals with how the data is read, and sorted --> ways of organization
public interface Organizable {

    //Different ways of being sorted includes, alphabetically, chronologically or alphabetically
    void sort();
    //The implementation differs across the different pages
    void reader(int id, String readerType);
}
