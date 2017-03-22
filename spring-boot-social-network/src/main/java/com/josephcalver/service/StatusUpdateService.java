package com.josephcalver.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.josephcalver.model.entity.StatusUpdate;
import com.josephcalver.model.repository.StatusUpdateDao;

@Service
public class StatusUpdateService {

	@Value("${status.page.size}")
	private int pageSize;

	@Autowired
	private StatusUpdateDao statusUpdateDao;

	public void save(StatusUpdate statusUpdate) {
		statusUpdateDao.save(statusUpdate);
	}

	public StatusUpdate getLatest() {
		return statusUpdateDao.findFirstByOrderByAddedDesc();
	}

	public Page<StatusUpdate> getPage(int pageNumber) {

		// Zero-based, so subtract 1
		PageRequest request = new PageRequest(pageNumber - 1, pageSize, Sort.Direction.DESC, "added");

		return statusUpdateDao.findAll(request);
	}

	public StatusUpdate get(Long id) {
		return statusUpdateDao.findOne(id);
	}

	public void delete(Long id) {
		statusUpdateDao.delete(id);
	}

}
