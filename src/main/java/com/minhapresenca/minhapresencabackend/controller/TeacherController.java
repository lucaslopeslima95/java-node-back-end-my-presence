package com.minhapresenca.minhapresencabackend.controller;


import com.minhapresenca.minhapresencabackend.DTO.TeacherDTO;
import com.minhapresenca.minhapresencabackend.entity.Teacher;
import com.minhapresenca.minhapresencabackend.servicesImplementations.TeacherServiceImpl;
import com.minhapresenca.minhapresencabackend.servicesImplementations.UserServiceImpl;
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
  private final UserServiceImpl userService;

  public TeacherController(TeacherServiceImpl teacherService, UserServiceImpl userService) {
    this.teacherService = teacherService;
    this.userService = userService;
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
    try {
      Teacher updatedTeacher = teacherService.update(id, teacherDTO);
      return ResponseEntity.ok(updatedTeacher);
    } catch (NoSuchElementException e) {
      return ResponseEntity.notFound().build();
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }
  }

}
