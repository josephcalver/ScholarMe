package com.josephcalver.controller;

import java.io.IOException;
import java.net.URLConnection;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.josephcalver.exceptions.ImageTooSmallException;
import com.josephcalver.exceptions.InvalidFileException;
import com.josephcalver.model.FileInfo;
import com.josephcalver.model.Profile;
import com.josephcalver.model.SiteUser;
import com.josephcalver.service.FileService;
import com.josephcalver.service.ProfileService;
import com.josephcalver.service.SiteUserService;

@Controller
public class ProfileController {

	@Autowired
	private SiteUserService siteUserService;

	@Autowired
	private ProfileService profileService;

	@Autowired
	private FileService fileService;

	@Value(value = "${photo.upload.directory}")
	private String photoUploadDirectory;

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

	@RequestMapping(value = "/edit-profile-about", method = RequestMethod.GET)
	ModelAndView editProfileAbout(ModelAndView modelAndView) {
		SiteUser user = getUser();
		Profile profile = profileService.getUserProfile(user);

		Profile webProfile = new Profile();
		webProfile.safeCopyFrom(profile);
		modelAndView.getModel().put("profile", webProfile);

		modelAndView.setViewName("edit-profile-about");
		return modelAndView;
	}

	@RequestMapping(value = "/edit-profile-about", method = RequestMethod.POST)
	ModelAndView editProfileAbout(ModelAndView modelAndView, @Valid Profile webProfile, BindingResult result) {
		modelAndView.setViewName("edit-profile-about");

		SiteUser user = getUser();
		Profile profile = profileService.getUserProfile(user);

		profile.safeMergeWith(webProfile);

		if (!result.hasErrors()) {
			profileService.save(profile);
		}

		modelAndView.setViewName("redirect:/profile");
		return modelAndView;
	}

	@RequestMapping(value = "/upload-profile-photo", method = RequestMethod.POST)
	ModelAndView handlePhotoUploads(ModelAndView modelAndView, @RequestParam("file") MultipartFile file) {
		modelAndView.setViewName("redirect:/profile");

		SiteUser user = getUser();
		Profile profile = profileService.getUserProfile(user);

		Path oldPhotoPath = profile.getPhoto(photoUploadDirectory);

		try {
			FileInfo photoInfo = fileService.saveImageFile(file, photoUploadDirectory, "photos", "p" + user.getId(), 100, 100);

			profile.setPhotoDetails(photoInfo);
			profileService.save(profile);

			if (oldPhotoPath != null) {
				Files.delete(oldPhotoPath);
			}

		} catch (InvalidFileException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ImageTooSmallException e) {
			e.printStackTrace();
		}

		return modelAndView;
	}

	@RequestMapping(value = "/profile-photo", method = RequestMethod.GET)
	@ResponseBody
	ResponseEntity<InputStreamResource> servePhoto() throws IOException {
		SiteUser user = getUser();
		Profile profile = profileService.getUserProfile(user);

		Path photoPath = Paths.get(photoUploadDirectory, "default", "avatar.jpg");

		if (profile != null && profile.getPhoto(photoUploadDirectory) != null) {
			photoPath = profile.getPhoto(photoUploadDirectory);
		}

		return ResponseEntity.ok().contentLength(Files.size(photoPath))
				.contentType(MediaType.parseMediaType(URLConnection.guessContentTypeFromName(photoPath.toString())))
				.body(new InputStreamResource(Files.newInputStream(photoPath, StandardOpenOption.READ)));
	}

}
