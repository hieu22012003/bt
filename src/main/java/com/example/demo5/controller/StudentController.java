package com.example.demo5.controller;

import com.example.demo5.entities.Student;
import com.example.demo5.service.impl.StudentServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/student")
public class StudentController {
 private final StudentServiceImpl studentService;

    public StudentController(StudentServiceImpl studentService) {
        this.studentService = studentService;
    }

    @InitBinder
    public void initBinder(WebDataBinder dataBinder) {
        StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);
        dataBinder.registerCustomEditor(String.class,stringTrimmerEditor);
    }



    @GetMapping("/list")
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

        return "student/index";
    }




    @GetMapping("/formAdd")
    public String ShowFormAdd(Model model) {
        Student student = new Student();
        model.addAttribute("student", student);
        return "student/Form";
    }

    @PostMapping("/save")
    public String SaveStudent(@Valid @ModelAttribute("student") Student student, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "student/Form";
        } else {
            studentService.addNewStudent(student);
            return "redirect:/student/list";
        }
    }

    @GetMapping("formUpdate")
    public String ShowFormUpdate(@RequestParam("studentId") Integer id, Model model) {
        Optional<Student> student = studentService.getStudentById(id);
        model.addAttribute("student", student);
        return "student/Form";
    }

    @GetMapping("delete")
    public String DeleteStudent(@RequestParam("studentId") Integer id, Model model) {
        studentService.deleteStudent(id);
        return "redirect:/student/list";
    }

}
