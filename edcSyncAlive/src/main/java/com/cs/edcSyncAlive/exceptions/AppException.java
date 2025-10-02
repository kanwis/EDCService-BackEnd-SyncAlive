package com.cs.edcSyncAlive.exceptions;


public class AppException extends RuntimeException{

	private static final long serialVersionUID = -2266389723240627847L;
	 
	private final String code;

    public AppException(String message, String code) {
        super(message);
        this.code = code;
    }
    
    public String getCode() {
    	return code;
    }
}
