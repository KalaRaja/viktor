package com.viktor.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.viktor.model.Status;
import com.viktor.service.StatusService;
import com.viktor.text.Text;

@RestController
public class StatusController {

	@Autowired
	private StatusService statusService;
	
	@Autowired
	private Text text;
	
	@RequestMapping("/status")
	public String getStatus(@RequestParam(value = "search", defaultValue = "all") String searchString) {
		if (searchString.equalsIgnoreCase("all")) {
			return dataToText(statusService.getStatuses());
		} else {
			return dataToText(statusService.getStatusBy(searchString));
		}
	}
	
	@RequestMapping("/down")
	public String getDown(@RequestParam(value = "search", defaultValue = "all") String searchString) {
		List<String> names = null;
		if (searchString.equalsIgnoreCase("all")) {
			 names = statusService.getDownStatus().stream().map((status) -> status.getName()).collect(Collectors.toList());
		} else {
			names = statusService.getDownStatusBy(searchString).stream().map((status) -> status.getName()).collect(Collectors.toList());
		}
		
		if (names.size() == 0) {
			return text.getALL_RUNNING();
		}
		
		if (names.size() == 1) {
			return names.get(0) ;
		}
		
		names.add(names.size() - 1, "and");
		return  String.join(", ", names).replace(", and,", " and");
	}
	
	private String dataToText(List<Status> statuses) {
		if (statuses.size() == 0) {
			return text.getNOT_FOUND();
		}
		
		long notOk = statuses.stream().map(status -> status.getResponseCode()).filter(code -> code != 200).collect(Collectors.counting());
		if (notOk == 0) {
			return text.getALL_RUNNING();
		}
		
		if (notOk == statuses.size()) {
			return text.getNONE_RUNNING();
		}
		
		if (statuses.size() == 1) {
			return notOk != 0 ? text.getRUNNING() : text.getNOT_RUNNING();
		}
		return notOk + " / " + statuses.size() + " " + text.getPARTIAL_RUNNING();
	}
}

