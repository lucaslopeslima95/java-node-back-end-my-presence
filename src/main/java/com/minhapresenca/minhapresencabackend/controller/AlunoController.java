package com.minhapresenca.minhapresencabackend.controller;


import com.minhapresenca.minhapresencabackend.entity.Aluno;
import com.minhapresenca.minhapresencabackend.entity.form.AlunoForm;
import com.minhapresenca.minhapresencabackend.entity.form.AlunoUpdateForm;
import com.minhapresenca.minhapresencabackend.service.impl.AlunoServiceImpl;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/alunos")
public class AlunoController {


  private final AlunoServiceImpl alunoService;

  public AlunoController(AlunoServiceImpl alunoService) {
    this.alunoService = alunoService;
  }

  @PostMapping
  public Aluno save(@RequestBody AlunoForm form) {
    return alunoService.create(form);
  }

  @GetMapping
  public List<Aluno> getAll(){
    return alunoService.getAll();
  }

  @DeleteMapping(path ={"/{id}"})
  public String delete(@PathVariable Long id){
    alunoService.delete(id);
    return "Deletado com Sucesso";
  }

  @PutMapping(path ={"/{id}"})
  public Aluno update( @RequestBody AlunoUpdateForm form, @PathVariable Long id) {
  return alunoService.update(id, form);
  }


}
