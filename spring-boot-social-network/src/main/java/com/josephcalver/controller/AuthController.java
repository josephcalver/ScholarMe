package com.josephcalver.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.josephcalver.model.SiteUser;
import com.josephcalver.service.SiteUserService;

@Controller
public class AuthController {

	@Autowired
	private SiteUserService siteUserService;

	@RequestMapping("/login")
	String admin() {
		return "login";
	}

	@RequestMapping(value = "/register", method = RequestMethod.GET)
	ModelAndView register(ModelAndView modelAndView) {
		SiteUser siteUser = new SiteUser();
		modelAndView.getModel().put("siteUser", siteUser);
		modelAndView.setViewName("register");
		return modelAndView;
	}

	@RequestMapping(value = "/register", method = RequestMethod.POST)
	ModelAndView register(ModelAndView modelAndView, @ModelAttribute(value = "siteUser") @Valid SiteUser siteUser,
			BindingResult result) {
		modelAndView.setViewName("register");

		if (!result.hasErrors()) {
			siteUserService.register(siteUser);
			modelAndView.setViewName("redirect:/");
		}

		return modelAndView;
	}

}
