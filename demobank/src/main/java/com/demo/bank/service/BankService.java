package com.demo.bank.service;

import java.util.List;


import com.demo.bank.bean.Account;
import com.demo.bank.bean.Customer;
import com.demo.bank.exception.InsufficentFundException;
import com.demo.bank.exception.InvalidAccountException;
import com.demo.bank.exception.InvalidAmountException;
import com.demo.bank.exception.InvalidEmailException;

 public interface BankService {

	
	public Double showBalance(int accountid) throws InvalidAccountException;
	
	public Double withDrawMoney(int accountid, Double transactionAmount)throws InvalidAccountException,InvalidAmountException,InsufficentFundException;
	
	public Double depositMoney(int accountid, Double transactionAmount)throws InvalidAccountException,InvalidAmountException;
	
	public void TransferFund(int fromAccountid,int toAccountId, Double transactionAmount )throws InvalidAccountException,InvalidAmountException,InsufficentFundException;
	
	public List showLastTransactions(int accountid)throws InvalidAccountException;
	
	public void openAccount(Customer customer,Account account) throws InvalidAmountException,InvalidEmailException;
	
}
