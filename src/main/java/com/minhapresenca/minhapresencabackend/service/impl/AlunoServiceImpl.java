package com.minhapresenca.minhapresencabackend.service.impl;

import com.minhapresenca.minhapresencabackend.entity.Aluno;
import com.minhapresenca.minhapresencabackend.entity.form.AlunoForm;
import com.minhapresenca.minhapresencabackend.entity.form.AlunoUpdateForm;
import com.minhapresenca.minhapresencabackend.repository.AlunoRepository;
import com.minhapresenca.minhapresencabackend.service.AlunoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class AlunoServiceImpl implements AlunoService {

  @Autowired
  private AlunoRepository repository;

  @Override
  public Aluno create(AlunoForm form) {
    Aluno aluno = new Aluno();
    aluno.setNome(form.getNome());
    aluno.setCpf(form.getCpf());
    aluno.setBairro(form.getBairro());
    aluno.setDataDeNascimento(form.getDataDeNascimento());
    return repository.save(aluno);
  }

  @Override
  public Aluno get(Long id) {
    return null;
  }

  public List<Aluno> getAll() {

//    if(dataDeNascimento == null) {
//      return repository.findAll();
//    } else {
//      LocalDate localDate = LocalDate.parse(dataDeNascimento, JavaTimeUtils.LOCAL_DATE_FORMATTER);
//      return repository.findByDataDeNascimento(localDate);
//    }
    return null;
  }

  @Override
  public Aluno update(Long id, AlunoUpdateForm alunoUp) {
    Aluno aluno = repository.findById(id).get();
    aluno.setNome(alunoUp.getNome());
    aluno.setDataDeNascimento(alunoUp.getDataDeNascimento());
    aluno.setBairro(alunoUp.getBairro());
    return repository.saveAndFlush(aluno);
  }

  @Override
  public void delete(Long id) {
    repository.deleteById(id);
  }


}
