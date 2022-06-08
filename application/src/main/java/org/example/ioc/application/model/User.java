package org.example.ioc.application.model;

import lombok.Data;
import lombok.Value;

@Value
public class User {
    private String login;
    private String pass;
    private String email;
    private String phone;
    private MessagingType messagingType;
}
