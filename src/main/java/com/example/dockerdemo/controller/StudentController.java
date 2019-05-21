package com.example.dockerdemo.controller;

import com.example.dockerdemo.entity.Student;
import com.example.dockerdemo.service.StudentService;
import org.springframework.web.bind.annotation.*;

@RestController
public class StudentController {

    private StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping("/students")
    public Student create(@RequestBody Student student) {
        return studentService.create(student);
    }

    @DeleteMapping("/students/{id}")
    public void remove(@PathVariable Integer id) {
        studentService.remove(id);
    }

    @GetMapping("/students/{id}")
    public Student get(@PathVariable Integer id) {
        return studentService.get(id).orElse(null);
    }

    @PutMapping("/students/{id}")
    public Student update(@PathVariable Integer id, @RequestBody Student student) {
        return studentService.update(id, student).orElse(null);
    }
}
