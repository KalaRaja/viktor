package com.viktor.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.viktor.model.Status;
import com.viktor.repo.StatusRepo;

@Service
public class StatusService {

	@Autowired StatusRepo statusRepo;
	
	public List<Status> getStatuses() {
		return statusRepo.findAll();
	}
	
	public List<Status> getStatusBy(String search) {
		List<Status> statuses = statusRepo.findByTag(search);
		if (statuses.isEmpty()) {
			statuses = statusRepo.findByName(search);
		}
		return statuses;
	}
	
	public List<Status> getDownStatus() {
		return statusRepo.findByResponseCodeNot(200);
	}
	
	public List<Status> getDownStatusBy(String tag) {
		return statusRepo.findByResponseCodeNotAndTag(200, tag);
	}
	
	public List<Status> getDownStatusByName(String name) {
		return statusRepo.findByResponseCodeNotAndName(200, name);
	}
	public List<Status> getAll() {
		return statusRepo.findAll();
	}
	
	public List<Status> getAllBy(String tag) {
		return statusRepo.findAllByTag(tag);
	}
}
