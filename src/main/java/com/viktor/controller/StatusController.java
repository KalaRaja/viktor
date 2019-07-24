package com.viktor.controller;

import java.text.DateFormatSymbols;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
	
	Logger logger = LoggerFactory.getLogger(StatusController.class);
	
	@RequestMapping("/status")
	public String getStatus(@RequestParam(value = "search", defaultValue = "all") String searchString) {
		logger.info("/status {}",  searchString);
		if (searchString.equalsIgnoreCase("all")) {
			return dataToText(statusService.getStatuses());
		} else {
			return dataToText(statusService.getStatusBy(searchString.toLowerCase()));
		}
	}
	
	@RequestMapping("/down")
	public String getDown(@RequestParam(value = "search", defaultValue = "all") String searchString) {
		logger.info("/down {}", searchString);
		List<String> names = null;
		if (searchString.equalsIgnoreCase("all")) {
			 names = statusService.getDownStatus().stream().map((status) -> status.getName()).collect(Collectors.toList());
		} else {
			if (statusService.getAll().stream().filter((status) -> status.getTag().equalsIgnoreCase(searchString)).collect(Collectors.toList()).isEmpty()) {
				return text.getNOT_FOUND();
			}
			names = statusService.getDownStatusBy(searchString.toLowerCase()).stream().map((status) -> status.getName()).collect(Collectors.toList());
		}
		
		return addAnd(names);
		
	}
	
	@RequestMapping("/all")
	public String getAll(@RequestParam(value = "search", defaultValue = "all") String searchString) {
		logger.info("/all {}", searchString);
		List<String> names;
		if (searchString.equalsIgnoreCase("all")) {
			names =  statusService.getAll().stream().map((status) -> status.getName()).collect(Collectors.toList());
		} else {
			names = statusService.getAllBy(searchString.toLowerCase()).stream().map((status) -> status.getName()).collect(Collectors.toList());
		}
		
		return addAnd(names);
	}
	
	@RequestMapping("/downtime")
	public String getDowntome(@RequestParam(value = "search") String serviceName) throws ParseException {
		logger.info("/downtime {}", serviceName);
		if (statusService.getAll().stream().filter(status -> status.getName().equalsIgnoreCase(serviceName)).collect(Collectors.toList()).isEmpty()) {
			return text.getNOT_FOUND();
		}
		
		List<Status> statusList = statusService.getDownStatusByName(serviceName.toLowerCase());
		if (statusList.isEmpty()) {
			return text.getRUNNING();
		}
		
		Status status = statusList.get(0);
		
		String textDateTime = status.getLastStatusChangeTimeStamp();
		
		return formatDate(textDateTime);
	}
	
	private String addAnd(List<String> items) {
		if (items.isEmpty()) {
			return text.getALL_RUNNING();
		}
		
		if (items.size() == 1) {
			return items.get(0) ;
		}
		
		items.add(items.size() - 1, text.getAND());
		return  String.join(", ", items).replace(", " + text.getAND() + ",", " "+text.getAND()); 
	}
	
	private String dataToText(List<Status> statuses) {
		if (statuses.isEmpty()) {
			return text.getNOT_FOUND();
		}
		
		long notOk = statuses.stream().map(status -> status.getResponseCode()).filter(code -> code != 200).collect(Collectors.counting());
		
		if (statuses.size() == 1) {
			return notOk != 0 ? text.getNOT_RUNNING() : text.getRUNNING();
		}
		
		if (notOk == 0) {
			return text.getALL_RUNNING();
		}
		
		if (notOk == statuses.size()) {
			return text.getNONE_RUNNING();
		}
		
		return notOk + " " +text.getOF()+ " " + statuses.size() + " " + text.getPARTIAL_RUNNING();
	}
	
	private String formatDate(String dateString) throws ParseException {
		SimpleDateFormat dateFormat = new SimpleDateFormat(text.getDATE_FORMAT());
		Date date = dateFormat.parse(dateString);
		DateFormatSymbols dateFormatSymbols = new DateFormatSymbols();
		String[] days = dateFormatSymbols.getWeekdays();
		String[] months = dateFormatSymbols.getMonths();
		String day = days[date.getDay()];
		int dateNumber = date.getDate();
		String month = months[date.getMonth()];		
		int year = date.getYear();
		return text.getWENT_DOWN_ON() + " " + day + ", " + dateNumber + " " + month + ", " + (year + 1900) + ", " + text.getAT() + " " + new SimpleDateFormat("hh:mm a").format(date);
	}
}

