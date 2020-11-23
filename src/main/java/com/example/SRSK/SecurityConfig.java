package com.example.SRSK;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {


    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Autowired
    public SecurityConfig(UserDetailsServiceImpl userDetailsService){
        this.userDetailsService = userDetailsService;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception{
        auth.userDetailsService(userDetailsService);
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {

                /*
                http
                .authorizeRequests()
                .antMatchers("/", "/index", "/main")
                .hasRole("USER")
                .and().formLogin().loginPage("/login")
                .usernameParameter("username").passwordParameter("password")
                .defaultSuccessUrl("/main", true)
                .and().logout().permitAll().and()
                        .authorizeRequests().antMatchers("/h2console/**", "/register_success", "/register").permitAll();

                 */

                http.authorizeRequests()
                        .antMatchers("/","/main","/index").hasAuthority("ROLE_USER")
                        .and()
                        .formLogin().loginPage("/login")
                        .usernameParameter("email").passwordParameter("password")
                        .and()
                        .formLogin()
                        .defaultSuccessUrl("/main",true);



                /*
                http.authorizeRequests()
                        .antMatchers("/main","/").hasAuthority("ROLE_USER")
                        .and()
                        .formLogin().loginPage("/login")
                        .defaultSuccessUrl("/main", true);
*/
                /*
                http.authorizeRequests()
                        .and()
                        .formLogin().loginPage("/login")
                        .defaultSuccessUrl("/main", true);
*/


                http.authorizeRequests()
                .antMatchers("/login","/h2console/**", "/register_success", "/register").permitAll();
                http.csrf().disable();
                http.headers().frameOptions().disable();



    }



}
