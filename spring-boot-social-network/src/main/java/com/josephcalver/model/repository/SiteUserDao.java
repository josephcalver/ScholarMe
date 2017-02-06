package com.josephcalver.model.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.josephcalver.model.entity.SiteUser;

@Repository
public interface SiteUserDao extends CrudRepository<SiteUser, Long> {

	SiteUser findByEmail(String email);

}
