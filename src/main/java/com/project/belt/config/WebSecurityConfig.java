package com.project.belt.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{
	private UserDetailsService userDetailsService;
	
	public WebSecurityConfig(UserDetailsService userDetailsService){
		this.userDetailsService = userDetailsService;
	}
	
	@Bean
	// Creates + returns a bcrypt instance
	public BCryptPasswordEncoder bCrypt(){
		return new BCryptPasswordEncoder();
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.
			authorizeRequests()
				// static folders and "/register" permitted to all
				.antMatchers("/static/**", "/register").permitAll()
				.antMatchers("/admin/**").access("hasRole('ADMIN')")
				.anyRequest().authenticated()
				.and()
			.formLogin() // for form-based authentication
				.loginPage("/") // URL for login page
				.defaultSuccessUrl("/profile") // URL after login
				.permitAll()
				.and()
			.logout() // by default, logout is /logout
				.permitAll();
	}

	@Autowired
	// Tells original details service to use bcrypt for handling login.
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception{
		auth.userDetailsService(userDetailsService).passwordEncoder(bCrypt());
	}
}
