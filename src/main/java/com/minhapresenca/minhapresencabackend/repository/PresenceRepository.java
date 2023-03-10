package com.minhapresenca.minhapresencabackend.repository;


import com.minhapresenca.minhapresencabackend.View.PresenceView;
import com.minhapresenca.minhapresencabackend.entity.Presence;
import com.minhapresenca.minhapresencabackend.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface PresenceRepository extends JpaRepository<Presence, Long> {
    @Query("select p.date from Presence p where p.student.id_Student= :id")
    List<String> findDatesByStudentId(long id);

}
