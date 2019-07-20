package com.viktor.service;

import java.util.ArrayList;
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
	
	public List<Status> getStatusBy(String id) {
		List<Status> statuses = statusRepo.findByTag(id);
		if (statuses.isEmpty()) {
			new ArrayList<Status>().add(statusRepo.findById(id).orElse(new Status()));
		}
		return statuses;
		
	}
}
