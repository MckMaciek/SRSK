package com.example.SRSK;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean
    public UserDetailsService userDetailsService(){

        UserDetails userDetails = User.withDefaultPasswordEncoder()
                .username("user")
                .password("user1")
                .roles("USER")
                .build();

        return new InMemoryUserDetailsManager(userDetails);
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {

                http
                .authorizeRequests()
                .antMatchers("/", "/index.html")
                .hasRole("USER")
                .and().formLogin().loginPage("/login.html")
                .usernameParameter("username").passwordParameter("password")
                .defaultSuccessUrl("/index.html", true)
                .and().logout().permitAll().and()
                        .authorizeRequests().antMatchers("/h2console/**").permitAll();

                http.authorizeRequests()
                .antMatchers("/").permitAll()
                .antMatchers("/h2console/**").permitAll();
                http.csrf().disable();
                http.headers().frameOptions().disable();

    }


}
