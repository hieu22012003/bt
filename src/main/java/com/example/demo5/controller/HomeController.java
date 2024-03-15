package com.example.demo5.controller;

import com.example.demo5.entities.Student;
import com.example.demo5.service.impl.ClassRoomServiceImpl;
import com.example.demo5.service.impl.StudentServiceImpl;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/")
public class HomeController {
    private final ClassRoomServiceImpl classRoomService;


    private final StudentServiceImpl studentService;

    public HomeController(ClassRoomServiceImpl classRoomService, StudentServiceImpl studentService) {
        this.classRoomService = classRoomService;
        this.studentService = studentService;
    }

    @GetMapping("/")
    public String getStudents(
            @RequestParam(name = "page", defaultValue = "1") int page,
            @RequestParam(name = "search", defaultValue = "") String search,
            Model model
    ) {
        int pageSize = 5; // Number of students per page

        Pageable pageable = PageRequest.of(page - 1, pageSize);
        Page<Student> pagedStudents;

        if (search != null && !search.isEmpty()) {
            pagedStudents = studentService.searchStudents(search, pageable);
        } else {
            pagedStudents = studentService.getStudents(pageable);
        }

        model.addAttribute("pagedStudents", pagedStudents.getContent());
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", pagedStudents.getTotalPages());
        model.addAttribute("search", search);

        return "/index";
    }
    @GetMapping("/pricing")
    public String Pricing(){
        return "/pricing";
    }

}
