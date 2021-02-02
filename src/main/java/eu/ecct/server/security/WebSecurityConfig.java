package eu.ecct.server.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.HttpStatusEntryPoint;

@EnableWebSecurity
@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private final String frontUrl;

    @Autowired
    public WebSecurityConfig(Environment environment) {
        this.frontUrl = environment.getProperty("server.address.frontend");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http.authorizeRequests()
                .antMatchers("/me").permitAll()
                .anyRequest().authenticated()
                .and()
                .logout()
                .logoutSuccessUrl(frontUrl)
                .logoutUrl("/logout")
                .logoutSuccessUrl(frontUrl)
                .deleteCookies("JSESSIONID")
                .and()
                .exceptionHandling(e -> e.authenticationEntryPoint(new HttpStatusEntryPoint(HttpStatus.UNAUTHORIZED)))
                .oauth2Login()
                .defaultSuccessUrl(frontUrl);

    }

}
