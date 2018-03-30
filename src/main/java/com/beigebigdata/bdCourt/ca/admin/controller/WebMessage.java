package com.beigebigdata.bdCourt.ca.admin.controller;

public class WebMessage {

	public static int ERROR = 500;
	public static int SUCCESS = 200;

	private int status;

	private Object message;

	public WebMessage() {

	}

	public WebMessage(int status, Object message) {
		this.status = status;
		this.message = message;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public Object getMessage() {
		return message;
	}

	public void setMessage(Object message) {
		this.message = message;
	}

}
