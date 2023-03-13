package com.minhapresenca.minhapresencabackend.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.br.CPF;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "student")
@Builder
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Student{

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id_Student;
  @NotBlank(message = "Por Favor informe o nome do aluno")
  private String name;
  @CPF(message = "CPF Inválido")
  @Column(unique = true)
  private String cpf;

  @NotBlank(message = "Por Favor informe o bairro do aluno")
  private String neighborhood;
  @NotBlank(message = "Por Favor informe o endereço do aluno")
  private String address;

  @OneToMany(mappedBy = "student", cascade = CascadeType.REMOVE , fetch = FetchType.LAZY)
  @JsonIgnore
  private List<Presence> presences;

  @ManyToOne
  @JoinColumn(name = "class_id")
  @JsonBackReference
  private Class aClass;

  @OneToOne
  @JoinColumn(name = "user_id")
  @JsonBackReference
  private User user;
}
