package com.josephcalver.model.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.josephcalver.model.entity.Profile;
import com.josephcalver.model.entity.SiteUser;

@Repository
public interface ProfileDao extends CrudRepository<Profile, Long> {

	Profile findByUser(SiteUser user);

	List<Profile> findByInterestsNameContainingIgnoreCase(String searchTerm);
	
	Page<Profile> findByInterestsNameContainingIgnoreCase(String searchTerm, Pageable request);

}
