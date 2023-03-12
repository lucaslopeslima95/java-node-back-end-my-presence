package com.minhapresenca.minhapresencabackend.service;

import com.minhapresenca.minhapresencabackend.entity.User;

import java.util.List;


public interface UserService {

    User save(User user);

    List<User> getAll();

    User update(Long id,String email,String password);

    void delete(Long id);

    User findByEmail(String email);
}
