package com.example.week7;

import com.example.week7.exceptions.BadRequestException;
import com.example.week7.models.Course;
import com.example.week7.models.Student;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Management {

    private static final Map<String, Course> COURSE_HASH_MAP = new HashMap<>();
    private static final Map<String, Student> STUDENT_HASH_MAP = new HashMap<>();
    private static final Map<String, Integer> STUDENT_GRADES = new HashMap<>();
    private static final Map<String, Integer> COURSE_ENROLLMENT_COUNT = new HashMap<>();

    public static void addCourse(Course course) {
        Course existingCourse = COURSE_HASH_MAP.get(course.getCode());
        if (existingCourse != null) {
            throw new BadRequestException("Course with code already exists");
        }
        COURSE_HASH_MAP.put(course.getCode(), course);
    }

    public static void addStudent(Student student) {
        Student existingStudent = STUDENT_HASH_MAP.get(student.getId());
        if (existingStudent != null) {
            throw new BadRequestException("Student with ID already exists");
        }
        STUDENT_HASH_MAP.put(student.getId(), student);
    }

    public static void updateStudent(Student student) {
        Student existingStudent = STUDENT_HASH_MAP.get(student.getId());
        if (existingStudent == null) {
            throw new BadRequestException("Student not found");
        }
        STUDENT_HASH_MAP.put(student.getId(), student);
    }


    public static void enrollStudent(String studentId, String courseCode) {
        Student student = STUDENT_HASH_MAP.get(studentId);
        if (student == null) {
            throw new BadRequestException("Student not found");
        }
        Course course = COURSE_HASH_MAP.get(courseCode);
        if (course == null) {
            throw new BadRequestException("Course not found");
        }

        Integer enrollmentCount = COURSE_ENROLLMENT_COUNT.get(course.getCode());
        if (enrollmentCount == null) {
            enrollmentCount = 0;
        }
        course.enrollStudent(enrollmentCount);
        student.enrollCourse(course);
        enrollmentCount++;
        COURSE_ENROLLMENT_COUNT.put(course.getCode(), enrollmentCount);
    }

    public static void assignGrade(String studentId, String courseCode, int grade) {
        Student student = STUDENT_HASH_MAP.get(studentId);
        if (student == null) {
            throw new BadRequestException("Student not found");
        }
        Course course = COURSE_HASH_MAP.get(courseCode);
        if (course == null) {
            throw new BadRequestException("Course not found");
        }
        student.assignGrade(course, grade);

        Integer totalGrade = STUDENT_GRADES.get(studentId);
        if (totalGrade == null) {
            totalGrade = 0;
        }
        totalGrade += grade;
        STUDENT_GRADES.put(studentId, totalGrade);
    }

    public static List<Course> getAllCourses() {
        return COURSE_HASH_MAP.values().stream().toList();
    }

    public static List<Student> getAllStudents() {
        return STUDENT_HASH_MAP.values().stream().toList();
    }

}
