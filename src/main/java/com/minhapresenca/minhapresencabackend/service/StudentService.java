package com.minhapresenca.minhapresencabackend.service;


import com.minhapresenca.minhapresencabackend.entity.Student;
import com.minhapresenca.minhapresencabackend.entity.form.StudentForm;

import java.util.List;

public interface StudentService {

  Student save(StudentForm student);

  List<Student> getAll();

  Student update(Long id, Student student);

  void delete(Long id);
}
