package cn.thislx.springbootmultisource;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@MapperScan(basePackages = "cn.thislx.springbootmultisource.dao")
@EnableAutoConfiguration
public class SpringbootMultisourceApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootMultisourceApplication.class, args);
    }
}
