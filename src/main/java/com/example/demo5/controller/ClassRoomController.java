package com.example.demo5.controller;


import com.example.demo5.entities.ClassRoom;
import com.example.demo5.service.impl.ClassRoomServiceImpl;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/classroom")
public class ClassRoomController {
    private final ClassRoomServiceImpl classRoomService;

    public ClassRoomController(ClassRoomServiceImpl classRoomService) {
        this.classRoomService = classRoomService;
    }

    @GetMapping("/list")
    public String GetClasss(Model model) {
        List<ClassRoom> classrooms = classRoomService.getAllClassRoom();
        model.addAttribute("classrooms", classrooms);
        return "classroom/index";
    }
    @GetMapping("/formAdd")
    public String ShowFormAdd(Model model) {
        ClassRoom classRoom = new ClassRoom();
        model.addAttribute("classroom", classRoom);
        return "classroom/Form";
    }
    @PostMapping("/save")
    public String SaveClassRoom(@Valid @ModelAttribute("classroom") ClassRoom classRoom, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "classroom/Form";
        } else {
            classRoomService.addClassRoom(classRoom);
            return "redirect:/classroom/list";
        }
    }

    @GetMapping("formUpdate")
    public String ShowFormUpdate(@RequestParam("classroomId") String id, Model model) {
        Optional<ClassRoom> classRoom = classRoomService.getClassRoombyId(id);
        model.addAttribute("classroom", classRoom);
        return "classroom/Form";
    }

    @GetMapping("delete")
    public String DeleteClassroom(@RequestParam("classroomId") String id, Model model) {
        classRoomService.deleteClassRoom(id);
        return "redirect:/classroom/list";
    }
}
