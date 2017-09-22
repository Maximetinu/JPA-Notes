package com.jpanotesproject.BusinessLogic;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.jpanotesproject.daos.UserDAO;

public class EntityManagerController {

	private static EntityManagerController instance = null;


	private static EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("JPA Notes Project");
	private static EntityManager em;
	
	private EntityManagerController() {
	}
	
	public static EntityManagerController getInstance() {
		if (instance == null) {
			instance = new EntityManagerController();
		}
		return instance;
	}
	
	public static EntityManager getEntityManager() {
		if (em == null) {
			em = emfactory.createEntityManager();
		}
		
		return em;
	}
}
