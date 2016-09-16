package com.testthree.message.business.dto;

import com.testthree.message.business.common.ErrorInfo;

public class ResponseObject<T> {
	
	private ErrorInfo error;
	private T responseData;
	
	public ErrorInfo getError() {
		return error;
	}
	public void setError(ErrorInfo error) {
		this.error = error;
	}
	public T getResponseData() {
		return responseData;
	}
	public void setResponseData(T responseData) {
		this.responseData = responseData;
	}
}
