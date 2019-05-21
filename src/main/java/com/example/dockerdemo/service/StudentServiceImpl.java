package com.example.dockerdemo.service;

import com.example.dockerdemo.entity.Student;
import com.example.dockerdemo.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;

    public StudentServiceImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public Student create(Student student) {
        return studentRepository.save(student);
    }

    @Override
    public void remove(Integer id) {
        studentRepository.deleteById(id);
    }

    @Override
    public Optional<Student> get(Integer id) {
        return studentRepository.findById(id);
    }

    @Override
    public Optional<Student> update(Integer id, Student student) {
        return studentRepository.existsById(id) ? Optional.of(studentRepository.save(student)) : Optional.empty();
    }
}
