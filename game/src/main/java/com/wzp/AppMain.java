package com.wzp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.mybatis.spring.annotation.MapperScan;

@SpringBootApplication

@MapperScan("com.wzp.*.dao")
public class AppMain {

    public static void main(String[] args) { SpringApplication.run(AppMain.class, args);
    }
}