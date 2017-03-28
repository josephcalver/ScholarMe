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
						"/preview",
						"/register",
						"/verifyemail",
						"/confirmregister",
						"/registrationconfirmed",
						"/invaliduser",
						"/expiredtoken",
						"/loggedout"
						)
					.permitAll()
				.antMatchers(
						"/js/*",
						"/css/*",
						"/img/*"
						)
					.permitAll()
				.antMatchers(
						"/profile",
						"/profile/*",
						"/edit-profile",
						"/upload-profile-photo",
						"/profile-photo/*",
						"/save-interest",
						"/delete-interest",
						"/profiles",
						"/search"
						)
					.authenticated()
				.antMatchers(
						"/addstatus",
						"/editstatus",
						"/deletestatus",
						"/viewstatus"
							)
					.hasRole("ADMIN")
				.anyRequest()
					.denyAll()
				.and()
				.formLogin()
						.loginPage("/login")
						.defaultSuccessUrl("/")
						.permitAll()
						.and()
						.logout()
						.logoutSuccessUrl("/loggedout")
					.permitAll();

		// @formatter:on
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(siteUserService).passwordEncoder(passwordEncoder);
	}

}
