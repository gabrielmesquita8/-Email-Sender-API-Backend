package com.project.Email_API.service;

import com.project.Email_API.model.Email;
import com.project.Email_API.model.User;
import com.project.Email_API.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.io.IOException;
import java.util.Optional;
import java.util.Properties;

@Service
public class EmailService {

    @Autowired
    UserRepository repository;

    public Session emailServiceConfiguration(Email email) {
        Properties properties = new Properties();

        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");
        properties.put("mail.mime.address.strict", "false");

        Optional<User> userInformation = repository.findByUsername(email.getFrom());

        Session session = Session.getDefaultInstance(properties, new Authenticator()
        {
            protected PasswordAuthentication getPasswordAuthentication(){

                return new PasswordAuthentication(userInformation.get().getUsername(),
                        userInformation.get().getPassword());
            }
        });
        return session;
    }

    public ResponseEntity sendEmail(Email email)
    {
        try {

            MimeMessage msg = new MimeMessage(emailServiceConfiguration(email));

            msg.setFrom(new InternetAddress(email.getFrom()));

            for(String to : email.getTo()) {
                msg.addRecipient(Message.RecipientType.TO, new InternetAddress((to)));
            }

            msg.setSubject(email.getSubject());
            msg.setText(email.getText());

            Transport.send(msg);

        }catch(Exception e){

            System.out.println("EmailService File Error"+ e);
        }

        return new ResponseEntity(email, HttpStatus.OK);
    }

    public ResponseEntity sendEmailWithAttachments(Email email) {

        try {

            MimeMessage msg = new MimeMessage(emailServiceConfiguration(email));

            msg.setFrom(new InternetAddress(email.getFrom()));

            for(String to : email.getTo()) {
                msg.addRecipient(Message.RecipientType.TO, new InternetAddress((to)));
            }

            msg.setSubject(email.getSubject());
            msg.setText(email.getText());

            Multipart multipart = new MimeMultipart();

            if(email.getAttachments() != null && email.getAttachments().length > 0){
                for (MultipartFile filePath : email.getAttachments()) {
                    MimeBodyPart attachPart = new MimeBodyPart();
                    try {
                        attachPart.setContent(filePath.getBytes(), filePath.getContentType());
                        attachPart.setFileName(filePath.getOriginalFilename());
                        attachPart.setDisposition(Part.ATTACHMENT);
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                    multipart.addBodyPart(attachPart);
                }
            }

            msg.setContent(multipart);

            Transport.send(msg);

        } catch(Exception e){
            System.out.println("EmailService File Error"+ e);
        }

        return new ResponseEntity(email, HttpStatus.OK);
    }
}
