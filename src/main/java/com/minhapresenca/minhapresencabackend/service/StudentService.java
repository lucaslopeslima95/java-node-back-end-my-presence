package com.minhapresenca.minhapresencabackend.service;


import com.minhapresenca.minhapresencabackend.entity.Student;

import java.util.List;

public interface StudentService {

  Student create(Student student);

  List<Student> getAll();

  Student update(Long id, Student formUpdate);

  void delete(Long id);
}
