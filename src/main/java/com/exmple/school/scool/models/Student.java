package com.exmple.school.scool.models;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Student {

    private final StringProperty id;
    private final StringProperty name;
    private final StringProperty email;
    private final StringProperty phone;
    private final StringProperty gender;

    public Student(String id, String name, String email, String phone, String gender) {
        this.id = new SimpleStringProperty(id);
        this.name = new SimpleStringProperty(name);
        this.email = new SimpleStringProperty(email);
        this.phone = new SimpleStringProperty(phone);
        this.gender = new SimpleStringProperty(gender);
    }

    // ID
    public String getId() {
        return id.get();
    }

    public StringProperty idProperty() {
        return id;
    }

    // Name
    public String getName() {
        return name.get();
    }

    public StringProperty nameProperty() {
        return name;
    }

    // Email
    public String getEmail() {
        return email.get();
    }

    public StringProperty emailProperty() {
        return email;
    }

    // Phone
    public String getPhone() {
        return phone.get();
    }

    public StringProperty phoneProperty() {
        return phone;
    }

    // Gender
    public String getGender() {
        return gender.get();
    }

    public StringProperty genderProperty() {
        return gender;
    }
}
