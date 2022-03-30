package com.springsecdemo.student;

import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;


@RestController
@RequestMapping("management/api/v1/student")
public class StudentManagementController {

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

    @GetMapping
    public List<Student> getAllStudents()
    {
        return studentsList;
    }

    @PostMapping
    public void registerNewStudent(@RequestBody Student student)
    {
        System.out.println(student);
    }
    @DeleteMapping(path = "{studentId}")
    public void deleteStudent(@PathVariable("studentId") Integer studId)
    {
        System.out.println(studId);
    }
    @PutMapping(path = "{studentId}")
    public void updateStudent(@PathVariable("studentId")Integer studId,@RequestBody Student student)
    {
        System.out.println(String.format("%s %s", studId,student));
    }
}
