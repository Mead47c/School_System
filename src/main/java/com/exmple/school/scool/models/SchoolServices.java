package com.exmple.school.scool.models;

import java.util.ArrayList;

public class SchoolServices {

    private ArrayList<School> schools = new ArrayList<>();
    private ArrayList<Student> students = new ArrayList<>();
    private ArrayList<Teacher> teachers = new ArrayList<>();
    private ArrayList<Grade> grades = new ArrayList<>();



    public void addTeacher(Teacher teacher) {
        teachers.add(teacher);
    }

    public void addGrade(Grade grade) {
        grades.add(grade);
    }

    // Find student by name (case-insensitive)
    public Student findStudentByName(String name) {
        for (Student student : students) {
            if (student.getName().equalsIgnoreCase(name)) {
                return student;
            }
        }
        return null;
    }


}
