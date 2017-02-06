package com.josephcalver.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import com.josephcalver.model.entity.Profile;
import com.josephcalver.model.entity.SiteUser;
import com.josephcalver.model.repository.ProfileDao;

@Service
public class ProfileService {

	@Autowired
	ProfileDao profileDao;

	// @PreAuthorize("isAuthenticated()")
	public void save(Profile profile) {
		profileDao.save(profile);
	}

	// @PreAuthorize("isAuthenticated()")
	public Profile getUserProfile(SiteUser user) {
		return profileDao.findByUser(user);
	}

}
