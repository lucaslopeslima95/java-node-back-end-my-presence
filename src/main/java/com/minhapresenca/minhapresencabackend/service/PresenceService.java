package com.minhapresenca.minhapresencabackend.service;


import com.minhapresenca.minhapresencabackend.entity.Presence;
import com.minhapresenca.minhapresencabackend.entity.Student;

import java.util.List;

public interface PresenceService {

  Presence create(Presence presence);

  List<Presence> getAll();

  void delete(Long id);

  Presence update(Long id,Presence presence);
}
