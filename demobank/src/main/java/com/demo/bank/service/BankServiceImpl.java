package com.demo.bank.service;

import java.util.List;
import java.util.regex.Pattern;

import com.demo.bank.bean.Account;
import com.demo.bank.bean.Customer;
import com.demo.bank.bean.Transaction;
import com.demo.bank.dao.BankServiceDao;
import com.demo.bank.dao.BankServiceDaoImpl;
import com.demo.bank.exception.InsufficentFundException;
import com.demo.bank.exception.InvalidAccountException;
import com.demo.bank.exception.InvalidAmountException;
import com.demo.bank.exception.InvalidEmailException;

public class BankServiceImpl implements BankService{

	
	BankServiceDao objBankServiceDao= new BankServiceDaoImpl();
	@Override
	public Double showBalance(int accountid) throws  InvalidAccountException {
		return objBankServiceDao.showBalance( accountid);
	}
	
	@Override
	public Double withDrawMoney(int accountid, Double transactionAmount)
			throws InvalidAccountException, InvalidAmountException, InsufficentFundException {
		Double currentBalance= objBankServiceDao.showBalance(accountid);
		if(currentBalance<transactionAmount) {
			throw new InsufficentFundException("Account no balance is insufficient balance is "+currentBalance +" Txn amount is "+transactionAmount);
		}
		currentBalance=currentBalance-transactionAmount;
		objBankServiceDao.updateBalance(accountid, currentBalance,new Transaction("Debit",transactionAmount,currentBalance ));
		return currentBalance;
	}


	@Override
	public Double depositMoney(int accountid, Double transactionAmount) throws InvalidAccountException, InvalidAmountException {
		Double currentBalance= objBankServiceDao.showBalance(accountid);
		if(transactionAmount<=0) {
			throw new InvalidAmountException("Invlaid Amount ! Amount should be gratherthan 0");
		}
		currentBalance=currentBalance+transactionAmount;
		objBankServiceDao.updateBalance(accountid, currentBalance,new Transaction("Credit",transactionAmount,currentBalance ));
		return currentBalance;
	}

	@Override
	public void TransferFund(int fromAccountid, int toAccountId, Double transactionAmount)
			throws InvalidAccountException, InvalidAmountException, InsufficentFundException {
		Double fromCurrentBalance= objBankServiceDao.showBalance(fromAccountid);
		System.out.println("fromCurrentBalance" +fromCurrentBalance);
		Double toCurrentBalance= objBankServiceDao.showBalance(toAccountId);
		System.out.println("toCurrentBalance" +toCurrentBalance);

		if(transactionAmount<=0) {
			throw new InvalidAmountException("Invlaid Amount ! Amount should be gratherthan 0");
		}
		if(fromCurrentBalance<transactionAmount) {
			throw new InsufficentFundException("Insufficient Fund Balance !! Balance is "+fromCurrentBalance + " transaction Amount is "+transactionAmount);
		}
		System.out.println("fromAccountid  "+fromAccountid+" "+ (fromCurrentBalance-transactionAmount));
		System.out.println("toAccountId  "+toAccountId+" "+ (toCurrentBalance+transactionAmount));
		objBankServiceDao.updateBalance(fromAccountid, fromCurrentBalance-transactionAmount,new Transaction("Debit",transactionAmount,fromCurrentBalance-transactionAmount ));
		objBankServiceDao.updateBalance(toAccountId, toCurrentBalance+transactionAmount,new Transaction("Credit",transactionAmount,toCurrentBalance+transactionAmount ));
	}

	@Override
	public List showLastTransactions(int accountid) throws InvalidAccountException {
		return objBankServiceDao.showLastTransactions(accountid);
	}

	@Override
	public void openAccount(Customer customer , Account account)throws InvalidEmailException,InvalidAmountException {
		String   regexPattern = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@" 
				+ "[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$";
		Pattern pattern = Pattern.compile(regexPattern);
		if( ! pattern.matcher(customer.getEmail()).matches() || customer.getEmail()==null || customer.getEmail().equals("")) 
		{
			throw new InvalidEmailException("Email id is not valid format");
		}
		if(account.getCurrentBalance()<1000.0) {
			throw new InvalidAmountException("minimum amount to open account is 1000 ");
		}
		objBankServiceDao.openAccount(customer,account);
	}

	
}
