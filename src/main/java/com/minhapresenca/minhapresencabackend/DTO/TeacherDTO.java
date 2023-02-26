package com.minhapresenca.minhapresencabackend.DTO;

import lombok.Builder;

@Builder
public record TeacherDTO(String email, String password, String name, String subject){}
