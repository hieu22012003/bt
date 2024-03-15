package com.example.demo5.dao;

import com.example.demo5.entities.Student;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student,Integer> {

    Page<Student> findByFirstNameContainingOrLastNameContainingOrEmailContaining(String keyword, String keyword1, String keyword2, Pageable pageable);
}
