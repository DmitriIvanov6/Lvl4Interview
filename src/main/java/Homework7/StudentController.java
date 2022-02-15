package Homework7;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(value = "/students")
public class StudentController {

    StudentRepository repository;

    public StudentController (StudentRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public String showAll(Model model) {
        Student student = new Student();
        model.addAttribute("student", student);
        List<Student> studentList = new ArrayList<>();
        repository.findAll().forEach(studentList::add);
        model.addAttribute("students", studentList);
        return "/students";
    }

    @PostMapping
    public String save(@ModelAttribute("student") Student student) {
        repository.save(student);
        return "redirect:/students";
    }


    @GetMapping("/delete")
    public String delete(Long id) {
        repository.deleteById(id);
        return "redirect:/students";
    }







}
