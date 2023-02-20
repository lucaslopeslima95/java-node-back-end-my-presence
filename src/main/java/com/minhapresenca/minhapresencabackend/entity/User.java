package com.minhapresenca.minhapresencabackend.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Data
@MappedSuperclass
@NoArgsConstructor
@AllArgsConstructor
public abstract class User {

    private String login;
    private String password;

}
