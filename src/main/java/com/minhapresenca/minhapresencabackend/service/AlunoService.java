package com.minhapresenca.minhapresencabackend.service;


import com.minhapresenca.minhapresencabackend.entity.Aluno;
import com.minhapresenca.minhapresencabackend.entity.form.AlunoForm;
import com.minhapresenca.minhapresencabackend.entity.form.AlunoUpdateForm;

import java.util.List;

public interface AlunoService {

  Aluno create(AlunoForm form);

  Aluno get(Long id);

  List<Aluno> getAll();

  Aluno update(Long id, AlunoUpdateForm formUpdate);

  void delete(Long id);



}
