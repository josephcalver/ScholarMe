package com.josephcalver.model;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SiteUserDao extends CrudRepository<SiteUser, Long> {

	SiteUser findByEmail(String email);

}
