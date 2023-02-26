package com.minhapresenca.minhapresencabackend.entity;

import com.minhapresenca.minhapresencabackend.entity.Student;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.context.annotation.Bean;

import javax.persistence.*;
import java.util.ArrayList;
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
    private Long id;
    private String className;
    @OneToMany(mappedBy = "aClass", cascade = CascadeType.REMOVE , fetch = FetchType.LAZY)
    private List <Student> studentList;

    @ManyToMany(mappedBy = "Class_Teacher")
    private Set<Teacher> teachers;
}
