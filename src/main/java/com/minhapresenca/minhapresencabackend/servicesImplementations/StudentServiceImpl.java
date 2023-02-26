package com.minhapresenca.minhapresencabackend.servicesImplementations;

import com.minhapresenca.minhapresencabackend.entity.Student;
import com.minhapresenca.minhapresencabackend.DTO.StudentDTO;
import com.minhapresenca.minhapresencabackend.repository.ClassRepository;
import com.minhapresenca.minhapresencabackend.repository.StudentRepository;
import com.minhapresenca.minhapresencabackend.service.StudentService;
import lombok.Builder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Builder
public class StudentServiceImpl implements StudentService {

  private StudentRepository repository;
  private ClassRepository classRepository;

  public StudentServiceImpl(StudentRepository repository) {
    this.repository = repository;
  }
  public Student save(StudentDTO studentDTO) {
    Student student = Student
            .builder()
            .name(studentDTO.name())
            .cpf(studentDTO.cpf())
            .address(studentDTO.address())
            .neighborhood(studentDTO.neighborhood())
            .aClass(classRepository.findById(studentDTO.idClass()).get())
            .build();
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
