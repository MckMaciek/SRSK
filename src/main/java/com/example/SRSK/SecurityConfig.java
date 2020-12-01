package com.example.SRSK;


import com.example.SRSK.services.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.logout.HttpStatusReturningLogoutSuccessHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

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

    @Bean
    public CustomLogoutHandler customLogoutHandler() {
        return new CustomLogoutHandler();
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
                        .antMatchers("/","/main","/index","/about","/getCode","/getImage").hasAuthority("ROLE_USER")
                        .and()
                        .formLogin().loginPage("/login")
                        .usernameParameter("email").passwordParameter("password")
                        .and()
                        .formLogin()
                        .defaultSuccessUrl("/main",true)
                        .and()
                        .logout()
                        .logoutUrl("/logout")
                        .addLogoutHandler(customLogoutHandler())
                        .logoutRequestMatcher(new AntPathRequestMatcher("/logout"));

                //http.logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout"));


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
