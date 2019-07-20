package com.viktor.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.viktor.model.Status;
import com.viktor.service.StatusService;

@RestController
public class StatusController {

	@Autowired
	private StatusService statusService;
	
	@RequestMapping("/status")
	public List<Status> getStatus(@RequestParam(value = "search", defaultValue = "all") String searchString) {
		if (searchString.equalsIgnoreCase("all")) {
			return statusService.getStatuses();
		} else {
			return statusService.getStatusBy(searchString);
		}
	}
}
