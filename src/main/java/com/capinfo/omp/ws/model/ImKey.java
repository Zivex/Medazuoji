package com.capinfo.omp.ws.model;

public class ImKey {
	private String returnType;
	private String generateSerialNumber;
	private String statusCode;
	private String executionTime;
	private String errorMessage;

	public String getReturnType() {
		return returnType;
	}

	public void setReturnType(String returnType) {
		this.returnType = returnType;
	}

	public String getGenerateSerialNumber() {
		return generateSerialNumber;
	}

	public void setGenerateSerialNumber(String generateSerialNumber) {
		this.generateSerialNumber = generateSerialNumber;
	}

	public String getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(String statusCode) {
		this.statusCode = statusCode;
	}

	public String getExecutionTime() {
		return executionTime;
	}

	public void setExecutionTime(String executionTime) {
		this.executionTime = executionTime;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
}
