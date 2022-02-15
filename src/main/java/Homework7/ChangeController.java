package Homework7;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(value = "/change")
public class ChangeController {

    public StudentRepository repository;

    public ChangeController(StudentRepository repository) {
        this.repository = repository;
    }


    @GetMapping
    public String changeShow (Model model, @RequestParam Long id, @RequestParam String name, @RequestParam int age) {
        Student student = new Student();
        model.addAttribute("student", student);
        student.setId(id);
        student.setName(name);
        student.setAge(age);
        return "/change";
    }

    @PostMapping
    public String changeStudent(@ModelAttribute("student") Student studentNew){
        repository.save(studentNew);
        return "redirect:/students";

    }
}
