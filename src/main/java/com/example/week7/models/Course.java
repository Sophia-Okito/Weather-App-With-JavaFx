package com.example.week7.models;

import com.example.week7.exceptions.BadRequestException;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Course {

    private final StringProperty name;
    private final StringProperty code;
    private final IntegerProperty capacity;

    public Course(String name, String code, int capacity) {
        this.name = new SimpleStringProperty(name);
        this.code = new SimpleStringProperty(code);
        this.capacity = new SimpleIntegerProperty(capacity);
    }

    public String getName() {
        return name.get();
    }

    public StringProperty nameProperty() {
        return name;
    }

    public String getCode() {
        return code.get();
    }

    public StringProperty codeProperty() {
        return code;
    }

    public IntegerProperty capacityProperty() {
        return capacity;
    }

    public void enrollStudent(int totalEnrolled) {
        if (totalEnrolled == capacity.get()) {
            throw new BadRequestException("Course is full");
        }
    }

    @Override
    public String toString() {
        return this.code.get();
    }
}
