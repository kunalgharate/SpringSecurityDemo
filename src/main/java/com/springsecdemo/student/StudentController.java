package com.springsecdemo.student;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


@RestController
@RequestMapping("api/v1/student")
public class StudentController {

    private static final List<Student> studentsList = Arrays.asList(
            new Student(1, "Ram"),
            new Student(2, "Devyani"),
            new Student(3, "Kunal"));

    @GetMapping(path = "/{studentId}")
    public Student getStudent(@PathVariable("studentId") Integer studentId) {

        return studentsList.stream().filter(student ->
                        studentId.equals(student.getStudentId())).
                findFirst().
                orElseThrow(() -> new IllegalStateException("Student " + studentId + " does not exists"));

    }
}
