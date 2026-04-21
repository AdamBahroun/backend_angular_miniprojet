package com.adam.voiture.util;

public interface EmailSender {
    void sendEmail(String toEmail, String body);
}