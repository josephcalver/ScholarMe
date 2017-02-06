package com.josephcalver.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.josephcalver.model.dto.SearchResult;
import com.josephcalver.model.repository.ProfileDao;

@Service
public class SearchService {

	@Autowired
	private ProfileDao profileDao;

	public List<SearchResult> search(String searchTerm) {

		return profileDao.findByInterestsNameContainingIgnoreCase(searchTerm).stream().map(SearchResult::new).collect(Collectors.toList());

	}

}
