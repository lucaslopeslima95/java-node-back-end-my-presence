package com.minhapresenca.minhapresencabackend.controller;


import com.minhapresenca.minhapresencabackend.DTO.TeacherDTO;
import com.minhapresenca.minhapresencabackend.entity.Teacher;
import com.minhapresenca.minhapresencabackend.servicesImplementations.TeacherServiceImpl;
import com.minhapresenca.minhapresencabackend.servicesImplementations.UserServiceImpl;
import org.hibernate.annotations.ManyToAny;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/teacher")
@CrossOrigin(origins = "*")
public class TeacherController {
  private final TeacherServiceImpl teacherService;
  public TeacherController(TeacherServiceImpl teacherService, UserServiceImpl userService) {
    this.teacherService = teacherService;
  }

  @PostMapping
  public ResponseEntity<Teacher> save(@Valid @RequestBody TeacherDTO teacherDTO) {
    return teacherService.save(teacherDTO);
  }

  @GetMapping
  public List<Teacher> getAll(){
    return teacherService.getAll();
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> delete(@PathVariable Long id) {
    return teacherService.delete(id);
  }

  @PutMapping(path ={"/{id}"})
  public ResponseEntity<Teacher> update(@RequestBody TeacherDTO teacherDTO, @PathVariable Long id) {
    return  new ResponseEntity<>(teacherService.update(id, teacherDTO), HttpStatus.ACCEPTED);
  }
}
