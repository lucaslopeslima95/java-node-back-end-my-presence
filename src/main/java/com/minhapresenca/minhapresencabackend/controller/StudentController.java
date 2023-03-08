package com.minhapresenca.minhapresencabackend.controller;


import com.minhapresenca.minhapresencabackend.DTO.StudentDTO;
import com.minhapresenca.minhapresencabackend.entity.Student;
import com.minhapresenca.minhapresencabackend.service.LogService;
import com.minhapresenca.minhapresencabackend.servicesImplementations.StudentServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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
  public ResponseEntity<Student> save(@RequestBody StudentDTO studentDTO) {
    //logService.saveLogToServer(studentDTO.name(), "Usuario","Create Student");
    return studentService.save(studentDTO);
  }

  @GetMapping
  public List<Student> getAll(){
    return studentService.getAll();
  }

  @DeleteMapping(path ={"/{id}"})
  public ResponseEntity<Void> delete(@PathVariable Long id){
    return studentService.delete(id);
  }
  @PutMapping(path ={"/{id}"})
  public ResponseEntity<Student> update(@RequestBody StudentDTO studentDTO, @PathVariable Long id) {
  return new ResponseEntity<>(studentService.update(id, studentDTO), HttpStatus.ACCEPTED);
  }
}
