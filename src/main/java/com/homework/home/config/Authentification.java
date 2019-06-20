package com.homework.home.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties("auth")
public class Authentification {

    private String userName;
    private String password;

}
