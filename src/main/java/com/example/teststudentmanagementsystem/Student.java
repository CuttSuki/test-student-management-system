package com.example.teststudentmanagementsystem;

public class Student {
    private String firstName;
    private String lastName;
    private int age;
    private int id;
    private String email;
    private String createdAt;
    private String updatedAt;
    Student(String firstName, String lastName, int age, int id, String email, String createdAt, String updatedAt){
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.id = id;
        this.email = email;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
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
    public String getCreatedAt() { return createdAt; }
    public String getUpdatedAt() { return updatedAt; }
    public int getAge(){
        return age;
    }
    public int getId(){
        return id;
    }
}
