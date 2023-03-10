package com.minhapresenca.minhapresencabackend.controller;


import com.minhapresenca.minhapresencabackend.DTO.UserDTO;
import com.minhapresenca.minhapresencabackend.servicesImplementations.UserServiceImpl;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/login")
@CrossOrigin(origins = "*")
public class UserController {
  private final UserServiceImpl userService;

  public UserController(UserServiceImpl userService) {
    this.userService = userService;
  }
//if (BCrypt.checkpw(candidate_password, stored_hash))

  @PostMapping
  public Boolean logIn(@RequestBody UserDTO userDTO) {
     Optional<Boolean> aBoolean = Optional.of(userService.findByEmailAndPassword(userDTO.email(), BCrypt.checkpw(candidate_password, stored_hash)));
      if (aBoolean.isPresent()){
        return true;
      }
      return false;
  }

}
