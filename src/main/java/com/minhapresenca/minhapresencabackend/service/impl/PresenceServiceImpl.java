package com.minhapresenca.minhapresencabackend.service.impl;

import com.minhapresenca.minhapresencabackend.entity.Presence;
import com.minhapresenca.minhapresencabackend.entity.Student;
import com.minhapresenca.minhapresencabackend.repository.PresenceRepository;
import com.minhapresenca.minhapresencabackend.repository.StudentRepository;
import com.minhapresenca.minhapresencabackend.service.PresenceService;
import com.minhapresenca.minhapresencabackend.service.StudentService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class PresenceServiceImpl implements PresenceService {

  private PresenceRepository presenceRepository;
  private StudentRepository studentRepository;

  public PresenceServiceImpl(PresenceRepository presenceRepository, StudentRepository studentRepository) {
    this.presenceRepository = presenceRepository;
    this.studentRepository = studentRepository;
  }
  @Override
  public Presence create(Long id) {
    Presence presence = new Presence();
    presence.setStudent(studentRepository.findById(id).get());
    presence.setDate(formatData());
    return presenceRepository.save(presence);
  }

  @Override
  public List<Presence> getAll() {
    return presenceRepository.findAll();
  }

  @Override
  public void delete(Long id) {
      presenceRepository.deleteById(id);
  }

  @Override
  public Presence update(Long id, Presence presenceUp) {
        Presence presence  = presenceRepository.findById(id).get();
        presence.setStudent(presenceUp.getStudent());
        presence.setDate(presenceUp.getDate());
    return presenceRepository.saveAndFlush(presence);
  }
  public LocalDateTime formatData(){
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
    LocalDateTime dateUnformatted = LocalDateTime.now();
    String formattedDate = dateUnformatted.format(formatter);
    LocalDateTime parsedDate = LocalDateTime.parse(formattedDate, formatter);
    return parsedDate;
  }
}
