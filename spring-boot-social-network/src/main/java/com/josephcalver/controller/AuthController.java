package com.josephcalver.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.josephcalver.model.entity.SiteUser;
import com.josephcalver.service.EmailService;
import com.josephcalver.service.SiteUserService;

@Controller
public class AuthController {

	@Autowired
	private SiteUserService siteUserService;

	@Autowired
	private EmailService emailService;

	@Value(value = "${message.registration.confirmed}")
	private String registrationConfirmedMessage;

	@Value(value = "${message.invalid.user}")
	private String invalidUserMessage;

	@Value(value = "${message.expired.token}")
	private String expiredTokenMessage;

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

			String token = siteUserService.createEmailVerificationToken(siteUser);

			emailService.sendVerificationEmail(siteUser.getEmail(), token);
			modelAndView.setViewName("redirect:/verifyemail");

			siteUser.setEnabled(true);
			siteUserService.save(siteUser);
		}

		return modelAndView;
	}

	@RequestMapping(value = "/verifyemail")
	ModelAndView verifyEmail(ModelAndView modelAndView) {

		modelAndView.setViewName("verifyemail");

		return modelAndView;
	}

	@RequestMapping(value = "/registrationconfirmed")
	ModelAndView registrationConfirmed(ModelAndView modelAndView) {

		modelAndView.setViewName("message");
		modelAndView.getModel().put("message", registrationConfirmedMessage);

		return modelAndView;
	}

	@RequestMapping(value = "/invaliduser")
	ModelAndView invalidUser(ModelAndView modelAndView) {

		modelAndView.setViewName("message");
		modelAndView.getModel().put("message", invalidUserMessage);

		return modelAndView;
	}

	@RequestMapping(value = "/expiredtoken")
	ModelAndView expiredToken(ModelAndView modelAndView) {

		modelAndView.setViewName("message");
		modelAndView.getModel().put("message", expiredTokenMessage);

		return modelAndView;
	}

}
