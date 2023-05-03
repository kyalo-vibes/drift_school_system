package com.drift.schoolsystem.controller;

import com.drift.schoolsystem.model.Student;
import com.drift.schoolsystem.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// StudentController handles HTTP requests related to student management.
@RestController
@RequestMapping("/api/students")
public class StudentController {

    // StudentService handles the business logic for student management.
    @Autowired
    private StudentService studentService;

    // addStudent creates a new student record.
    @PostMapping
    public Student addStudent(@RequestBody Student student) {
        return studentService.addStudent(student);
    }

    // updateStudent updates an existing student record.
    @PutMapping("/{id}")
    public Student updateStudent(@PathVariable Long id, @RequestBody Student updatedStudent) {
        return studentService.updateStudent(id, updatedStudent);
    }

    // deleteStudent removes a student record.
    @DeleteMapping("/{id}")
    public boolean deleteStudent(@PathVariable Long id) {
        return studentService.deleteStudent(id);
    }

    // getAllStudents retrieves all student records.
    @GetMapping
    public List<Student> getAllStudents() {
        return studentService.getAllStudents();
    }

    // getStudentById retrieves a student record by its ID.
    @GetMapping("/{id}")
    public Student getStudentById(@PathVariable Long id) {
        return studentService.getStudentById(id);
    }
}
