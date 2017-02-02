package com.josephcalver.tests;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import javax.transaction.Transactional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.josephcalver.model.Interest;
import com.josephcalver.model.Profile;
import com.josephcalver.model.SiteUser;
import com.josephcalver.service.InterestService;
import com.josephcalver.service.ProfileService;
import com.josephcalver.service.SiteUserService;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@WebAppConfiguration
@Transactional
public class ProfileControllerRestTest {

	@Autowired
	private SiteUserService siteUserService;

	@Autowired
	private ProfileService profileService;

	@Autowired
	private InterestService interestService;

	private MockMvc mockMvc;

	@Autowired
	private WebApplicationContext webApplicationContext;

	@Before
	public void setup() {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	}
	
	@Test
	@WithMockUser(username="test@example.com")
	public void testSaveAndDeleteInterest() throws Exception {
		
		String interestText = "interest not in db";
		
		mockMvc.perform(post("/save-interest").param("name", interestText)).
			andExpect(status().isOk());
		
		Interest interest = interestService.get(interestText);
		
		assertNotNull("Interest should exist", interest);
		assertEquals("Retrieved interest text should match", interestText, interest.getName());
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String email = auth.getName();
		
		SiteUser user = siteUserService.get(email);
		Profile profile = profileService.getUserProfile(user);
		
		assertTrue("Profile should contain interest", profile.getInterests().contains(new Interest(interestText)));
		
		mockMvc.perform(post("/delete-interest").param("name", interestText)).
			andExpect(status().isOk());
		
		profile = profileService.getUserProfile(user);
		
		assertFalse("Profile should not contain interest", profile.getInterests().contains(new Interest(interestText)));
		
	}

}
