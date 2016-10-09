package com.venus.frame.utils;

public class ResultBean {
	private boolean success;
	private String message;
	private Object object;
	public boolean isSuccess() {
		return success;
	}
	public void setSuccess(boolean success) {
		this.success = success;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Object getObject() {
		return object;
	}
	public void setObject(Object object) {
		this.object = object;
	}
	
	public static ResultBean getResult(boolean success,String message,Object object) {
		ResultBean res = new ResultBean();
		res.setSuccess(success);
		res.setMessage(message);
		res.setObject(object);
		return res;
	}
}
