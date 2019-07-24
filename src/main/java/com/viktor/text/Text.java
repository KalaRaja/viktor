package com.viktor.text;

import org.springframework.stereotype.Component;

@Component
public class Text {

	private final String ALL_RUNNING = "All of your end points are running";
	private final String NONE_RUNNING = "None of your end points are running";
	private final String RUNNING = "Your end point is running";
	private final String NOT_RUNNING = "Your end point is not running";
	private final String PARTIAL_RUNNING = "of your end points are not running";
	private final String NOT_FOUND = "No name found, please try with a different name";
	private final String DATE_FORMAT = "yyyy.MM.dd.HH.mm.ss";
	private final String WENT_DOWN_ON = "Your end point went down on";
	private final String AND = "and";
	private final String AT = "at";
	private final String OF = "of";

	public String getOF() {
		return OF;
	}

	public String getWENT_DOWN_ON() {
		return WENT_DOWN_ON;
	}

	public String getAND() {
		return AND;
	}

	public String getAT() {
		return AT;
	}

	public String getDATE_FORMAT() {
		return DATE_FORMAT;
	}

	public String getNOT_FOUND() {
		return NOT_FOUND;
	}

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
