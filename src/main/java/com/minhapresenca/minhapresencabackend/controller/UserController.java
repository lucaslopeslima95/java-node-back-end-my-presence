package com.minhapresenca.minhapresencabackend.controller;


import com.minhapresenca.minhapresencabackend.DTO.UserDTO;
import com.minhapresenca.minhapresencabackend.entity.User;
import com.minhapresenca.minhapresencabackend.servicesImplementations.UserServiceImpl;
import org.springframework.http.ResponseEntity;
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

  @PostMapping
  public ResponseEntity<Boolean> logIn(@RequestBody UserDTO userDTO) {
     User user = userService.findByEmail(userDTO.email());
      if (BCrypt.checkpw(userDTO.password(), user.getPassword())){
        return ResponseEntity.ok().build();
      }
      return ResponseEntity.notFound().build();
  }

}
