package com.main.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.main.service.SecurityUserService;

@Configuration
@EnableWebSecurity
public class CustomSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private SecurityUserService userService;

	@Autowired
	private PasswordEncoder passwordEncoder;

	String [] PUBLIC_END_POINTS = {"/auth/login","/auth/logout","/auth/refresh-token"};
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userService).passwordEncoder(passwordEncoder);
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
	//	super.configure(http);
		
		 http.cors().and().csrf().disable()
         .exceptionHandling()
         .and().httpBasic()
         .and()
         .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
         .and()
         .authorizeRequests()
         .antMatchers(HttpMethod.POST,PUBLIC_END_POINTS).permitAll()
         .antMatchers(HttpMethod.POST).hasRole("ADMIN")
    	 .antMatchers(HttpMethod.PUT).hasRole("ADMIN")
         .anyRequest().authenticated()
         .and()
         .addFilterBefore(authFilter(), UsernamePasswordAuthenticationFilter.class);
	}
	
	@Override
    @Bean
    protected AuthenticationManager authenticationManager() throws Exception {
        // TODO Auto-generated method stub
        return super.authenticationManager();
    }

    @Bean
    public AuthFilter authFilter() {
        return new AuthFilter();
    }

}
