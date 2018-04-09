package project.config;

import org.springframework.boot.SpringApplication;

import java.security.Security;

public class Runner {

    public static void main(String[] args) {
        Security.addProvider(new org.bouncycastle.jce.provider.BouncyCastleProvider());

        Object[] configs = {MainConfig.class, FilterConfig.class, CorsConfig.class};
        String[] argsArray = {};
        SpringApplication.run(configs, argsArray);
    }
}
