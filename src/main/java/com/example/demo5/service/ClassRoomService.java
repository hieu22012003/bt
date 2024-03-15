package com.example.demo5.service;

import com.example.demo5.entities.ClassRoom;

import java.util.List;
import java.util.Optional;

public interface ClassRoomService {
    List<ClassRoom>getAllClassRoom();
    Optional<ClassRoom> getClassRoombyId(String id);
    void  addClassRoom(ClassRoom classRoom);
    default void updateClassRoom(){}
    void deleteClassRoom(String id);
}
