package com.projet.BackendPfe.config;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
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

import com.projet.BackendPfe.services.UserDetailsServiceImpl;
@Configuration
@EnableWebSecurity

@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	@Autowired
	UserDetailsServiceImpl userDetailsService;

	@Autowired
	private JwtAuthEntryPointJwt unauthorizedHandler;

	@Bean
	public JwtRequestFilter authenticationJwtTokenFilter() {
		return new JwtRequestFilter();
	}

	@Override
	public void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
		authenticationManagerBuilder.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
	}

	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.cors().and().csrf().disable()
		.exceptionHandling().authenticationEntryPoint(unauthorizedHandler).and()
			.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
			.authorizeRequests().antMatchers("/api/login").permitAll().antMatchers("/api/signupGeneraliste").permitAll().antMatchers("/api/signupExpert").permitAll().antMatchers("/api/signupAdmin").permitAll()
			.antMatchers("/expert/signup").permitAll()	.antMatchers("/expert/login").permitAll().antMatchers("/medecin/signup").permitAll().antMatchers("/adminDigital/signup").permitAll().antMatchers("/adminMedical/signup").permitAll()
			.antMatchers("/adminDigital/login").permitAll().antMatchers("/adminMedical/login").permitAll()
			.antMatchers("/api/test/**").permitAll().antMatchers("/api/addP").permitAll().antMatchers("/medecins/signup").permitAll().antMatchers("/login").permitAll().antMatchers("/api/getuser/{id}").permitAll()
			.antMatchers("/login").permitAll().antMatchers("/consultation/addimage1D/{idConsultation}").permitAll()
			.antMatchers("/login").permitAll().antMatchers("/consultation/addimage2D/{idConsultation}").permitAll()
			.antMatchers("/login").permitAll().antMatchers("/consultation/addimage3D/{idConsultation}").permitAll()
			.antMatchers("/login").permitAll().antMatchers("/consultation/addimage4D/{idConsultation}").permitAll()
			.antMatchers("/login").permitAll().antMatchers("/consultation/addimage5D/{idConsultation}").permitAll()
			.antMatchers("/login").permitAll().antMatchers("/consultation/image/{id}").permitAll()
			.anyRequest().authenticated();
		
		http.addFilterBefore(authenticationJwtTokenFilter(), UsernamePasswordAuthenticationFilter.class);
	}
}
