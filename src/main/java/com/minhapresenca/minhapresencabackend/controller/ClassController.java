package com.minhapresenca.minhapresencabackend.controller;


import com.minhapresenca.minhapresencabackend.DTO.ClassDTO;
import com.minhapresenca.minhapresencabackend.entity.Class;
import com.minhapresenca.minhapresencabackend.service.LogService;
import com.minhapresenca.minhapresencabackend.servicesImplementations.ClassServiceImpl;
import com.minhapresenca.minhapresencabackend.servicesImplementations.StudentServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/class")
@CrossOrigin(origins = "*")
public class ClassController {
  private final ClassServiceImpl classService;
  private final LogService logService;

  public ClassController(ClassServiceImpl classService, StudentServiceImpl studentService, LogService logService) {
    this.classService = classService;
    this.logService = logService;
  }

  @PostMapping("/save-class")
  public ResponseEntity<Class> save(@RequestBody ClassDTO classDTO) {
    return classService.save(classDTO);
  }

  @GetMapping("/list-all-class")
  public List<Class> getAll(){
    return classService.getAll();
  }

  @DeleteMapping(path ={"/delete/{id}"})
  public ResponseEntity<Void> delete(@PathVariable Long id){
    return classService.delete(id);
  }

  @PutMapping(path ={"/update/{id}"})
  public ResponseEntity<Class> update(@RequestBody ClassDTO aClass, @PathVariable Long id) {
  return new ResponseEntity<>(classService.update(id, aClass), HttpStatus.ACCEPTED);
  }


}
