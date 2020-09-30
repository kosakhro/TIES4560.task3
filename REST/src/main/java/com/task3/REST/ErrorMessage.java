package com.task3.REST;

import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement
public class ErrorMessage {
	private String errorMessage;
	// own custom error code
	private int errorCode;
	// link to documentation regarding a error and it’s resolving
	private String documentation;

	public ErrorMessage() {
	}

	public ErrorMessage(String errorMessage, int errorCode, String documentation) {
		super();
		this.errorMessage = errorMessage;
		this.errorCode = errorCode;
		this.documentation = documentation;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public int getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(int errorCode) {
		this.errorCode = errorCode;
	}

	public String getDocumentation() {
		return documentation;
	}

	public void setDocumentation(String documentation) {
		this.documentation = documentation;
	}
}