package com.diyo.studentbackend.controller;

import com.diyo.studentbackend.entity.Student;
import com.diyo.studentbackend.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("students")
@CrossOrigin("http://localhost:4200")
public class StudentController {
    @Autowired
    private StudentRepository studentRepository;

    @PostMapping
    public String saveStudent(@RequestBody Student student){
        studentRepository.save(student);
        return "Student saved successfully!";
    }

    @GetMapping("/{id}")
    public ResponseEntity<Student> getStudentById(@PathVariable("id") Long sid) {
        return ResponseEntity.ok(studentRepository.findById(sid).get());
    }

    @GetMapping
    public ResponseEntity<List<Student>> getAllStudents(){
        return ResponseEntity.ok(studentRepository.findAll());
    }

    @DeleteMapping("/{sid}")
    public ResponseEntity<String> deleteStudent(@PathVariable("sid") Long sid){
        studentRepository.deleteById(sid);
        return ResponseEntity.ok("Student deleted successfully!");
    }

    @PutMapping("/{sid}")
    public ResponseEntity<String> updateStudent(@PathVariable("sid") Long sid, @RequestBody Student student) {
        Optional<Student> std = this.studentRepository.findById(sid);
        if(std.isPresent()) {
            student.setSid(std.get().getSid());
            this.studentRepository.save(student);
            return ResponseEntity.ok("Student updated successfully!");
        }
        else {
            return ResponseEntity.ok("Student not found!");
        }
    }

    @GetMapping("/search/{firstName}")
    public ResponseEntity<List<Student>> searchStudent(@PathVariable("firstName") String firstName){
        return ResponseEntity.ok(this.studentRepository.findByFirstNameContaining(firstName));
    }
}
