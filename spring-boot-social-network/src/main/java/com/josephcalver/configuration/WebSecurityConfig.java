package com.josephcalver.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.josephcalver.service.SiteUserService;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	SiteUserService siteUserService;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	protected void configure(HttpSecurity http) throws Exception {

		// @formatter:off
		
		http.authorizeRequests()
				.antMatchers(
						"/",
						"/about",
						"/register",
						"/verifyemail",
						"/confirmregister",
						"/registrationconfirmed",
						"/invaliduser",
						"/expiredtoken",
						"/search"
						)
					.permitAll()
				.antMatchers(
						"/js/*",
						"/css/*",
						"/img/*"
						)
					.permitAll()
				.antMatchers(
						"/addstatus",
						"/editstatus",
						"/deletestatus",
						"/viewstatus"
						)
					.hasRole("ADMIN")
				.antMatchers(
						"/profile",
						"/profile/*",
						"/edit-profile",
						"/upload-profile-photo",
						"/profile-photo/*",
						"/save-interest",
						"/delete-interest"
						)
					.authenticated()
				.anyRequest()
					.denyAll()
					.and()
				.formLogin()
					.loginPage("/login")
					.defaultSuccessUrl("/")
					.permitAll()
					.and()
				.logout()
					.permitAll();

		// @formatter:on
	}

	// @Autowired
	// public void configureGlobal(AuthenticationManagerBuilder auth) throws
	// Exception {
	// auth.inMemoryAuthentication().withUser("joseph").password("password").roles("USER");
	// }

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(siteUserService).passwordEncoder(passwordEncoder);
	}

}
