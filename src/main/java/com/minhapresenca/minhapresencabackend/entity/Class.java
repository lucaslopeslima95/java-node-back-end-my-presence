package com.minhapresenca.minhapresencabackend.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "class")
@Builder
public class Class {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_class;
    private String className;
    @OneToMany(mappedBy = "aClass", cascade = CascadeType.REMOVE , fetch = FetchType.LAZY)
    private List <Student> studentList;

    @ManyToMany(mappedBy = "classList")
    private Set<Teacher> teacherList;
}
