package com.minhapresenca.minhapresencabackend.service;


import com.minhapresenca.minhapresencabackend.DTO.TeacherDTO;
import com.minhapresenca.minhapresencabackend.entity.Teacher;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface TeacherService {

    ResponseEntity<Teacher> save(TeacherDTO teacherDTO);

    List<Teacher> getAll();

    Teacher update(Long id, TeacherDTO teacherDTO);

    ResponseEntity<Void> delete(Long id);
}
