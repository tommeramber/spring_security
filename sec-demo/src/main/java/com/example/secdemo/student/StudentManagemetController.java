package com.example.secdemo.student;

import java.util.Arrays;
import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("management/api/v1/students")
public class StudentManagemetController {
    /* private static final List<Course> COURSES = Arrays.asList(
            new Course(1, "Course A"), 
            new Course(2, "Course B"),
            new Course(3, "Course C")); */

    private static final List<Student> STUDENTS = Arrays.asList(
        new Student(1, "lol A"), 
        new Student(2, "AAA B"),
        new Student(3, "KKK OOO"));


    /* PreAuthorized inputs: 
        haseRole('ROLE_')
        hasAnyRole('ROLE_')
        hasAuthority('permission')
        hasAnyAuthority('permission')
    */

    @GetMapping
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_ADMINTRAINEE')")
    public List<Student> getAllStudents(){
        System.out.println("getAllStudents");
        return STUDENTS;
    }

    @PostMapping()
    @PreAuthorize("hasAuthority('student:write')")
    public void registerNewStudent(@RequestBody Student student){
        //STUDENTS.add(student);
        System.out.println("registerNewStudent");
        System.out.println(student);
    }

    @DeleteMapping(path="{studentId}")
    @PreAuthorize("hasAuthority('student:write')")
    public void deleteStudent(@PathVariable("studentId") Integer studentId){
        //STUDENTS.add(student);
        System.out.println("deleteStudent");
        System.out.println(studentId);
    }

    @PutMapping(path="{studentId}")
    @PreAuthorize("hasAuthority('student:write')")
    public void updateStudent(@PathVariable("studentId") Integer studentId,@RequestBody Student student){
        //STUDENTS.add(student);
        System.out.println("updateStudent");
        System.out.println(String.format("%s %s",student, studentId));
    }


}