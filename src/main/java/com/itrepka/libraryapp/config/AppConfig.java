package com.itrepka.libraryapp.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class AppConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private MyUserDetailsService myUserDetailsService;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Autowired
    public void configureGlobalSecurity(AuthenticationManagerBuilder auth) {
        DaoAuthenticationProvider dap = new DaoAuthenticationProvider();
        dap.setUserDetailsService(myUserDetailsService);
        dap.setPasswordEncoder(passwordEncoder());

        auth.authenticationProvider(dap);
    }

    private String[] staticResources = {
            "/css/**",
            "/img/**",
            "/js/**",
            "/vendor/**",
            "/scss/**",
            "/my-css/**",
            "/font/**"
    };

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable() //tymczasowe wylaczenie zabezpeczenia przed csrf
        .authorizeRequests()
                .antMatchers("/admin/**").hasRole("ADMIN")
                .antMatchers(staticResources).permitAll()
                .antMatchers("/**").hasRole("ADMIN")
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/login")
                .usernameParameter("username")
                .passwordParameter("password")
                .permitAll()
                .and()
                .logout();
    }
}
