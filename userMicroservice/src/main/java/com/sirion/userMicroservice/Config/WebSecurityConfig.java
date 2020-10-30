package com.sirion.userMicroservice.Config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;


@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {


    @Autowired
    UserDetailsService userDetailsService;

    @Autowired
    PasswordEncoder encoder;

    //restricts URLs--Authorization
    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {

        //Form Login
//        httpSecurity.authorizeRequests()
//                .antMatchers("/user/signUp").permitAll()
//                .antMatchers("/user/*").hasAnyAuthority("LEARNER","MENTOR")
//        .and().formLogin();


        //Basic Auth
        httpSecurity.httpBasic().and().authorizeRequests()
                .antMatchers("/user/*").hasAnyAuthority("LEARNER","MENTOR");
    }


    //ignores URLs
    @Override
    public void configure(WebSecurity web) throws Exception {
        super.configure(web);
        web.ignoring().antMatchers("/user/signUp");
    }


    //set roles--Authentication
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(encoder);
    }
}
