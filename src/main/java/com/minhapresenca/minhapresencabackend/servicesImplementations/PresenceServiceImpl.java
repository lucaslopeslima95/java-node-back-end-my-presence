package com.minhapresenca.minhapresencabackend.servicesImplementations;

import com.minhapresenca.minhapresencabackend.View.PresenceView;
import com.minhapresenca.minhapresencabackend.entity.Presence;
import com.minhapresenca.minhapresencabackend.repository.PresenceRepository;
import com.minhapresenca.minhapresencabackend.repository.StudentRepository;
import com.minhapresenca.minhapresencabackend.service.PresenceService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class PresenceServiceImpl implements PresenceService {

  private PresenceRepository presenceRepository;
  private StudentRepository studentRepository;

  public PresenceServiceImpl(PresenceRepository presenceRepository, StudentRepository studentRepository) {
    this.presenceRepository = presenceRepository;
    this.studentRepository = studentRepository;
  }
  @Override
  public Presence save(Long id) {
    Presence presence = Presence.builder()
            .student(studentRepository.findById(id).get())
            .date(String.valueOf(ZonedDateTime.now()))
            .build();
    return presenceRepository.save(presence);
  }
  @Override
  public List<Presence> findByStudentId(long id){
    List<Presence> presences = presenceRepository.findByStudentId(id);
    for (Presence presence : presences) {
      ZonedDateTime date = ZonedDateTime.parse(presence.getDate());
      if (date != null) {
        String formattedDate = date.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss"));
        presence.setDate(formattedDate);
      }
    }
    return presences;
  }

  @Override
  public ResponseEntity<Void> delete(Long id) {
    try {
      presenceRepository.deleteById(id);
      return ResponseEntity.ok().build();
    } catch (NoSuchElementException e) {
      return ResponseEntity.notFound().build();
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }
  }



}
