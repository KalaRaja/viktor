package com.viktor.text;

import org.springframework.stereotype.Component;

@Component
public class Text {

	private final String ALL_RUNNING = "All of your web services are running";
	private final String NONE_RUNNING = "None of your web services are running";
	private final String RUNNING = "Your web service is running";
	private final String NOT_RUNNING = "Your web service is not running";
	private final String PARTIAL_RUNNING = "of your web services are running";

	public String getALL_RUNNING() {
		return ALL_RUNNING;
	}

	public String getNONE_RUNNING() {
		return NONE_RUNNING;
	}

	public String getRUNNING() {
		return RUNNING;
	}

	public String getNOT_RUNNING() {
		return NOT_RUNNING;
	}

	public String getPARTIAL_RUNNING() {
		return PARTIAL_RUNNING;
	}

}
