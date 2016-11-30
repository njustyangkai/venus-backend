package com.venus.frame.utils;

public class ResultBean {
	private boolean success;
	private String message;
	private Object data;

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

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public static ResultBean getSuccess(String message, Object data) {
		ResultBean res = new ResultBean();
		res.setSuccess(true);
		res.setMessage(message);
		res.setData(data);
		return res;
	}

	public static ResultBean getFail(String message, Object data) {
		ResultBean res = new ResultBean();
		res.setSuccess(false);
		res.setMessage(message);
		res.setData(data);
		return res;
	}
}
