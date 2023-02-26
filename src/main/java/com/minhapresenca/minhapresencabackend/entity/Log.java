package com.minhapresenca.minhapresencabackend.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.ZonedDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Log {

    private String nameStudent;
    private String operation;
    private String whoDo;
    private String createdAt;
}
