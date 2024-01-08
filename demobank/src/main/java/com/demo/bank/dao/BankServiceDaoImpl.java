package com.demo.bank.dao;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.demo.bank.bean.Account;
import com.demo.bank.bean.Customer;
import com.demo.bank.bean.Transaction;
import com.demo.bank.database.DBUtil;
import com.demo.bank.exception.InvalidAccountException;

import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.Query;

public class BankServiceDaoImpl implements BankServiceDao{
	private static EntityManager manager;

	public BankServiceDaoImpl() 
	{
		manager = DBUtil.getManager();
	}
	public void beginTransaction() 
	{
		manager.getTransaction().begin();		
	}
	public void commitTransaction() 
	{
		manager.getTransaction().commit();		
	}
	@Override
	public void openAccount(Customer customer, Account account) {
		try {
			beginTransaction();
			account.setCutomer(customer);
			manager.persist(account);
			commitTransaction();
			System.out.println(account+" opened");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	@Override
	public Double showBalance(int accountid) throws InvalidAccountException {
		Double bal=0.0;
		try {
		beginTransaction();
		System.out.println("accountid :: "+accountid);
		String jpql = "SELECT a FROM Account a WHERE a.id = :id";
		Account account= manager.createQuery(jpql,Account.class).setParameter("id", accountid).getSingleResult();
		bal=account.getCurrentBalance();
		System.out.println(account);
		commitTransaction();
		}
		catch (NoResultException e) {
			throw new InvalidAccountException("Account Not availble in DB "+accountid);
		}catch (Exception e) {
			e.printStackTrace();
		}
		return bal;
	}
	
	public void updateBalance(int accountid ,Double currentBalance,Transaction t) throws InvalidAccountException {
		try {
		beginTransaction();
		Account acc=manager.find(Account.class, accountid);
		acc.setCurrentBalance(currentBalance);
		Collection<Transaction> aa= acc.getTransaction();
		aa.add(t);
		acc.setTransaction(aa);
		manager.persist(acc);
		//manager.persist(t);
		commitTransaction();
		}
		catch (NoResultException e) {
			throw new InvalidAccountException("Account Not availble in DB "+accountid);
		}catch (Exception e) {
			e.printStackTrace();
		}
	}

	
	public List showLastTransactions(int accountid) {
		ArrayList<Account> account= new ArrayList<Account>();
		beginTransaction();
		account= (ArrayList<Account>) manager.createQuery("SELECT a FROM Account a WHERE a.id = :id", Account.class).setParameter("id", accountid).getResultList();
		commitTransaction();
		return account;
	}
}
