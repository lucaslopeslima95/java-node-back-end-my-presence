package com.minhapresenca.minhapresencabackend.servicesImplementations;

import com.minhapresenca.minhapresencabackend.Exception.ClassNotExistsException;
import com.minhapresenca.minhapresencabackend.Exception.UserAlreadyExistsException;
import com.minhapresenca.minhapresencabackend.entity.Student;
import com.minhapresenca.minhapresencabackend.DTO.StudentDTO;
import com.minhapresenca.minhapresencabackend.entity.User;
import com.minhapresenca.minhapresencabackend.repository.ClassRepository;
import com.minhapresenca.minhapresencabackend.repository.StudentRepository;
import com.minhapresenca.minhapresencabackend.service.StudentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class StudentServiceImpl implements StudentService {

  private final StudentRepository studentRepository;
  private final ClassRepository classRepository;
  private final UserServiceImpl userService;

  public StudentServiceImpl(StudentRepository studentRepository, ClassRepository classRepository, UserServiceImpl userService) {
    this.studentRepository = studentRepository;
    this.classRepository = classRepository;
    this.userService = userService;
  }
  public ResponseEntity<Student> save(StudentDTO studentDTO) {
    Student student;
    try{
       User userSaved = userService.buildUser(studentDTO.email(),studentDTO.password());
       student = Student
              .builder()
               .user(userSaved)
              .name(studentDTO.name())
              .cpf(studentDTO.cpf())
              .address(studentDTO.address())
              .neighborhood(studentDTO.neighborhood())
              .aClass(classRepository.findById(studentDTO.idClass()).get())
              .build();
    } catch (UserAlreadyExistsException ex) {
    return ResponseEntity.status(HttpStatus.CONFLICT).build();
    }catch (ClassNotExistsException e){
      return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).build();
    }
    catch (Exception ex) {
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

      userService.update(optionalStudent.get().getUser().getId(), studentDTO.email(), studentDTO.password());
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

  @Override
  public List<Student> findByClassId(Long id) {
    return studentRepository.findStudentsByIdClass(id);
  }
}
