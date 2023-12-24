package com.cc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;

/**
 * Hello world!
 *
 */

@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
@ComponentScan({"com.cc", "com.ccbase"})
@EnableConfigurationProperties
@ConfigurationPropertiesScan("com.cc")
public class OssApplication
{
    public static void main( String[] args )
    {
        SpringApplication.run(OssApplication.class, args);
    }
}
