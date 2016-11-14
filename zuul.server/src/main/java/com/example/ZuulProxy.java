package com.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@SpringBootApplication
@EnableZuulProxy
public class ZuulProxy extends WebSecurityConfigurerAdapter {

	public static void main(String[] args) {
		SpringApplication.run(ZuulProxy.class, args);
	}

		public void configure(HttpSecurity http) throws Exception {
		http
			.httpBasic()
				.and()
				.authorizeRequests()
				.antMatchers("/routes/**").permitAll()
				.antMatchers("/simpleservice1/**").hasRole("USER")
				.antMatchers("/simpleservice2/**").hasRole("ADMIN")
				.anyRequest().authenticated();
	}

	@Autowired
	protected void registerGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth
				.inMemoryAuthentication()
				.withUser("user").password("password").roles("USER").and()
				.withUser("admin").password("password").roles("ADMIN", "USER").and()
				.withUser("manager").password("password").roles("MANAGER","USER").and()
				.withUser("guest").password("password").roles("GUEST");

	}
}
