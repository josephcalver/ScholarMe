package com.josephcalver.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.josephcalver.model.StatusUpdate;
import com.josephcalver.service.StatusUpdateService;

@Controller
public class PageController {

	@Autowired
	private StatusUpdateService statusUpdateService;

	@RequestMapping("/")
	String home() {
		return "home";
	}

	@RequestMapping("/about")
	String about() {
		return "about";
	}

	@RequestMapping(value = "/viewstatus", method = RequestMethod.GET)
	ModelAndView viewStatus(ModelAndView modelAndView) {
		modelAndView.setViewName("viewstatus");
		return modelAndView;
	}

	@RequestMapping(value = "/addstatus", method = RequestMethod.GET)
	ModelAndView addStatus(ModelAndView modelAndView, @ModelAttribute("statusUpdate") StatusUpdate statusUpdate) {
		modelAndView.setViewName("addstatus");
		StatusUpdate latestStatusUpdate = statusUpdateService.getLatest();
		modelAndView.getModel().put("latestStatusUpdate", latestStatusUpdate);
		return modelAndView;
	}

	@RequestMapping(value = "/addstatus", method = RequestMethod.POST)
	ModelAndView addStatus(ModelAndView modelAndView, @Valid StatusUpdate statusUpdate, BindingResult result) {
		modelAndView.setViewName("addstatus");

		if (!result.hasErrors()) {
			statusUpdateService.save(statusUpdate);
			modelAndView.getModel().put("statusUpdate", new StatusUpdate());
		}

		StatusUpdate latestStatusUpdate = statusUpdateService.getLatest();
		modelAndView.getModel().put("latestStatusUpdate", latestStatusUpdate);
		return modelAndView;
	}

}
