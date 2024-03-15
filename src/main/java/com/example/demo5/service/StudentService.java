package com.example.demo5.service;

import com.example.demo5.entities.Student;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.List;
import java.util.Optional;

public interface StudentService {
    List<Student> getAllStudents();
    Optional<Student> getStudentById(Integer id);
    void addNewStudent(Student student);
    default void updateStudent() {}
    void deleteStudent(Integer id);
    public Page<Student> getStudents(Pageable pageable);
}
