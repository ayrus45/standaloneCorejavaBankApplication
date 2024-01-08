package com.demo.bank.exception;

public class InvalidEmailException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5342543532993169063L;

	public InvalidEmailException(String string) {
		super(string);
	}

}
