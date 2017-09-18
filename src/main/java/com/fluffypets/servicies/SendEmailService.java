package com.fluffypets.servicies;

public interface SendEmailService {
    boolean sendEmailTo(String toWho,String subject,String content);
}
