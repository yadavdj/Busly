package com.dj.busly.utils;

public class ResponseMessage {
	String response;
	
	/**
	 * Constructor
	 * @param response
	 */
	public ResponseMessage(String response) {
		super();
		this.response = response;
	}

	public String getResponse() {
		return response;
	}

	public void setResponse(String response) {
		this.response = response;
	}
	
}