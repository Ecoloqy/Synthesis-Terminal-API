package eu.ecct.synthesysterminal.security.config;

import eu.ecct.synthesysterminal.security.CustomLoginFailureHandler;
import eu.ecct.synthesysterminal.security.AccountDetailsService;
import eu.ecct.synthesysterminal.security.config.SecurityBeansConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private final AccountDetailsService accountDetailsService;
    private final CustomLoginFailureHandler customLoginFailureHandler;
    private final SecurityBeansConfig beansConfig;

    @Autowired
    public WebSecurityConfig(AccountDetailsService accountDetailsService,
                             CustomLoginFailureHandler customLoginFailureHandler,
                             SecurityBeansConfig beansConfig) {
        this.accountDetailsService = accountDetailsService;
        this.customLoginFailureHandler = customLoginFailureHandler;
        this.beansConfig = beansConfig;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(accountDetailsService)
                .passwordEncoder(beansConfig.getPasswordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeRequests()
                .antMatchers("/dashboard", "/settings").authenticated()
                .antMatchers("/api/auth/**", "/api/arduino/**").permitAll()
                .antMatchers("/", "/about", "/login", "/registration", "/logout").permitAll()
                .antMatchers("/webjars/**", "/static/**", "/img/**", "/css/**", "/js/**").permitAll()
                .antMatchers().anonymous()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/login")
                .usernameParameter("username")
                .passwordParameter("password")
                .loginProcessingUrl("/authorize")
                .defaultSuccessUrl("/dashboard", true)
                .failureHandler(customLoginFailureHandler)
                .and()
                .logout()
                .logoutUrl("/logout")
                .logoutSuccessUrl("/")
                .deleteCookies("JSESSIONID")
                .and()
                .httpBasic();
    }

}
