package com.demo.bank.exception;

public class InsufficentFundException extends RuntimeException{
	
	public InsufficentFundException(String message){
		super(message);
	}

}
