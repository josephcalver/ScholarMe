package com.josephcalver.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
	ModelAndView search(ModelAndView modelAndView, @RequestParam("s") String searchTerm) {

		List<SearchResult> results = searchService.search(searchTerm);

		modelAndView.getModel().put("results", results);
		modelAndView.setViewName("search-results");

		return modelAndView;
	}

}
