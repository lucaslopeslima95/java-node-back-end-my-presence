package com.minhapresenca.minhapresencabackend.entity;

import com.minhapresenca.minhapresencabackend.entity.Student;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.context.annotation.Bean;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Class {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String className;
    @OneToMany(mappedBy = "aClass", cascade = CascadeType.REMOVE , fetch = FetchType.LAZY)
    private List <Student> studentList = new ArrayList<>();;
}
