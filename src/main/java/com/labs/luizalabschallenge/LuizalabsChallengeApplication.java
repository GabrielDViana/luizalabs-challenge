package com.labs.luizalabschallenge;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableJpaRepositories("com.labs.luizalabschallenge")
@EnableTransactionManagement
public class LuizalabsChallengeApplication {
    public static void main(String[] args) {
        SpringApplication.run(LuizalabsChallengeApplication.class, args);
    }

}
