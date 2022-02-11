package com.project.Email_API.controllers;

import com.project.Email_API.model.Email;
import com.project.Email_API.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/email")
public class EmailController {

    @Autowired
    EmailService emailService;

    @PostMapping("/simpleEmail")
    public ResponseEntity sendSimpleEmail(@RequestBody Email email) {
        return emailService.sendEmail(email);
    }

    @PostMapping
    @RequestMapping(path = "/sendWithAttachment", consumes = { MediaType.MULTIPART_FORM_DATA_VALUE })
    public ResponseEntity sendEmailWithAttachment(@ModelAttribute Email email) {
       return   emailService.sendEmailWithAttachments(email);
    }
}
