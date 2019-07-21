package com.viktor.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Status {

	@Id
	private String url;

	@Column(name = "responsecode")
	private int responseCode;

	@Column(name = "timestamp")
	private String pollTimeStamp;

	@Column(name = "timestamp_since_last_change")
	private String lastStatusChangeTimeStamp;

	private String tag;

	public Status(String url, int responseCode, String pollTimeStamp, String lastStatusChangeTimeStamp, String tag) {
		super();
		this.url = url;
		this.responseCode = responseCode;
		this.pollTimeStamp = pollTimeStamp;
		this.lastStatusChangeTimeStamp = lastStatusChangeTimeStamp;
		this.tag = tag;
	}

	public Status() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public int getResponseCode() {
		return responseCode;
	}

	public void setResponseCode(int responseCode) {
		this.responseCode = responseCode;
	}

	public String getPollTimeStamp() {
		return pollTimeStamp;
	}

	public void setPollTimeStamp(String pollTimeStamp) {
		this.pollTimeStamp = pollTimeStamp;
	}

	public String getLastStatusChangeTimeStamp() {
		return lastStatusChangeTimeStamp;
	}

	public void setLastStatusChangeTimeStamp(String lastStatusChangeTimeStamp) {
		this.lastStatusChangeTimeStamp = lastStatusChangeTimeStamp;
	}

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

}
