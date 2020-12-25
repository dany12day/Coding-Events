package com.GhereDaniel.CodingEvents.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private DataSource dataSource;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .jdbcAuthentication()
                .dataSource(dataSource)
                .passwordEncoder(passwordEncoder())
                .usersByUsernameQuery(
                        "SELECT username, password, enabled from users where username = ?")
                .authoritiesByUsernameQuery(
                        "SELECT u.username, a.name " +
                                "FROM user_type a, users u " +
                                "WHERE u.username = ? " +
                                "AND u.user_type_id = a.id"
                );
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }



    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.formLogin().and().csrf().disable();

        http.authorizeRequests()
                .antMatchers("/admin","/admin/view","/admin/delete").hasAuthority("Admin")
                .antMatchers("/events/create", "/events/delete", "/eventCategories/create", "/eventTag/create").hasAnyAuthority("RegularUser","Admin")
                .antMatchers("/","/home", "/login", "/events/view", "/eventCategories/view", "/eventTag/view", "/register").permitAll();
    }

}