package com.app.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class Myconfig {

	
	
	@Bean
	public UserDetailsService users()
	{
	return new UserDetailsServiceImpl();
	}

@Bean
public BCryptPasswordEncoder  passwordencoder()
{
	return new BCryptPasswordEncoder();
}

@Bean
public DaoAuthenticationProvider  authenticationprovider()
{
	
	DaoAuthenticationProvider  authprovider=new DaoAuthenticationProvider();
	authprovider.setUserDetailsService(this.users());
	authprovider.setPasswordEncoder(passwordencoder());
	return authprovider;
}

@Bean
public SecurityFilterChain securityfilterchain(HttpSecurity http)throws Exception {
	http.authorizeRequests()
	.antMatchers("/admin/**").hasRole("/ADMIN")
	.antMatchers("/user/**").hasRole("USER")
	.antMatchers("/**").permitAll().and().formLogin().loginPage("/signin").and()
			.csrf().disable()
	
	.httpBasic();
	http.authenticationProvider(authenticationprovider());
	return http.build();
}

//configurer method




//@Bean
//protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//	auth.authenticationProvider(authenticationprovider());// from database
//	
//}

@Bean
public AuthenticationManager authenticatemanager(AuthenticationConfiguration configuration)throws Exception
{
	return configuration.getAuthenticationManager();
}



// kon kon access kr paega aur kya
//@Override
//protected void configure(HttpSecurity http) throws Exception {
//	http.authorizeRequests().antMatchers("/admin/**").hasRole("/ADMIN")
//	.antMatchers("/user/**").hasRole("USER")
//	.antMatchers("/**").permitAll().and().formLogin().loginPage("/signin").and().csrf().disable();
//}
}
