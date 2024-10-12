package io.github.reflectframework.unitbot;

import io.github.reflectframework.reflecttelegrambot.annotation.EnableBot;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableBot
@EnableFeignClients
@SpringBootApplication
public class UnitBotApplication {

    public static void main(String[] args) {
        SpringApplication.run(UnitBotApplication.class, args);
    }

}
