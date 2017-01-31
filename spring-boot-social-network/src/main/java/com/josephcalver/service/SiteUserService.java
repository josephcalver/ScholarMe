package com.josephcalver.service;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.josephcalver.model.SiteUser;
import com.josephcalver.model.SiteUserDao;
import com.josephcalver.model.VerificationDao;
import com.josephcalver.model.VerificationToken;
import com.josephcalver.model.VerificationTokenType;

@Service
public class SiteUserService implements UserDetailsService {

	@Autowired
	private SiteUserDao siteUserDao;

	@Autowired
	private VerificationDao verificationDao;

	public void register(SiteUser siteUser) {
		siteUser.setRole("ROLE_USER");
		siteUserDao.save(siteUser);
	}

	public void save(SiteUser siteUser) {
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

		Boolean enabled = siteUser.getEnabled();

		return new User(email, password, enabled, true, true, true, auth);
	}

	public String createEmailVerificationToken(SiteUser siteUser) {
		VerificationToken token = new VerificationToken(UUID.randomUUID().toString(), siteUser,
				VerificationTokenType.REGISTRATION);
		verificationDao.save(token);
		return token.getToken();
	}

	public VerificationToken getVerificationToken(String token) {
		return verificationDao.findByToken(token);
	}

	public SiteUser get(String email) {
		return siteUserDao.findByEmail(email);
	}

	public SiteUser get(Long id) {
		return siteUserDao.findOne(id);
	}

}
