package com.praveen.security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class ApplicationSecurityConfig extends WebSecurityConfigurerAdapter {
	
	private final PasswordEncoder passwordEncoder;
	
	@Autowired
	public ApplicationSecurityConfig(PasswordEncoder passwordEncoder) {
		this.passwordEncoder = passwordEncoder;
	}

	 // Authentication based on role
	 @Override
	 protected void configure(AuthenticationManagerBuilder auth) throws Exception {
	  auth.inMemoryAuthentication()
	         .withUser("praveen")
	         .password(passwordEncoder.encode("password123"))
	         .roles("ADMIN")
	      .and()
	      .withUser("prasad")
	      .password(passwordEncoder.encode("password"))
	      .roles("USER");
	 }
	 
	
	 
	// Authorization based on role
	 @Override
	 protected void configure(HttpSecurity http) throws Exception {
	  http.authorizeRequests()
	      .antMatchers("/api/v1/getMsg/admin").hasRole("ADMIN")
	      .antMatchers("/api/v1/getMsg/user").hasAnyRole("USER","ADMIN")
	         .antMatchers("/").permitAll()         
	         .and().formLogin();
	 }

}
