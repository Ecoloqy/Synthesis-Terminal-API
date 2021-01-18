package eu.ecct.synthesysterminal.config;

import eu.ecct.synthesysterminal.config.authorization.controller.ApiKeyFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {

    private final ApiKeyFilter customFilter;

    @Autowired
    public WebSecurityConfiguration(ApiKeyFilter customFilter) {
        this.customFilter = customFilter;
    }

    @Override
    protected void configure(HttpSecurity http) {
        http.addFilterAfter(customFilter, BasicAuthenticationFilter.class);
    }

}