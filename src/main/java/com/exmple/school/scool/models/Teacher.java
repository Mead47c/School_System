package com.exmple.school.scool.models;

public class Teacher {
    private int id;
    private String name;
    private String email;
    private String phone;
    private String gender;

    public Teacher(int id, String name, String email, String phone, String gender) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.gender = gender;
    }

    // ID
    public int getId() {
        return id;
    }

    // Name
    public String getName() {
        return name;
    }

    // Email
    public String getEmail() {
        return email;
    }

    // Phone
    public String getPhone() {
        return phone;
    }

    // Gender
    public String getGender() {
        return gender;
    }
}
