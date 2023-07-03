package com.kimmich;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author gaopeng
 * @time 2020/10/23 23:09:46
 */
@SpringBootApplication
@MapperScan(basePackages = "com.kimmich.mapper")
public class App {
    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }
}
