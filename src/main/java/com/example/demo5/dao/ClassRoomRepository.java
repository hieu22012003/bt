package com.example.demo5.dao;

import com.example.demo5.entities.ClassRoom;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClassRoomRepository extends JpaRepository<ClassRoom, String> {
}
