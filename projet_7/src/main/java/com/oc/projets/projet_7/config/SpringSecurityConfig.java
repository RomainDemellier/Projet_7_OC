package com.oc.projets.projet_7.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.oc.projets.projet_7.jwt.JwtTokenProvider;
import com.oc.projets.projet_7.service.UsagerDetailsService;


@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;

	@Autowired
	private UsagerDetailsService usagerDetailsService;

	@Autowired
	private JwtRequestFilter jwtRequestFilter;

	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}


	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		// configure AuthenticationManager so that it knows from where to load
		// user for matching credentials
		// Use BCryptPasswordEncoder
		auth.userDetailsService(usagerDetailsService).passwordEncoder(passwordEncoder());
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		//        http.csrf().disable().authorizeRequests().antMatchers("/api/authenticated/**").authenticated().anyRequest().permitAll();

		http
		.csrf().disable()
		.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
		.and()
		.authorizeRequests()
		.antMatchers(HttpMethod.POST,"/authenticate").permitAll()
		.antMatchers(HttpMethod.POST,"/api/usager/create").permitAll()
//		.antMatchers(HttpMethod.PUT,"/api/emprunt/prolonger").authenticated()
		.antMatchers(HttpMethod.GET,"/api/hello").permitAll()
		.antMatchers(HttpMethod.POST,"/api/livre/create").permitAll()
		.antMatchers(HttpMethod.POST,"/api/auteur/create").permitAll()
//		.antMatchers(HttpMethod.GET,"/api/auteur/**").permitAll()
		//.antMatchers(HttpMethod.GET,"/api/auteur/{id}").permitAll()
		.antMatchers(HttpMethod.GET,"/api/authenticated/hello").authenticated()
		.antMatchers(HttpMethod.GET,"/api/admin/hello").hasAuthority("ADMIN")
//		.anyRequest().authenticated();
		.anyRequest().permitAll();
//		.and()
//		.exceptionHandling().authenticationEntryPoint(jwtAuthenticationEntryPoint).and().sessionManagement()
//		.sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		
		http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
		http.cors();
	}

	//    @Override
	//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
	//        /*ShaPasswordEncoder encoder = new ShaPasswordEncoder();
	//        auth.userDetailsService(customUserDetailsService).passwordEncoder(encoder);*/
	//        //auth.inMemoryAuthentication().withUser("user")
	//
	//        auth.userDetailsService(usagerDetailsSevice).passwordEncoder(new BCryptPasswordEncoder());
	//    }

	@Bean
	public BCryptPasswordEncoder encodePWD(){
		return new BCryptPasswordEncoder();
	}


	@Bean

	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
