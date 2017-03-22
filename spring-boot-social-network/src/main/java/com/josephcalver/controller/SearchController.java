package com.josephcalver.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.josephcalver.model.dto.SearchResult;
import com.josephcalver.service.SearchService;

@Controller
public class SearchController {

	@Autowired
	private SearchService searchService;

	@RequestMapping(value = "/search", method = { RequestMethod.POST, RequestMethod.GET })
	ModelAndView search(ModelAndView modelAndView, @RequestParam("s") String searchTerm, @RequestParam(name="p", defaultValue="1") int pageNumber) {

		Page<SearchResult> results = searchService.search(searchTerm, pageNumber);

		modelAndView.getModel().put("s", searchTerm);
		modelAndView.getModel().put("page", results);
		modelAndView.setViewName("search-results");

		return modelAndView;
	}

}
