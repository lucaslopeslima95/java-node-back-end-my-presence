package com.minhapresenca.minhapresencabackend.service;


import com.minhapresenca.minhapresencabackend.entity.Student;
import com.minhapresenca.minhapresencabackend.entity.Teacher;
import com.minhapresenca.minhapresencabackend.entity.form.StudentForm;

import java.util.List;

public interface TeacherService {

  Teacher save(Teacher student);

  List<Teacher> getAll();

  Student update(Long id, Teacher teacher);

  void delete(Long id);
}
