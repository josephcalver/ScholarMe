package com.josephcalver.model.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.josephcalver.model.entity.StatusUpdate;

@Repository
public interface StatusUpdateDao extends PagingAndSortingRepository<StatusUpdate, Long> {

	StatusUpdate findFirstByOrderByAddedDesc();

}
