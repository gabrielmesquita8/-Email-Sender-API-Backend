package com.project.Email_API.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
public class Exception {
    private Date timestamp;
    private int status;
    private List<String> message;
}
