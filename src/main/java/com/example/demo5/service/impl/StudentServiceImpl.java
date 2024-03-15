package com.example.demo5.service.impl;

import com.example.demo5.dao.StudentRepository;
import com.example.demo5.entities.Student;
import com.example.demo5.service.StudentService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.List;
import java.util.Optional;

@Service
public class StudentServiceImpl implements StudentService {
    private final StudentRepository studentRepostirory;

    @Autowired
    public StudentServiceImpl(StudentRepository studentRepostirory) {
        this.studentRepostirory = studentRepostirory;
    }

    @Override
    public List<Student> getAllStudents() {
        return studentRepostirory.findAll();
    }

    @Override
    public Optional<Student> getStudentById(Integer id) {
        return studentRepostirory.findById(id);
    }

    @Override
    @Transactional
    public void addNewStudent(Student student) {
        studentRepostirory.save(student);
    }

    @Override
    public void updateStudent() {

    }

    @Override
    public void deleteStudent(Integer id) {
        studentRepostirory.deleteById(id);
    }

    public Page<Student> getStudents(Pageable pageable) {
        return studentRepostirory.findAll(pageable);
    }

    public Page<Student> searchStudents(String keyword, Pageable pageable) {
        return studentRepostirory.findByFirstNameContainingOrLastNameContainingOrEmailContaining(
                keyword, keyword, keyword, pageable
        );
    }
}