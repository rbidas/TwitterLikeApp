package com.esencjal.twitter.like;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jms.annotation.EnableJms;

@SpringBootApplication
@EnableJms
public class TwitterApplication {

    public static void main(String[] args) {
        SpringApplication.run(TwitterApplication.class, args);
    }

}
