package com.josephcalver.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import com.josephcalver.model.dto.SearchResult;
import com.josephcalver.model.entity.Profile;
import com.josephcalver.model.entity.SiteUser;
import com.josephcalver.model.repository.ProfileDao;

@Service
public class ProfileService {

	@Value("${profiles.page.size}")
	private int pageSize;

	@Autowired
	ProfileDao profileDao;

	@PreAuthorize("isAuthenticated()")
	public void save(Profile profile) {

		profileDao.save(profile);
	}

	@PreAuthorize("isAuthenticated()")
	public Profile getUserProfile(SiteUser user) {

		return profileDao.findByUser(user);
	}

	@PreAuthorize("isAuthenticated()")
	public Page<SearchResult> findAllProfiles(int pageNumber) {

		PageRequest request = new PageRequest(pageNumber - 1, pageSize, Sort.Direction.ASC, "user.lastName");

		Page<Profile> results = profileDao.findAll(request);

		Converter<Profile, SearchResult> converter = new Converter<Profile, SearchResult>() {

			public SearchResult convert(Profile profile) {
				return new SearchResult(profile);
			}

		};

		return results.map(converter);
	}

}
