package com.savsoftware.helpdesk.resources.Excption;

import java.io.Serializable;

public class StandarError implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Long timestLong;
	private Integer Status;
	private String error;
	private String message;
	private String path;
	
	public StandarError() {
		super();
	}

	public StandarError(Long timestLong, Integer status, String error, String message, String path) {
		super();
		this.timestLong = timestLong;
		Status = status;
		this.error = error;
		this.message = message;
		this.path = path;
	}

	public Long getTimestLong() {
		return timestLong;
	}

	public void setTimestLong(Long timestLong) {
		this.timestLong = timestLong;
	}

	public Integer getStatus() {
		return Status;
	}

	public void setStatus(Integer status) {
		Status = status;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}
	
}
