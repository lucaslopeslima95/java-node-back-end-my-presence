package com.minhapresenca.minhapresencabackend.controller;


import com.minhapresenca.minhapresencabackend.DTO.ClassDTO;
import com.minhapresenca.minhapresencabackend.entity.Class;
import com.minhapresenca.minhapresencabackend.servicesImplementations.ClassServiceImpl;
import com.minhapresenca.minhapresencabackend.servicesImplementations.StudentServiceImpl;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/class")
@CrossOrigin(origins = "*")
public class ClassController {
  private final ClassServiceImpl classService;
  private final StudentServiceImpl studentService;

  public ClassController(ClassServiceImpl classService, StudentServiceImpl studentService) {
    this.classService = classService;
    this.studentService = studentService;
  }

  @PostMapping
  public Class save(@RequestBody ClassDTO classStudent) {
    return classService.create(classStudent);
  }

  @GetMapping
  public List<Class> getAll(){
    return classService.getAll();
  }

  @DeleteMapping(path ={"/{id}"})
  public String delete(@PathVariable Long id){
    classService.delete(id);
    return "Deletado com Sucesso";
  }

  @PutMapping(path ={"/{id}"})
  public Class update(@RequestBody Class aClass, @PathVariable Long id) {
  return classService.update(id, aClass);
  }


}
