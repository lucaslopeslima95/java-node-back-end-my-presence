package com.minhapresenca.minhapresencabackend.entity.form;


import lombok.Data;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
public class StudentForm {

    private String name;

    @Column(unique = true)
    private String cpf;

    private String neighborhood;

    private String address;

//  @NotEmpty(message = "Preencha o campo corretamente.")
//  @Size(min = 3, max =50, message = "'${validatedValue}' precisa estar entre {min} e {max} caracteres.")
}
