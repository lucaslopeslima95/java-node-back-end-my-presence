package com.minhapresenca.minhapresencabackend.service;

import com.minhapresenca.minhapresencabackend.entity.Class;
import com.minhapresenca.minhapresencabackend.DTO.ClassDTO;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ClassService {

  ResponseEntity<Class> save(ClassDTO Class);

  List<Class> getAll();

  Class update(Long id, ClassDTO classDTO);

  ResponseEntity<Void> delete(Long id);
}
