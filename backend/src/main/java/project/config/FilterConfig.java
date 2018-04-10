package project.config;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import project.filter.SessionIdHandlerLog4jFilter;

@Configuration
public class FilterConfig {

    private static final String ALL_URLS_PATTERN = "/*";

    @Bean
    public FilterRegistrationBean registerSessionIdHandlerLog4jFilter() {
        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setFilter(new SessionIdHandlerLog4jFilter());
        registration.addUrlPatterns(ALL_URLS_PATTERN);
        registration.setName(SessionIdHandlerLog4jFilter.class.getSimpleName());
        registration.setOrder(2);
        return registration;
    }

}
