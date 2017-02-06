package com.josephcalver.tests;

import static org.junit.Assert.*;

import java.util.HashSet;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.josephcalver.model.entity.Interest;
import com.josephcalver.model.entity.Profile;
import com.josephcalver.model.entity.SiteUser;
import com.josephcalver.service.InterestService;
import com.josephcalver.service.ProfileService;
import com.josephcalver.service.SiteUserService;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@WebAppConfiguration
@Transactional
public class ProfileTest {
	
	@Autowired
	private SiteUserService siteUserService;
	
	@Autowired
	private ProfileService profileService;
	
	@Autowired
	private InterestService interestService;
	
	private SiteUser[] users = {
			new SiteUser("testuser1@example.com", "password", "firstname1", "lastname1"),
			new SiteUser("testuser2@example.com", "password", "firstname2", "lastname2"),
			new SiteUser("testuser3@example.com", "password", "firstname3", "lastname3"),
	};
	
	private String[][] interests = {
			{"music", "hiking_xyz", "travel"},
			{"music", "music", "gardening"},
			{"surfing", "walking", "opera"},
	};

	@Test
	public void testInterests() {
		
		for (int i=0; i<users.length; i++) {
			SiteUser user = users[i];
			String[] interestArray = interests[i];
			siteUserService.register(user);
			
			HashSet<Interest> interestsSet = new HashSet<>();
			
			for(String interestText: interestArray) {
				Interest interest = interestService.createIfNotExists(interestText);
				interestsSet.add(interest);
				
				assertNotNull("Interest should not be null", interest);
				assertNotNull("Interest should have ID", interest.getId());
				assertEquals("Text should match", interestText, interest.getName());
			}
			
			Profile profile = new Profile(user);
			profile.setInterests(interestsSet);
			profileService.save(profile);
			
			Profile retrievedProfile = profileService.getUserProfile(user);
			
			assertEquals("Interest sets should match", interestsSet, retrievedProfile.getInterests());
		}
		
	}

}
