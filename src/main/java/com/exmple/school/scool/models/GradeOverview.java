package com.exmple.school.scool.models;

public class GradeOverview {
    private String code;
    private String name;
    private String teacher;
    private int grade;

    // Constructor to initialize all fields
    public GradeOverview(String code, String name, String teacher, int grade) {
        this.code = code;
        this.name = name;
        this.teacher = teacher;
        this.grade = grade;
    }

    // Getters only (immutable after creation)
    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public String getTeacher() {
        return teacher;
    }

    public int getGrade() {
        return grade;
    }
}
