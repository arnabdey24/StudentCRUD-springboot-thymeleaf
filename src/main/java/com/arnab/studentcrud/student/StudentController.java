package com.arnab.studentcrud.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class StudentController {

    private final StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }


    @GetMapping(path = "/")
    public String viewHomePage(Model model){
        model.addAttribute("studentList",studentService.getStudents());
        return "index";
    }

    @GetMapping(path = "/addStudentForm")
    public String viewAddStudentPage(Model model){
        Student student=new Student();
        model.addAttribute("student",student);
        return "addForm";
    }

    @PostMapping(path = "/saveStudent")
    public String saveStudent(@ModelAttribute("student") Student student){
        studentService.addStudent(student);
        return "redirect:/";
    }

    @GetMapping(path = "/showUpdateForm/{id}")
    public String showUpdateForm(@PathVariable("id") Long id, Model model){
        Student student=studentService.getStudentById(id);
        model.addAttribute("student",student);
        return "updateForm";
    }

    @GetMapping(path = "/removeStudent/{id}")
    public String removeStudent(@PathVariable("id") Long id){
        studentService.removeStudentById(id);
        return "redirect:/";
    }
}
