package com.fluffypets.services.impl;

import com.fluffypets.services.SendEmailService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

class SendEmailServiceImpl implements SendEmailService {
    private static final Logger logger = LogManager.getLogger(SendEmailServiceImpl.class.getName());


    private String myEmail;
    private Session session;

    public SendEmailServiceImpl(String username, String password) {

        myEmail = username;

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");


        this.session = Session.getInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });
    }

    @Override
    public boolean sendEmailTo(String toWho, String subject, String content) {
        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(myEmail));
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(toWho));
            message.setSubject(subject);
            message.setText(content);

            Transport.send(message);
            logger.info("letter to " + toWho + " was sent");
            return true;
        } catch (MessagingException e) {
            logger.error("letter to " + toWho + " was NOT sent " + e.getLocalizedMessage());
            return false;
        }
    }


}