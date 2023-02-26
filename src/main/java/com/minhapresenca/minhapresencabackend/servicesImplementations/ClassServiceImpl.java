package com.minhapresenca.minhapresencabackend.servicesImplementations;


import com.minhapresenca.minhapresencabackend.entity.Class;
import com.minhapresenca.minhapresencabackend.DTO.ClassDTO;
import com.minhapresenca.minhapresencabackend.repository.ClassRepository;
import com.minhapresenca.minhapresencabackend.service.ClassService;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ClassServiceImpl implements ClassService {

  private ClassRepository classRepository;

  public ClassServiceImpl(ClassRepository classRepository) {
    this.classRepository = classRepository;
  }

  @Override
  public Class create(ClassDTO aClass) {
    Class aClassNew = new Class();
    aClassNew.setClassName(aClass.getClassName());
    return classRepository.save(aClassNew);
  }

  @Override
  public List<Class> getAll() {
    return classRepository.findAll();
  }

  @Override
  public Class update(Long id, Class aClassUp) {
    Class aClass = classRepository.findById(id).get();
    aClass.setClassName(aClassUp.getClassName());
    aClass.setStudentList(aClassUp.getStudentList());
    return classRepository.saveAndFlush(aClass);
  }

  @Override
  public void delete(Long id) {
      classRepository.deleteById(id);
  }


}
