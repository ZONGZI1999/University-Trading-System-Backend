package com.zekizheng.trading;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
@MapperScan("com.zekizheng.trading.mapper")
public class UniversityTradingSystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(UniversityTradingSystemApplication.class, args);
    }

}
