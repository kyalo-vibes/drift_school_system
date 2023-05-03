package com.drift.schoolsystem.service;

import com.drift.schoolsystem.model.Student;
import com.drift.schoolsystem.model.ExamResult;
import com.drift.schoolsystem.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

// StudentService provides methods to manage Student entities in the system.
@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    // Adds a new student to the system.
    public Student addStudent(Student student) {
        return studentRepository.save(student);
    }

    // Updates an existing student's information using the provided ID.
    public Student updateStudent(Long id, Student updatedStudent) {
        Optional<Student> studentOptional = studentRepository.findById(id);

        if (studentOptional.isPresent()) {
            Student student = studentOptional.get();
            student.setFirstName(updatedStudent.getFirstName());
            student.setLastName(updatedStudent.getLastName());
            return studentRepository.save(student);
        }
        return null;
    }

    // Deletes a student from the system using the provided ID.
    public boolean deleteStudent(Long id) {
        Optional<Student> studentOptional = studentRepository.findById(id);
        if (studentOptional.isPresent()) {
            studentRepository.deleteById(id);
            return true;
        }
        return false;
    }

    // Retrieves all students in the system.
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    // Retrieves a student by their ID.
    public Student getStudentById(Long id) {
        return studentRepository.findById(id).orElse(null);
    }

    // Retrieves a list of students by their full name.
    public List<Student> getStudentsByName(String name) {
        return studentRepository.findAll().stream()
                .filter(student -> (student.getFirstName() + " " + student.getLastName()).equalsIgnoreCase(name))
                .collect(Collectors.toList());
    }

    // Filters the students by their full name.
    public List<Student> filterStudentsByName(String name) {
        return studentRepository.findAll().stream()
                .filter(student -> (student.getFirstName() + " " + student.getLastName()).equalsIgnoreCase(name))
                .collect(Collectors.toList());
    }

}
