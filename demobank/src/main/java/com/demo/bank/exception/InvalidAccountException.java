package com.demo.bank.exception;

public class InvalidAccountException extends RuntimeException {
	public InvalidAccountException(String message){
		super(message);
	}

}
