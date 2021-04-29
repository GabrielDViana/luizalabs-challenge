package com.labs.luizalabschallenge;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableJpaRepositories("com.labs.luizalabschallenge")
@EnableTransactionManagement
public class LuizalabsChallengeApplication {
    public static final String ACCOUNT_SID = "AC1c009f66925e669230b8948a4912ab39";
    public static final String AUTH_TOKEN = "0b6baab67fba6ef3f9b3a616a94e7652";
    public static void main(String[] args) {
        SpringApplication.run(LuizalabsChallengeApplication.class, args);
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
        Message message = Message.creator(
                new com.twilio.type.PhoneNumber("whatsapp:+556196302228"),
                new com.twilio.type.PhoneNumber("whatsapp:+14155238886"),
                "Your Yummy Cupcakes Company order of 1 dozen frosted cupcakes has shipped and should be delivered on July 10.")
                .create();

        System.out.println(message.getSid());
    }

}
