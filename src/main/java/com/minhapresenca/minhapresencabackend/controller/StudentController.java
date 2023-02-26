package com.minhapresenca.minhapresencabackend.controller;


import com.minhapresenca.minhapresencabackend.entity.Student;
import com.minhapresenca.minhapresencabackend.DTO.StudentDTO;
import com.minhapresenca.minhapresencabackend.service.LogService;
import com.minhapresenca.minhapresencabackend.servicesImplementations.StudentServiceImpl;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/student")
@CrossOrigin(origins = "*")
public class StudentController {
  private final StudentServiceImpl studentService;
  private LogService logService;

  public StudentController(StudentServiceImpl studentService, LogService logService) {
    this.studentService = studentService;
    this.logService = logService;
  }

  @PostMapping
  public Student save(@RequestBody StudentDTO studentDTO) {
    logService.saveLogToServer(studentDTO.name(), "Usuario","Create Student");
    return studentService.save(studentDTO);
  }

  @GetMapping
  public List<Student> getAll(){
    return studentService.getAll();
  }

  @DeleteMapping(path ={"/{id}"})
  public String delete(@PathVariable Long id){
    studentService.delete(id);
    return "Deletado com Sucesso";
  }

  @PutMapping(path ={"/{id}"})
  public Student update(@RequestBody Student student, @PathVariable Long id) {
  return studentService.update(id, student);
  }
}
