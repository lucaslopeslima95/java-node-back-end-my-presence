package com.minhapresenca.minhapresencabackend.controller;


import com.minhapresenca.minhapresencabackend.entity.Student;
import com.minhapresenca.minhapresencabackend.entity.form.StudentForm;
import com.minhapresenca.minhapresencabackend.service.impl.StudentServiceImpl;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/presence")
public class PresenceController {
  private final StudentServiceImpl studentService;

  public PresenceController(StudentServiceImpl studentService) {
    this.studentService = studentService;
  }

  @PostMapping
  public Student save(@RequestBody StudentForm student) {
    return studentService.create(student);
  }

  @GetMapping
  public List<Student> getAll(){
    return studentService.getAll();
  }

  @DeleteMapping
  public String delete(@RequestBody Long id){
    studentService.delete(id);
    return "Deletado com Sucesso";
  }

  @PutMapping(path ={"/{id}"})
  public Student update(@RequestBody Student student, @PathVariable Long id) {
  return studentService.update(id, student);
  }


}
