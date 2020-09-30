package com.task3.REST;


public class DataNotFoundException extends RuntimeException {

	private static final long serialVersionUID = -5555567240703065575L;

	public DataNotFoundException(String message) {
		super(message);
	}
}