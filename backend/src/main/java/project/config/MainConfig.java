package project.config;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;


@Configuration
@EnableAutoConfiguration
@ComponentScan({"project.controller", "project.service", "project.converter"})
@PropertySource("classpath:application.properties")
//@EnableTransactionManagement TODO
public class MainConfig {

}