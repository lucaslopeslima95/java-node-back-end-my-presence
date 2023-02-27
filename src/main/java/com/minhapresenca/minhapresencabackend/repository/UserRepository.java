package com.minhapresenca.minhapresencabackend.repository;


import com.minhapresenca.minhapresencabackend.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    boolean findByEmailAndPassword(String email,String password);
}
