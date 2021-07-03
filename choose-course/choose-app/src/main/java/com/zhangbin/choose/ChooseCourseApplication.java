package com.zhangbin.choose;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@MapperScan("com.zhangbin.choose.mapper")
public class ChooseCourseApplication {

    public static void main(String[] args) {
        SpringApplication.run(ChooseCourseApplication.class, args);
    }

}
