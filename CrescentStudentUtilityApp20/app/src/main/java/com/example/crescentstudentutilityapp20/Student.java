package com.example.crescentstudentutilityapp20;

public class Student {

    private String fullName;
    private String password;
    private String username;

    public Student(String fullName, String password) {
        this.fullName = fullName;
        this.password = password;
    }

    public Student(String attribute) {
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
