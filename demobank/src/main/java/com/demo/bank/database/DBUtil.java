package com.demo.bank.database;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class DBUtil 
{
	private static EntityManagerFactory managerFactory;
	private static EntityManager entityManager;
	static 
	{
		managerFactory= Persistence.createEntityManagerFactory("jpademo");
	}
	 public static EntityManager getManager()
	 {
		 if(entityManager == null || ! entityManager.isOpen())
		 {
			 entityManager = managerFactory.createEntityManager();
			 System.out.println("Entity manager is loladed..");
		 }
		 return entityManager;
	 }

}