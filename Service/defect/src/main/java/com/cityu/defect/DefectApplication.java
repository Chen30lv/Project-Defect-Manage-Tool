package com.cityu.defect;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.cityu.defect.mapper")
public class DefectApplication {

    public static void main(String[] args) {
        SpringApplication.run(DefectApplication.class, args);
    }

}
