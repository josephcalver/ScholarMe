package com.josephcalver.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.josephcalver.model.Profile;
import com.josephcalver.model.SiteUser;
import com.josephcalver.service.ProfileService;
import com.josephcalver.service.SiteUserService;

@Controller
public class ProfileController {

	@Autowired
	SiteUserService siteUserService;

	@Autowired
	ProfileService profileService;

	private SiteUser getUser() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String email = auth.getName();
		return siteUserService.get(email);
	}

	@RequestMapping(value = "/profile")
	ModelAndView showProfile(ModelAndView modelAndView) {

		SiteUser user = getUser();
		Profile profile = profileService.getUserProfile(user);

		if (profile == null) {
			profile = new Profile();
			profile.setUser(user);
			profileService.save(profile);
		}

		Profile webProfile = new Profile();
		webProfile.safeCopyFrom(profile);
		modelAndView.getModel().put("profile", webProfile);

		modelAndView.setViewName("profile");
		return modelAndView;
	}

	@RequestMapping(value = "/editprofileabout", method = RequestMethod.GET)
	ModelAndView editProfileAbout(ModelAndView modelAndView) {
		SiteUser user = getUser();
		Profile profile = profileService.getUserProfile(user);

		Profile webProfile = new Profile();
		webProfile.safeCopyFrom(profile);
		modelAndView.getModel().put("profile", webProfile);

		modelAndView.setViewName("editprofileabout");
		return modelAndView;
	}

	@RequestMapping(value = "/editprofileabout", method = RequestMethod.POST)
	ModelAndView editProfileAbout(ModelAndView modelAndView, @Valid Profile webProfile, BindingResult result) {
		modelAndView.setViewName("editprofileabout");

		SiteUser user = getUser();
		Profile profile = profileService.getUserProfile(user);

		profile.safeMergeWith(webProfile);

		if (!result.hasErrors()) {
			profileService.save(profile);
		}

		modelAndView.setViewName("redirect:/profile");
		return modelAndView;
	}

}
