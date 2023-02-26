package com.minhapresenca.minhapresencabackend.DTO;

import lombok.Builder;

@Builder
public record StudentDTO(String email, String password, String name, String cpf, String neighborhood, String address, Long idClass){}
