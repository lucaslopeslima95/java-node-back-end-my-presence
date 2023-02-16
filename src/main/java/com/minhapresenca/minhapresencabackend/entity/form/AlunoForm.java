package com.minhapresenca.minhapresencabackend.entity.form;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AlunoForm {

//  @NotEmpty(message = "Preencha o campo corretamente.")
//  @Size(min = 3, max =50, message = "'${validatedValue}' precisa estar entre {min} e {max} caracteres.")
  private String nome;

  private String cpf;

  private String bairro;


  private LocalDate dataDeNascimento;
}
