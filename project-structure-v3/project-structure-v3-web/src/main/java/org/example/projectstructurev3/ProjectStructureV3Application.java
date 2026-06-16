package org.example.projectstructurev3;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("org.example.projectstructurev3.mapper")
@SpringBootApplication
public class ProjectStructureV3Application {

    public static void main(String[] args) {
        SpringApplication.run(ProjectStructureV3Application.class, args);
    }
}
