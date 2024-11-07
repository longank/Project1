package com.javaweb.customexception;

@SuppressWarnings("serial")
public class FieldRequiredException extends RuntimeException{

	public FieldRequiredException(String s) {
		super(s);
	}
	
}
