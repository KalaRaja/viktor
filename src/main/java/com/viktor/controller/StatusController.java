package com.viktor.controller;

import java.util.List;
import java.util.stream.Collectors;

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
	public String getStatus(@RequestParam(value = "search", defaultValue = "all") String searchString) {
		if (searchString.equalsIgnoreCase("all")) {
			return dataToText(statusService.getStatuses());
		} else {
			return dataToText(statusService.getStatusBy(searchString));
		}
	}
	
	private static String dataToText(List<Status> statuses) {
		long notOk = statuses.stream().map(status -> status.getResponseCode()).filter(code -> code != 200).collect(Collectors.counting());
		if (notOk == 0) {
			return "All of your web services are running";
		}
		if (statuses.size() == 1) {
			return notOk != 0 ? "Your web service is not running" : "Your web service is running";
		}
		return notOk + " out of " + statuses.size() + " of your web services are running";
	}
}
