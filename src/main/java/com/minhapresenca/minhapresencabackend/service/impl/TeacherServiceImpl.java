package com.minhapresenca.minhapresencabackend.service.impl;

import com.minhapresenca.minhapresencabackend.entity.Student;
import com.minhapresenca.minhapresencabackend.entity.Teacher;
import com.minhapresenca.minhapresencabackend.entity.form.StudentForm;
import com.minhapresenca.minhapresencabackend.repository.StudentRepository;
import com.minhapresenca.minhapresencabackend.service.TeacherService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeacherServiceImpl implements TeacherService {

  @Override
  public Teacher save(Teacher student) {
    return null;
  }

  @Override
  public List<Teacher> getAll() {
    return null;
  }

  @Override
  public Student update(Long id, Teacher teacher) {
    return null;
  }

  @Override
  public void delete(Long id) {

  }
}
