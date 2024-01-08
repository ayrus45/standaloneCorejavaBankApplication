package com.demo.bank.main;

import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

import com.demo.bank.bean.Account;
import com.demo.bank.bean.Customer;
import com.demo.bank.exception.InvalidAccountException;
import com.demo.bank.exception.InvalidAmountException;
import com.demo.bank.exception.InvalidEmailException;
import com.demo.bank.service.BankService;
import com.demo.bank.service.BankServiceImpl;

public class MainApp 
{
	public static void main(String[] args) 
	{
		int ans= 0;
		Scanner sc= new Scanner(System.in);
	   BankService service = new BankServiceImpl();
	   Customer customer ;
		do 
		{
			System.out.println("0.open account\n1.ShowBalance \n2.WithDraw Money"
					+ "\n3.depositMoney \n4.TransferFund \n5.showLastTransactions");

			System.out.println("Enter your choice ");
			int choice = sc.nextInt();
			switch (choice) {
			case 0:
				System.out.println("Enter Customer name ");
				String custName =sc.next();
				System.out.println("Enter Customer email");
				String email=sc.next();
				Customer objCustomer=new Customer(custName, email);
				System.out.println("Enter Account opening balance  ");
				Double currentbalance=sc.nextDouble();
				Account objAccount= new Account(objCustomer, currentbalance);
				try {
				service.openAccount(objCustomer, objAccount);
				System.out.println("Account opened"+objAccount);
				}catch (InvalidEmailException e) {
					System.out.println(e.getMessage());
					//e.printStackTrace();
				}
				catch (InvalidAmountException e) {
					System.out.println(e.getMessage());
					//e.printStackTrace();
				}
				catch (Exception e) {
				System.out.println(e.getMessage());
				e.printStackTrace();
				}
				break;
			case 1:
				System.out.println("Enter Account ID ");
				int id =sc.nextInt();
				try {
					Double d = service.showBalance(id);
					System.out.println("Account No : "+id +" balance is : "+d);
				} catch (InvalidAccountException e) {
					System.out.println(e.getMessage());
				}catch (Exception e) {
					System.out.println(e.getMessage());
				}
				break;
			case 2:
				System.out.println("Enter Account ID ");
				id =sc.nextInt();
				System.out.println("Enter Amount to withdraw ");
				Double transactionAmount=sc.nextDouble();
				try {
					Double d = service.withDrawMoney(id,transactionAmount);
					System.out.println("Account No : "+id +" balance is : "+d);
				} catch (InvalidAccountException e) {
					System.out.println(e.getMessage());
				}catch (Exception e) {
					System.out.println(e.getMessage());
				}

				break;
			case 3:
				System.out.println("Enter Account ID ");
				id =sc.nextInt();
				
				System.out.println("Enter Amount to deposit ");
				Double transactionAmount1=sc.nextDouble();
				try {
					Double d = service.depositMoney(id,transactionAmount1);
					System.out.println("Account No : "+id +" balance after deposit is : "+d);
				} catch (InvalidAccountException e) {
					System.out.println(e.getMessage());
				}catch (Exception e) {
					System.out.println(e.getMessage());
				}
				break;
			case 4:
				System.out.println("Enter Account ID ");
				id =sc.nextInt();
				System.out.println("Enter Beneficiary Account ID ");
				int acc2 =sc.nextInt();
				System.out.println("Enter Amount to transfer ");
				try {
				Double transactionAmount2 =sc.nextDouble();
				service.TransferFund(id,acc2,transactionAmount2);
				 System.out.println("Amount Sucessfully transfred");
				} catch (InvalidAccountException e) {
					System.out.println(e.getMessage());
				}catch (Exception e) {
					System.out.println(e.getMessage());
				}
				break;
			case 5:
				try {
				System.out.println("Enter Account ID ");
				id =sc.nextInt();
				List<Account> acc=service.showLastTransactions(id);
				System.out.println(acc);
				}catch (Exception e) {
					System.out.println(e.getMessage());
				}
				break;
			default:
				break;
			}
			System.out.println("want to continue ?(1 to continue)");
			ans= sc.nextInt();
		} while (ans==1);

	}


}
