package com.minhapresenca.minhapresencabackend.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
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
    private String name;
    private String subject;

    @ManyToOne
    @JoinColumn(name = "id_user")
    private User user;

    @ManyToMany
    @JoinTable(name = "Class_Teacher",
            joinColumns = @JoinColumn(name = "student_id"),
            inverseJoinColumns = @JoinColumn(name = "subject_id"))
    private Set<Class> aClass;

}
