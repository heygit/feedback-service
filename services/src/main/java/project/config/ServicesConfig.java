package project.config;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;


@Configuration
@EnableAutoConfiguration
@ComponentScan({"project.service"})
@EntityScan("project")
//@EnableJpaRepositories(value = "project.repository") TODO
@PropertySource("classpath:services.properties")
//@EnableTransactionManagement TODO
public class ServicesConfig {

}