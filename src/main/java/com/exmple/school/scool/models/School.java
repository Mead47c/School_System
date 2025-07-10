package com.exmple.school.scool.models;

import java.util.ArrayList;

public class School {

    private String name;
    private String address;
    private String phoneNumber;

    private ArrayList<Student> students;
    private ArrayList<Teacher> teachers;
    private ArrayList<Grade> grades;

    // Default constructor
    public School() {
        initObjects();
    }

    // Constructor with school name
    public School(String name) {
        this.name = name;
        initObjects();
    }

    // Initialize collections
    private void initObjects() {
        this.students = new ArrayList<>();
        this.teachers = new ArrayList<>();
        this.grades = new ArrayList<>();
    }

    // Getters and setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
