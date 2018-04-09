package project.config;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import project.filter.SessionIdHandlerLog4jFilter;

import static project.constants.ParamValues.ALL_URLS_PATTERN;

@Configuration
public class FilterConfig {

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
