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

  @PostMapping("/save-student")
  public ResponseEntity<Student> save(@RequestBody StudentDTO studentDTO) {
    return studentService.save(studentDTO);
  }

  @GetMapping("/list-all-student")
  public List<Student> getAll(){
    return studentService.getAll();
  }

  @DeleteMapping(path ={"/delete-by-id/{id}"})
  public ResponseEntity<Void> delete(@PathVariable Long id){
    return studentService.delete(id);
  }
  @PutMapping(path ={"/update-student/{id}"})
  public ResponseEntity<Student> update(@RequestBody StudentDTO studentDTO, @PathVariable Long id) {
  return new ResponseEntity<>(studentService.update(id, studentDTO), HttpStatus.ACCEPTED);
  }
  @GetMapping("/find-by-id-class/{id_class}")
  public ResponseEntity<List<Student>> findByIdClass(@PathVariable Long id_class){
    return new ResponseEntity<>(studentService.findByClassId(id_class),HttpStatus.ACCEPTED);
  }
}
