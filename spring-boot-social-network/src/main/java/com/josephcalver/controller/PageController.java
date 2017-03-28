package com.josephcalver.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.josephcalver.model.entity.StatusUpdate;
import com.josephcalver.service.StatusUpdateService;

@Controller
public class PageController {

	@Autowired
	private StatusUpdateService statusUpdateService;

	@Value(value = "${message.error.forbidden}")
	private String accessDeniedMessage;

	@RequestMapping("/")
	ModelAndView home(ModelAndView modelAndView) {

		StatusUpdate statusUpdate = statusUpdateService.getLatest();

		modelAndView.getModel().put("statusUpdate", statusUpdate);
		modelAndView.setViewName("home");

		return modelAndView;
	}

	@RequestMapping("/403")
	ModelAndView accessDenied(ModelAndView modelAndView) {

		modelAndView.getModel().put("accessDeniedMessage", accessDeniedMessage);
		modelAndView.setViewName("verificationmessage");

		return modelAndView;
	}

	@RequestMapping("/about")
	ModelAndView about(ModelAndView modelAndView) {

		modelAndView.setViewName("about");

		return modelAndView;
	}

	@RequestMapping("/preview")
	ModelAndView preview(ModelAndView modelAndView) {

		modelAndView.setViewName("preview");

		return modelAndView;
	}

	@RequestMapping("/loggedout")
	ModelAndView loggedout(ModelAndView modelAndView) {

		modelAndView.setViewName("loggedout");

		return modelAndView;
	}

}
