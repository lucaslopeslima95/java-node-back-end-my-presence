package com.minhapresenca.minhapresencabackend.service;

import com.minhapresenca.minhapresencabackend.entity.Class;
import com.minhapresenca.minhapresencabackend.entity.form.ClassForm;

import java.util.List;

public interface ClassService {

  Class create(ClassForm Class);

  List<Class> getAll();

  Class update(Long id, Class studentClass);

  void delete(Long id);
}
