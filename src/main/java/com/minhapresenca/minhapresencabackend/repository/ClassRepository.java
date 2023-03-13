package com.minhapresenca.minhapresencabackend.repository;


import com.minhapresenca.minhapresencabackend.entity.Class;
import com.minhapresenca.minhapresencabackend.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClassRepository extends JpaRepository<Class, Long> {
    @Query("select c.className from Class c JOIN  c.teacherList t where t.id_teacher = :id")
    List<Class> listClassByTeacherId(Long id);
}
