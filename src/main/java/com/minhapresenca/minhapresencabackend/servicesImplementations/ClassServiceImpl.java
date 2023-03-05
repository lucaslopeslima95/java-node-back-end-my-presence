package com.minhapresenca.minhapresencabackend.servicesImplementations;


import com.minhapresenca.minhapresencabackend.entity.Class;
import com.minhapresenca.minhapresencabackend.DTO.ClassDTO;
import com.minhapresenca.minhapresencabackend.repository.ClassRepository;
import com.minhapresenca.minhapresencabackend.service.ClassService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;


@Service
public class ClassServiceImpl implements ClassService {

  private final ClassRepository classRepository;

  public ClassServiceImpl(ClassRepository classRepository) {
    this.classRepository = classRepository;
  }

  @Override
  public ResponseEntity<Class> save(ClassDTO aClass) {
    Class aClassNew = null;
    try {
      aClassNew = Class
              .builder()
              .className(aClass.className())
              .build();
    }catch (Exception ex){
      ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }
    return new ResponseEntity<>(classRepository.save(aClassNew),HttpStatus.CREATED);
  }

  @Override
  public List<Class> getAll() {
    return classRepository.findAll();
  }

  @Override
  public Class update(Long id, Class classUp) {
    if (id == null || classUp == null) {
      throw new IllegalArgumentException("ID e User não podem ser nulos");
    }
    Optional<Class> optionalClass = classRepository.findById(id);
    if(optionalClass.isPresent()) {
      Class aClass = classRepository.findById(id).get();
      aClass.setClassName(classUp.getClassName());
      aClass.setStudentList(classUp.getStudentList());
      return classRepository.saveAndFlush(aClass);
    }
    return optionalClass.orElse(null);
  }

  @Override
  public ResponseEntity<Void> delete(Long id) {
    try {
      classRepository.deleteById(id);
      return ResponseEntity.ok().build();
    } catch (NoSuchElementException e) {
      return ResponseEntity.notFound().build();
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }
  }


}
