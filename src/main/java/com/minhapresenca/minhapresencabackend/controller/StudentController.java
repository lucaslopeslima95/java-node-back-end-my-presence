package com.minhapresenca.minhapresencabackend.controller;


import com.minhapresenca.minhapresencabackend.entity.Student;
import com.minhapresenca.minhapresencabackend.entity.form.StudentForm;
import com.minhapresenca.minhapresencabackend.service.impl.StudentServiceImpl;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/alunos")
public class StudentController {
  private final StudentServiceImpl alunoService;

  public StudentController(StudentServiceImpl alunoService) {
    this.alunoService = alunoService;
  }

  @PostMapping
  public Student save(@RequestBody StudentForm studentForm) {
    return alunoService.create(studentForm);
  }

  @GetMapping
  public List<Student> getAll(){
    return alunoService.getAll();
  }

  @DeleteMapping(path ={"/{id}"})
  public String delete(@PathVariable Long id){
    alunoService.delete(id);
    return "Deletado com Sucesso";
  }

  @PutMapping(path ={"/{id}"})
  public Student update(@RequestBody Student student, @PathVariable Long id) {
  return alunoService.update(id, student);
  }


}
