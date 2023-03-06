package com.minhapresenca.minhapresencabackend.service;


import com.minhapresenca.minhapresencabackend.View.PresenceView;
import com.minhapresenca.minhapresencabackend.entity.Presence;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface PresenceService {

  Presence save(Long id);

  PresenceView findByStudentId(long id);

  ResponseEntity<Void> delete(Long id);


}
