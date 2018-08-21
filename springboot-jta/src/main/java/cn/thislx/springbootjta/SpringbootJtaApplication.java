package cn.thislx.springbootjta;

import cn.thislx.springbootjta.config.datasource.DBPriMaryConfig;
import cn.thislx.springbootjta.config.datasource.DBSecondConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(value = {DBPriMaryConfig.class, DBSecondConfig.class})
public class SpringbootJtaApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootJtaApplication.class, args);
    }
}
