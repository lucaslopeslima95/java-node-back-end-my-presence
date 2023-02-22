package com.minhapresenca.minhapresencabackend.controller;


import com.minhapresenca.minhapresencabackend.entity.Student;
import com.minhapresenca.minhapresencabackend.entity.Teacher;
import com.minhapresenca.minhapresencabackend.service.impl.TeacherServiceImpl;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/teacher")
public class TeacherController {
  private final TeacherServiceImpl teacherService;

  public TeacherController(TeacherServiceImpl teacherService) {
    this.teacherService = teacherService;
  }

  @PostMapping
  public Teacher save(@RequestBody Teacher teacher) {
    return teacherService.save(teacher);
  }

  @GetMapping
  public List<Teacher> getAll(){
    return teacherService.getAll();
  }

  @DeleteMapping(path ={"/{id}"})
  public String delete(@PathVariable Long id){
    teacherService.delete(id);
    return "Deletado com Sucesso";
  }

  @PutMapping(path ={"/{id}"})
  public Student update(@RequestBody Teacher teacher, @PathVariable Long id) {
  return teacherService.update(id, teacher);
  }
}
