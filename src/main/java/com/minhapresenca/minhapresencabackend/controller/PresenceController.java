package com.minhapresenca.minhapresencabackend.controller;

import com.minhapresenca.minhapresencabackend.entity.Presence;
import com.minhapresenca.minhapresencabackend.servicesImplementations.PresenceServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/presence")
@CrossOrigin(origins = "*")
public class PresenceController {
  private final PresenceServiceImpl presenceService;

  public PresenceController(PresenceServiceImpl presenceService) {
    this.presenceService = presenceService;
  }

  @PostMapping("/save-presence/{id}")
  public Presence save(@PathVariable Long  id) {
    return presenceService.save(id);
  }

  @GetMapping("/seach-presences-by-student-id/{id}")
  public List <String> getPresencesStudent(@PathVariable Long id){
    return presenceService.findByStudentId(id);
  }

  @DeleteMapping("/delete-presence/{id}")
  public ResponseEntity<Void> delete(@PathVariable Long id){
    return presenceService.delete(id);
  }
}
