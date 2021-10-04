package com.xxxx.skill;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.stereotype.Repository;

@SpringBootApplication
@MapperScan("com.xxxx.skill.mapper")
// @EnableAutoConfiguration(exclude = {DataSourceAutoConfiguration.class})
@Repository("*Mapper")
public class SkillDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(SkillDemoApplication.class, args);
    }

}
