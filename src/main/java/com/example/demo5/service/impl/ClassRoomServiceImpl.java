package com.example.demo5.service.impl;

import com.example.demo5.dao.ClassRoomRepository;
import com.example.demo5.entities.ClassRoom;
import com.example.demo5.service.ClassRoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClassRoomServiceImpl implements ClassRoomService {
    @Autowired
    private final ClassRoomRepository classRoomRepository;

    public ClassRoomServiceImpl(ClassRoomRepository classRoomRepository) {
        this.classRoomRepository = classRoomRepository;
    }

    @Override
    public List<ClassRoom> getAllClassRoom() {
        return classRoomRepository.findAll();
    }

    @Override
    public Optional<ClassRoom> getClassRoombyId(String id) {
        return classRoomRepository.findById(id);
    }

    @Override
    public void addClassRoom(ClassRoom classRoom) {
        classRoomRepository.save(classRoom);
    }

    @Override
    public void updateClassRoom() {

    }

    @Override
    public void deleteClassRoom(String id) {
        classRoomRepository.deleteById(id);
    }
}
