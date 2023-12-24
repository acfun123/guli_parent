package com.cc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * Hello world!
 *
 */

@SpringBootApplication
//配置扫描controller和swagger配置类
@ComponentScan({"com.ccbase", "com.cc"})
@EnableTransactionManagement
public class EduServiceApplication
{
    public static void main( String[] args )
    {
        SpringApplication.run(EduServiceApplication.class, args);
    }
}
