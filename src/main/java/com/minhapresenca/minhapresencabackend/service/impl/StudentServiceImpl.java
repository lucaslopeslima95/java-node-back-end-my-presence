package com.minhapresenca.minhapresencabackend.service.impl;

import com.minhapresenca.minhapresencabackend.entity.Student;
import com.minhapresenca.minhapresencabackend.repository.StudentRepository;
import com.minhapresenca.minhapresencabackend.service.StudentService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {


  private StudentRepository repository;

  public StudentServiceImpl(StudentRepository repository) {
    this.repository = repository;
  }
  public Student create(Student form) {
    Student student = new Student();
    student.setName(form.getName());
    student.setCpf(form.getCpf());
    student.setNeighborhood(form.getNeighborhood());
    student.setAddress(form.getAddress());
    return repository.save(student);
  }

  public List<Student> getAll() {
    return repository.findAll();
  }

  @Override
  public Student update(Long id, Student studentUp) {
    Student student = repository.findById(id).get();
    student.setName(studentUp.getName());
    student.setNeighborhood(studentUp.getNeighborhood());
    return repository.saveAndFlush(student);
  }

  @Override
  public void delete(Long id) {
    repository.deleteById(id);
  }
}
