package com.example.demo.security;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
public class FoodRecipeSecurity extends WebSecurityConfigurerAdapter {

	

	 
	/**
	 * 
	Swagger configured 
	Input parameter http
	
	 */
	protected void configure(final HttpSecurity http) throws Exception {
		http.csrf().disable().authorizeRequests()
        .antMatchers("/swagger-ui/**", "/FoodRecipeDemo/**").permitAll()
        .anyRequest().authenticated()
        .and()
        .httpBasic();
	}
	
	/**
	 Input as Authentication details
	 */
	@Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception { auth.inMemoryAuthentication()
                .withUser("user")
                .password(passwordEncoder().encode("user"))
                .authorities("ADMIN");
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
	
}
