package ru.gb.persist;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestMapping;

@Repository
@RequestMapping(value = "/students")
public interface StudentRepository extends JpaRepository<Student, Long> {


}
