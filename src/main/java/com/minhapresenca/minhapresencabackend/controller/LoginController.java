package com.minhapresenca.minhapresencabackend.controller;


import com.minhapresenca.minhapresencabackend.entity.Presence;
import com.minhapresenca.minhapresencabackend.servicesImplementations.PresenceServiceImpl;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/presence")
@CrossOrigin(origins = "*")
public class LoginController {
  private final PresenceServiceImpl presenceService;

  public LoginController(PresenceServiceImpl presenceService) {
    this.presenceService = presenceService;
  }
  @PostMapping
  public Presence save(@RequestBody Long  id) {
    return presenceService.create(id);
  }

  @GetMapping
  public List<Presence> getAll(){
    return presenceService.getAll();
  }

  @DeleteMapping
  public String delete(@RequestBody Long id){
    presenceService.delete(id);
    return "Deletado com Sucesso";
  }

  @PutMapping(path ={"/{id}"})
  public Presence update(@RequestBody Presence presence, @PathVariable Long id) {
  return presenceService.update(id, presence);
  }
}
