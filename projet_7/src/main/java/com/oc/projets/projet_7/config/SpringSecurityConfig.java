package com.oc.projets.projet_7.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.oc.projets.projet_7.service.UsagerDetailsSevice;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	UsagerDetailsSevice usagerDetailsSevice;
	
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable().authorizeRequests().antMatchers("/api/authenticated/**").authenticated().anyRequest().permitAll();
        //http.authorizeRequests().antMatchers("**/css/**", "**/js/**", "**/images/**").permitAll();
        //http.authorizeRequests().antMatchers("/login","/webjars/**", "/css/**", "/images/**","/js/**").permitAll();
    }
    
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        /*ShaPasswordEncoder encoder = new ShaPasswordEncoder();
        auth.userDetailsService(customUserDetailsService).passwordEncoder(encoder);*/
        //auth.inMemoryAuthentication().withUser("user")

        auth.userDetailsService(usagerDetailsSevice).passwordEncoder(new BCryptPasswordEncoder());
    }
    
    @Bean
    public BCryptPasswordEncoder encodePWD(){
        return new BCryptPasswordEncoder();
    }
}
