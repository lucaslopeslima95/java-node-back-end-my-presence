package com.minhapresenca.minhapresencabackend.servicesImplementations;

import com.minhapresenca.minhapresencabackend.entity.Presence;
import com.minhapresenca.minhapresencabackend.repository.PresenceRepository;
import com.minhapresenca.minhapresencabackend.repository.StudentRepository;
import com.minhapresenca.minhapresencabackend.service.PresenceService;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;
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
    return presenceRepository.save(presence);
  }
  @Override
  public List<Presence> getAll() {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
    List<Presence> presences = presenceRepository.findAll();
    for (Presence presence : presences) {
      ZonedDateTime date = ZonedDateTime.parse(presence.getDate());
      if (date != null) {
        String formattedDate = date.format(formatter);
        presence.setDate(formattedDate);
      }
    }
    return presences;
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

}
