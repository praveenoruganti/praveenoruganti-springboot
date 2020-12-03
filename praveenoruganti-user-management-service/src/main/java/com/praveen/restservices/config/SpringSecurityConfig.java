package com.praveen.restservices.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter{
	
	@Autowired
	DataSource datasource;

	// Authentication based on role
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//		auth.inMemoryAuthentication()
//		       .withUser("praveen")
//		       .password("pcfadmin")
//		       .roles("ADMIN")
//			   .and()
//			   .withUser("prasad")
//			   .password("pcfuser")
//			   .roles("USER");
		
//		auth.jdbcAuthentication()
//		    .dataSource(datasource);	
		
	}
	
//	@Bean
//	public PasswordEncoder getPasswordEncoder() {
//		return NoOpPasswordEncoder.getInstance();
//	}

	// Authorization based on role
	@Override
	protected void configure(HttpSecurity http) throws Exception {
//		http.authorizeRequests()
//			   .antMatchers("/rest/hello/getMsg/admin").hasRole("ADMIN")
//			   .antMatchers("/rest/hello/getMsg/user").hasAnyRole("USER","ADMIN")
//		       .antMatchers("/").permitAll()		       
//		       .and().formLogin();
		http .csrf().disable() .authorizeRequests() .anyRequest().permitAll(); 
	}

}
