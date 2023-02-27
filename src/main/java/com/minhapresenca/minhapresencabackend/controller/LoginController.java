package com.minhapresenca.minhapresencabackend.controller;


import com.minhapresenca.minhapresencabackend.DTO.LoginDTO;
import com.minhapresenca.minhapresencabackend.entity.Presence;
import com.minhapresenca.minhapresencabackend.entity.User;
import com.minhapresenca.minhapresencabackend.servicesImplementations.UserServiceImpl;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/login")
@CrossOrigin(origins = "*")
public class LoginController {
  private final UserServiceImpl userService;

  public LoginController(UserServiceImpl userService) {
    this.userService = userService;
  }


  @PostMapping
  public Boolean logIn(@RequestBody LoginDTO loginDTO) {
     Optional<Boolean> aBoolean = Optional.of(userService.findByEmailAndPassword(loginDTO.email(), loginDTO.password()));
      if (aBoolean.isPresent()){
        return true;
      }
      return false;
  }

}
