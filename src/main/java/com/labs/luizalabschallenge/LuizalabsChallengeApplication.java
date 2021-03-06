package com.labs.luizalabschallenge;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = {"com.labs.luizalabschallenge"})
@EnableJpaRepositories("com.labs.luizalabschallenge.repository")
@EntityScan("com.labs.luizalabschallenge.domain")
@MapperScan(basePackages = "com.labs.luizalabschallenge")
public class LuizalabsChallengeApplication {
    public static void main(String[] args) {
        SpringApplication.run(LuizalabsChallengeApplication.class, args);
    }

}
