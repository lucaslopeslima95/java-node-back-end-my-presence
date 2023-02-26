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

  private StudentRepository studentRepository;
  private ClassRepository classRepository;

  public StudentServiceImpl(StudentRepository studentRepository) {
    this.studentRepository = studentRepository;
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
    return studentRepository.save(student);
  }

  public List<Student> getAll() {
    return studentRepository.findAll();
  }

  @Override
  public Student update(Long id, Student studentUp) {
    Student student = studentRepository.findById(id).get();
    student.setName(studentUp.getName());
    student.setNeighborhood(studentUp.getNeighborhood());
    return studentRepository.saveAndFlush(student);
  }

  @Override
  public void delete(Long id) {
    studentRepository.deleteById(id);
  }

}
