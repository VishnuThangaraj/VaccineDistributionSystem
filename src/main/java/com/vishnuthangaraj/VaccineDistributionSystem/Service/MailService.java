package com.vishnuthangaraj.VaccineDistributionSystem.Service;

import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class MailService {

    @Autowired
    JavaMailSender javaMailSender;

    // Generate Mail
    public void generateMail(String receiver, String subject, String mailBody){

        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper =new MimeMessageHelper(mimeMessage, "UTF-8");

        try{
            mimeMessageHelper.setTo(receiver);
            mimeMessageHelper.setSubject(subject);
            mimeMessageHelper.setText(mailBody);
            javaMailSender.send(mimeMessage); // Send Mail
        }
        catch(Exception exception){
            System.out.println(exception);
        }
    }
}
