package com.minhapresenca.minhapresencabackend.servicesImplementations;

import com.minhapresenca.minhapresencabackend.Exception.UserAlreadyExistsException;
import com.minhapresenca.minhapresencabackend.entity.Student;
import com.minhapresenca.minhapresencabackend.DTO.StudentDTO;
import com.minhapresenca.minhapresencabackend.entity.User;
import com.minhapresenca.minhapresencabackend.repository.ClassRepository;
import com.minhapresenca.minhapresencabackend.repository.StudentRepository;
import com.minhapresenca.minhapresencabackend.repository.UserRepository;
import com.minhapresenca.minhapresencabackend.service.StudentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class StudentServiceImpl implements StudentService {

  private final StudentRepository studentRepository;
  private final ClassRepository classRepository;
  private final UserRepository userRepository;

  public StudentServiceImpl(StudentRepository studentRepository, ClassRepository classRepository, UserRepository userRepository) {
    this.studentRepository = studentRepository;
    this.classRepository = classRepository;
    this.userRepository = userRepository;
  }
  public ResponseEntity<Student> save(StudentDTO studentDTO) {
    Student student;
    try{
      User user = User
              .builder()
              .password(BCrypt.hashpw(studentDTO.password(), BCrypt.gensalt()))
              .email(studentDTO.email())
              .build();
      User userSaved = userRepository.save(user);
       student = Student
              .builder()
               .user(userSaved)
              .name(studentDTO.name())
              .cpf(studentDTO.cpf())
              .address(studentDTO.address())
              .neighborhood(studentDTO.neighborhood())
              .aClass(classRepository.findById(studentDTO.idClass()).get())
              .build();
    } catch (  UserAlreadyExistsException ex) {
    return ResponseEntity.status(HttpStatus.CONFLICT).build();
  } catch (Exception ex) {
    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
  }
    return new ResponseEntity<>(studentRepository.save(student), HttpStatus.CREATED);
}

  public List<Student> getAll() {
    return studentRepository.findAll();
  }

  @Override
  public Student update(Long id, StudentDTO studentDTO) {
    if(id == null ||  studentDTO == null){
            throw  new IllegalArgumentException("Id and user cant is null");
    }
    Optional<Student> optionalStudent = studentRepository.findById(id);
    if (optionalStudent.isPresent()){
      Student student = optionalStudent.get();
      student.setName(studentDTO.name());
      student.setNeighborhood(studentDTO.neighborhood());
      return studentRepository.saveAndFlush(student);
    }
    return optionalStudent.orElse(null);
  }
  @Override
  public ResponseEntity<Void> delete(Long id) {
      try{
        studentRepository.deleteById(id);
        return ResponseEntity.ok().build();
      }catch (NoSuchElementException e ){
        return ResponseEntity.notFound().build();
      }catch (Exception e ){
        return  ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
      }
  }
}
