package com.fluffypets.servicies.email;

public interface SendEmailService {
    boolean sendEmailTo(String toWho,String subject,String content);
}
