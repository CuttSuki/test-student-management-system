package com.example.teststudentmanagementsystem;

public class Student {
    private String firstName;
    private String lastName;
    private int age;
    private int id;
    private String email;
    Student(String firstName, String lastName, int age, int id, String email){
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.id = id;
        this.email = email;
    }
    public String getFirstName(){
        return firstName;
    }
    public String getLastName(){
        return lastName;
    }
    public String getEmail(){
        return email;
    }
    public int getAge(){
        return age;
    }
    public int getId(){
        return id;
    }
}
