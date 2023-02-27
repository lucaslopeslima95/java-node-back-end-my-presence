package com.minhapresenca.minhapresencabackend.DTO;

import com.minhapresenca.minhapresencabackend.entity.Class;
import lombok.Builder;
import java.util.Set;

@Builder
public record TeacherDTO(String email, String password, String name, String subject, Set<Class> classList){}
