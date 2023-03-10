package com.minhapresenca.minhapresencabackend.servicesImplementations;

import com.minhapresenca.minhapresencabackend.View.PresenceView;
import com.minhapresenca.minhapresencabackend.entity.Presence;
import com.minhapresenca.minhapresencabackend.entity.Student;
import com.minhapresenca.minhapresencabackend.repository.PresenceRepository;
import com.minhapresenca.minhapresencabackend.repository.StudentRepository;
import com.minhapresenca.minhapresencabackend.service.PresenceService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

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
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
    String formattedDateTime = ZonedDateTime.now(ZoneId.of("America/Sao_Paulo")).format(formatter);

    Presence presence = Presence.builder()
            .student(studentRepository.findById(id).get())
            .date(formattedDateTime)
            .build();
    return presenceRepository.save(presence);
  }
  @Override
  public List <String> findByStudentId(long id){
    List <String> dates = presenceRepository.findDatesByStudentId(id);
    return dates;
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
