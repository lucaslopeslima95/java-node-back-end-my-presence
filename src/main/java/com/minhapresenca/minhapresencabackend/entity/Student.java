package com.minhapresenca.minhapresencabackend.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
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
  private String name;

 // @Column(unique = true)
  private String cpf;

  private String neighborhood;

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
  private User user;
}
