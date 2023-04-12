package com.luka.r18;

import org.mybatis.spring.annotation.MapperScan;
import org.mybatis.spring.annotation.MapperScans;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
//@MapperScan("com.luka.r18.mappers")
@SpringBootApplication
public class R18Application {

    public static void main(String[] args)  {
        SpringApplication.run(R18Application.class, args);
    }

}

