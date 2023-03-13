package com.minhapresenca.minhapresencabackend.controller;


import com.minhapresenca.minhapresencabackend.DTO.TeacherDTO;
import com.minhapresenca.minhapresencabackend.entity.Teacher;
import com.minhapresenca.minhapresencabackend.servicesImplementations.TeacherServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@RestController
@RequestMapping("/teacher")
@CrossOrigin(origins = "*")
public class TeacherController {
  private final TeacherServiceImpl teacherService;
  public TeacherController(TeacherServiceImpl teacherService) {
    this.teacherService = teacherService;
  }

  @PostMapping("/save")
  public ResponseEntity<Teacher> save(@Valid @RequestBody TeacherDTO teacherDTO) {
    return teacherService.save(teacherDTO);
  }

  @GetMapping("/list-all")
  public List<Teacher> getAll(){
    return teacherService.getAll();
  }

  @DeleteMapping("/delete-teacher/{id}")
  public ResponseEntity<Void> delete(@PathVariable Long id) {
    return teacherService.delete(id);
  }

  @PutMapping(path ={"/update/teacher{id}"})
  public ResponseEntity<Teacher> update(@RequestBody TeacherDTO teacherDTO, @PathVariable Long id) {
    return  new ResponseEntity<>(teacherService.update(id, teacherDTO), HttpStatus.ACCEPTED);
  }
}
