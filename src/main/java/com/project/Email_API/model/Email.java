package com.project.Email_API.model;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class Email {
    private String from;
    private String [] to;
    private String subject;
    private String text;
    private MultipartFile[] attachments;
}
