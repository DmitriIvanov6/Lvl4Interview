package ru.gb.rest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.gb.persist.Student;
import ru.gb.persist.StudentRepository;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/students")
public class StudentController {


    private final StudentRepository repository;

    public StudentController(StudentRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/all")
    public ResponseEntity<List<Student>> findAll() {
        return ResponseEntity.ok(repository.findAll()) ;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Student> findById(@PathVariable("id") long id) {
        if (repository.findById(id).isEmpty()){
            throw new BadDataException("User with id = " + id + " not exists. Timestamp: " + LocalDateTime.now());
        }
        return ResponseEntity.ok(repository.findById(id).get());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Student create(@RequestBody Student student) {
        if (student.getId() != null) {
            throw new IllegalArgumentException("Can't create user with id not null. Timestamp: " + LocalDateTime.now());
        }
        return repository.save(student);
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping
    public Student update(@RequestBody Student student) {
        if (student.getId() == null) {
            throw new IllegalArgumentException("Can't update user with id null. Timestamp: " + LocalDateTime.now());
        }
        return repository.save(student);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable long id) {
        if (repository.findById(id).isEmpty()){
            throw new BadDataException("User with id = " + id + " not exists. Timestamp: " + LocalDateTime.now());
        }
        repository.deleteById(id);
    }


}
