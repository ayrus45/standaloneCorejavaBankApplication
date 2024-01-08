package com.demo.bank.exception;

public class InvalidAmountException  extends RuntimeException{
	
	public InvalidAmountException(String message){
		super(message);
	}

}
