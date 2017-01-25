package com.josephcalver.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.josephcalver.model.SiteUser;
import com.josephcalver.model.SiteUserDao;

@Service
public class SiteUserService implements UserDetailsService {

	@Autowired
	private SiteUserDao siteUserDao;

	public void register(SiteUser siteUser) {
		siteUser.setRole("ROLE_USER");
		siteUserDao.save(siteUser);
	}

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		SiteUser siteUser = siteUserDao.findByEmail(email);

		if (siteUser == null) {
			return null;
		}

		List<GrantedAuthority> auth = AuthorityUtils.commaSeparatedStringToAuthorityList(siteUser.getRole());

		String password = siteUser.getPassword();

		return new User(email, password, auth);
	}

}
