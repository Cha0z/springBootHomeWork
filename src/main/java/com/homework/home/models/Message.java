package com.homework.home.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.ZonedDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Message {

    private String from;
    private String to;
    private String content;
    private ZonedDateTime dateTime;
}
