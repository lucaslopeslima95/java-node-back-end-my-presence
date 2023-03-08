package com.minhapresenca.minhapresencabackend.repository;


import com.minhapresenca.minhapresencabackend.entity.Class;
import com.minhapresenca.minhapresencabackend.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClassRepository extends JpaRepository<Class, Long> {}
