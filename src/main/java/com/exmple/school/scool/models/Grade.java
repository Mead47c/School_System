package com.exmple.school.scool.models;

import java.util.ArrayList;

public class Grade {

    private String code;
    private String name;
    private Teacher teacher;
    private ArrayList<Student> students;

    // Constructor to initialize Grade with code and name
    public Grade(String code, String name){
        this.code = code;
        this.name = name;
    }

    // Getters and Setters
    public String getCode(){
        return code;
    }

    public void setCode(String code){
        this.code = code;
    }

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    public Teacher getTeacher(){
        return teacher;
    }

    public void setTeacher(Teacher teacher){
        this.teacher = teacher;
    }

    public ArrayList<Student> getStudents(){
        return students;
    }

    public void setStudents(ArrayList<Student> students){
        this.students = students;
    }
}
