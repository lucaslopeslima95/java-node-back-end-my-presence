package com.minhapresenca.minhapresencabackend.service;


import com.minhapresenca.minhapresencabackend.entity.Student;
import com.minhapresenca.minhapresencabackend.DTO.StudentDTO;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface StudentService {

  ResponseEntity<Student> save(StudentDTO student);

  List<Student> getAll();

  Student update(Long id, StudentDTO studentDTO);

  ResponseEntity<Void> delete(Long id);

}
