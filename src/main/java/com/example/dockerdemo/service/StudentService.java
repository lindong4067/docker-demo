package com.example.dockerdemo.service;

import com.example.dockerdemo.entity.Student;

import java.util.Optional;

public interface StudentService {
    Student create(Student student);
    void remove(Integer id);
    Optional<Student> get(Integer id);
    Optional<Student> update(Integer id, Student student);
}
