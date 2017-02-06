package com.josephcalver.model.dto;

import java.util.Set;

import com.josephcalver.model.entity.Interest;
import com.josephcalver.model.entity.Profile;

public class SearchResult {

	private Long userId;
	private String firstName;
	private String lastName;
	private Set<Interest> interests;

	public SearchResult(Profile profile) {
		this.userId = profile.getUser().getId();
		this.firstName = profile.getUser().getFirstName();
		this.lastName = profile.getUser().getLastName();
		this.interests = profile.getInterests();
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Set<Interest> getInterests() {
		return interests;
	}

	public void setInterests(Set<Interest> interests) {
		this.interests = interests;
	}

	@Override
	public String toString() {
		return "SearchResult [userId=" + userId + ", firstName=" + firstName + ", lastName=" + lastName + ", interests="
				+ interests + "]";
	}

}
