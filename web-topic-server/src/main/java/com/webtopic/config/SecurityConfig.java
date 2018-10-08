package com.webtopic.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;

import lombok.AllArgsConstructor;
import lombok.ToString;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	@ToString
	@AllArgsConstructor
	private enum Role{
		USER("USER"),
		ADMIN("ADMIN");		
		
		private final String role;	
	}
	
	private final int bCryptStrength = 12;
	
    @Bean
    public PasswordEncoder encoder() {
        return new BCryptPasswordEncoder(bCryptStrength);
    }	
	
	@Autowired
	private DataSource securityDataSource;

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.jdbcAuthentication()
        .passwordEncoder(encoder())
        .dataSource(securityDataSource);
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
        .antMatchers(HttpMethod.POST, "/api/**").hasRole(Role.ADMIN.toString())
		.antMatchers(HttpMethod.PUT, "/api/**").hasRole(Role.ADMIN.toString())
		.antMatchers(HttpMethod.DELETE, "/api/**").hasRole(Role.ADMIN.toString())
        .and()
		.httpBasic()
		.and()
		.csrf().disable()
		.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
	}
	
	@Bean
	public UserDetailsManager userDetailsManager() {
		JdbcUserDetailsManager jdbcUserDetailsManager = new JdbcUserDetailsManager();
		jdbcUserDetailsManager.setDataSource(securityDataSource);
		return jdbcUserDetailsManager;
	}

}
