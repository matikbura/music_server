package com.lj.music_server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
public class MusicServerApplication {
    public static void main(String[] args) {
        SpringApplication.run(MusicServerApplication.class, args);
    }

}
