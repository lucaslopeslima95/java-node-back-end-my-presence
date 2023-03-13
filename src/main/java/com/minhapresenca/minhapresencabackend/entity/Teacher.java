package com.minhapresenca.minhapresencabackend.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="teacher")
@Builder
public class Teacher{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id_teacher;
    @NotBlank(message = "Por Favor informe o nome do professor")
    private String name;
    @NotBlank(message = "Por Favor informe a materia do professor")
    private String subject;

    @ManyToOne
    @JoinColumn(name = "id_user")
    @JsonBackReference
    private User user;

    @ManyToMany
    @JoinTable(name = "class_teacher",
            joinColumns = @JoinColumn(name = "id_class"),
            inverseJoinColumns = @JoinColumn(name = "id_teacher"))
    @JsonBackReference
    @NotBlank(message = "Por Favor informe as turmas do professor")
    private Set<Class> classList;

}
