package com.demo.bank.dao;

import java.util.List;

import com.demo.bank.bean.Account;
import com.demo.bank.bean.Customer;
import com.demo.bank.bean.Transaction;
import com.demo.bank.exception.InvalidAccountException;

public interface BankServiceDao {

	void openAccount(Customer customer,Account account);
	Double showBalance(int accountid) throws InvalidAccountException;
	void updateBalance(int accountid ,Double currentBalance, Transaction t) throws InvalidAccountException;
	//void insertTransaction(Transaction transaction);
	List showLastTransactions(int accountid);
}
