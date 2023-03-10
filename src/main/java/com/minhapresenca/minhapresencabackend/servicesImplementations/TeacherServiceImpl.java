package com.minhapresenca.minhapresencabackend.servicesImplementations;

import com.minhapresenca.minhapresencabackend.Exception.UserAlreadyExistsException;
import com.minhapresenca.minhapresencabackend.entity.Teacher;
import com.minhapresenca.minhapresencabackend.DTO.TeacherDTO;
import com.minhapresenca.minhapresencabackend.entity.User;
import com.minhapresenca.minhapresencabackend.repository.TeacherRepository;
import com.minhapresenca.minhapresencabackend.service.TeacherService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class TeacherServiceImpl implements TeacherService {

  private TeacherRepository teacherRepository;
  private UserServiceImpl userService;

  public TeacherServiceImpl(TeacherRepository teacherRepository, UserServiceImpl userService) {
    this.teacherRepository = teacherRepository;
    this.userService = userService;
  }

  public ResponseEntity<Teacher> save(TeacherDTO teacherDTO) {
    Teacher teacher;
    try {
      User userSaved = userService.buildUser(teacherDTO.email(),teacherDTO.password());
      teacher = Teacher.builder()
              .name(teacherDTO.name())
              .subject(teacherDTO.subject())
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
    long id_user = 0;
    if (id == null || teacherDTO == null) {
      throw new IllegalArgumentException("ID e User n??o podem ser nulos");
    }
    Optional<Teacher> optionalTeacher = teacherRepository.findById(id);
    if (optionalTeacher.isPresent()) {
      Teacher teacher = optionalTeacher.get();
      teacher.setName(teacherDTO.name());
      teacher.setSubject(teacherDTO.subject());
      id_user = optionalTeacher.get().getUser().getId();
      userService.update(id_user, teacherDTO.email(), teacherDTO.password());
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
