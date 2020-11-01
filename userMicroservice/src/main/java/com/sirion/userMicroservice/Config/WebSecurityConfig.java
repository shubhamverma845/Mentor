package com.sirion.userMicroservice.Config;


import com.sirion.userMicroservice.Filters.JwtRequestFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {


    @Autowired
    UserDetailsService userDetailsService;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    JwtRequestFilter jwtRequestFilter;

    //restricts URLs--Authorization
    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {

        //Form Login
//        httpSecurity.authorizeRequests()
//                .antMatchers("/user/signUp").permitAll()
//                .antMatchers("/user/*").hasAnyAuthority("LEARNER","MENTOR")
//        .and().formLogin();

        //Permit all
        httpSecurity.cors().and().csrf().disable()
                .authorizeRequests().antMatchers("/login", "/signUp").permitAll()
                .anyRequest().authenticated()
                .and().sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        httpSecurity.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);

        //Basic Auth
//        httpSecurity.httpBasic().and().authorizeRequests()
//                .antMatchers("/token/*", "/user/signUp").permitAll()
//                .anyRequest().authenticated();
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



    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception{
        return super.authenticationManagerBean();
    }
}
