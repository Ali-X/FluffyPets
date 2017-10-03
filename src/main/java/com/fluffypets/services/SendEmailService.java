package com.fluffypets.services;

public interface SendEmailService {
    boolean sendEmailTo(String toWho,String subject,String content);
}
