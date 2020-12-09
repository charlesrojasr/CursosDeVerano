package com.bytecode.core;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.bytecode.core.dao.JpaUserDetailsService;

@Configuration
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter{

	@Autowired
	private JpaUserDetailsService userDetails;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests().antMatchers("/", "/css/**", "/js/**", "/fonts/**", "/scss/**", "/vendor/**", "/images/**","/webfonts/**", "/principal/", "/api/**").permitAll()		
		//.antMatchers("/").permitAll()
		//.antMatchers("/index").permitAll()
		.antMatchers("/alumnos/listar/").hasAnyRole("ROLE_ADMIN")
		.antMatchers("/alumnos/formulario/").hasAnyRole("ROLE_ADMIN")
		.antMatchers("/cursos/listar/").hasAnyRole("ROLE_USER")
		.antMatchers("/cursos/form/").hasAnyRole("ROLE_USER")
		//.antMatchers("/cursos/", "/alumnos/").permitAll()	
		.anyRequest().authenticated()
		.and()
		.formLogin().loginPage("/").permitAll()
		.and()
		.logout().permitAll();
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		
		auth.userDetailsService(userDetails).passwordEncoder(passwordEncoder);
		/*
		PasswordEncoder encoder = passwordEncoder();
		
		UserBuilder users = User.builder().passwordEncoder(password ->{return encoder.encode(password);});
		
		auth.inMemoryAuthentication()
		.withUser(users.username("admin").password("123").roles("ADMIN", "USER"))
		.withUser(users.username("edith").password("123").roles("USER"));
	*/
	}

	
}
