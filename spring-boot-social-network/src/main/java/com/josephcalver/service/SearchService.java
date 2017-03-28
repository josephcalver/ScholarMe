package com.josephcalver.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.josephcalver.model.dto.SearchResult;
import com.josephcalver.model.entity.Profile;
import com.josephcalver.model.repository.ProfileDao;

@Service
public class SearchService {

	@Value("${results.page.size}")
	private int pageSize;

	@Autowired
	private ProfileDao profileDao;

	public Page<SearchResult> search(String searchTerm, int pageNumber) {

		PageRequest request = new PageRequest(pageNumber - 1, pageSize, Sort.Direction.ASC, "user.lastName");

		Page<Profile> results = profileDao.findByInterestsNameContainingIgnoreCase(searchTerm, request);

		Converter<Profile, SearchResult> converter = new Converter<Profile, SearchResult>() {

			public SearchResult convert(Profile profile) {
				return new SearchResult(profile);
			}

		};

		return results.map(converter);
	}

}
