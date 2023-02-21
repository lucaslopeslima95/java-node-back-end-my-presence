package com.minhapresenca.minhapresencabackend.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
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
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Student extends User{

  private String name;

  @Column(unique = true)
  private String cpf;

  private String neighborhood;

  private String address;

  @OneToMany(mappedBy = "student", cascade = CascadeType.REMOVE , fetch = FetchType.LAZY)
  @JsonIgnore
  private List<Presence> presences = new ArrayList<>();;

  @ManyToOne
  @JoinColumn(name = "class_id")
  private Class aClass;

}
