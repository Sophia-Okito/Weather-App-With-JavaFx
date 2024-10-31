package com.example.week7.models;

import com.example.week7.exceptions.BadRequestException;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.util.HashMap;
import java.util.Map;

public class Student {
    private final StringProperty name;
    private final StringProperty id;
    private final Map<Course, Integer> enrolledCourses = new HashMap<>();

    public Student(String id, String name) {
        this.id = new SimpleStringProperty(id);
        this.name = new SimpleStringProperty(name);
    }

    public StringProperty nameProperty() {
        return name;
    }

    public StringProperty idProperty() {
        return id;
    }

    public String getId() {
        return id.get();
    }

    public String getName() {
        return name.get();
    }

    public StringProperty getCourses() {
        StringBuilder courses = new StringBuilder();
        courses.append("Name\t\t\tGrade\n");
        courses.append("-----------------\n");
        for (Course course : enrolledCourses.keySet()) {
            courses.append(String.format("%-15s %d\n", course.getName(), enrolledCourses.get(course)));
        }
        return new SimpleStringProperty(courses.toString());
    }

    public void enrollCourse(Course course) {
        if (enrolledCourses.containsKey(course)) {
            throw new BadRequestException("Student is already enrolled in this course");
        }
        enrolledCourses.put(course, 0);
    }

    public void assignGrade(Course course, int grade) {
        if (!enrolledCourses.containsKey(course)) {
            throw new BadRequestException("Student is not enrolled in this course");
        }
        enrolledCourses.put(course, grade);
    }

    @Override
    public String toString() {
        return this.id.get();
    }
}
