package com.minhapresenca.minhapresencabackend.servicesImplementations;

import com.minhapresenca.minhapresencabackend.Exception.UserAlreadyExistsException;
import com.minhapresenca.minhapresencabackend.entity.Teacher;
import com.minhapresenca.minhapresencabackend.DTO.TeacherDTO;
import com.minhapresenca.minhapresencabackend.entity.User;
import com.minhapresenca.minhapresencabackend.repository.TeacherRepository;
import com.minhapresenca.minhapresencabackend.repository.UserRepository;
import com.minhapresenca.minhapresencabackend.service.TeacherService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class TeacherServiceImpl implements TeacherService {

  private TeacherRepository teacherRepository;
  private UserRepository userRepository;

  public TeacherServiceImpl(TeacherRepository teacherRepository) {
    this.teacherRepository = teacherRepository;
  }

  public ResponseEntity<Teacher> save(TeacherDTO teacherDTO) {
    Teacher teacher;
    try {
      User user = User
              .builder()
              .password(teacherDTO.password())
              .email(teacherDTO.email())
              .build();
      User userSaved = userRepository.save(user);
      teacher = Teacher.builder()
              .name(teacherDTO.name())
              .subject(teacherDTO.name())
              .user(userSaved)
              .build();
    } catch (UserAlreadyExistsException ex) {
      return ResponseEntity.status(HttpStatus.CONFLICT).build();
    } catch (Exception ex) {
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }
    return new ResponseEntity<>(teacherRepository.save(teacher), HttpStatus.CREATED);
  }

  @Override
  public List<Teacher> getAll() {
    return teacherRepository.findAll();
  }

  @Override
  public Teacher update(Long id, TeacherDTO teacherDTO) {
    if (id == null || teacherDTO == null) {
      throw new IllegalArgumentException("ID e User não podem ser nulos");
    }
    Optional<Teacher> optionalTeacher = teacherRepository.findById(id);
    if (optionalTeacher.isPresent()) {
      Teacher teacher = optionalTeacher.get();
      teacher.setName(teacherDTO.name());
      teacher.setSubject(teacherDTO.subject());
      return teacherRepository.saveAndFlush(teacher);
    }
    return optionalTeacher.orElse(null);
  }

  @Override
  public ResponseEntity<Void> delete(Long id) {
    try {
      teacherRepository.deleteById(id);
      return ResponseEntity.ok().build();
    } catch (NoSuchElementException e) {
      return ResponseEntity.notFound().build();
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }
  }
}
