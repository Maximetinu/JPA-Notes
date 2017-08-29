package com.jpanotesproject.IO;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.jpanotesproject.daos.*;
import com.jpanotesproject.model.*;

/*
 * This class is meant to be the interface of our application by managing user's input by a terminal menu (in this basic case)
 * Here services will be used, without creating dependencies with DAOs nor Model/Entities
 * ATM it is a testing main
 */
public class Application {
	
	static EntityManager em;

	public static void main(String[] args) {
		
		// Tag entity working
		// TODO: Uncomment from persistence.xml User and Note (one by one), debug and correct
		
		initEntityManager();

		Tag t = new Tag("taggy");
		TagDAO tDAO = new TagDAO(em);
		tDAO.persist(t);
		
		commitChanges();
		closeEntityManager();	    
	}
	
	private static void commitChanges() {
		em.getTransaction( ).commit( );
	}
	
	private static void initEntityManager() {
		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory( "JPA Notes Project" );
	    EntityManager em = emfactory.createEntityManager( );
	    em.getTransaction( ).begin( );
	}
	
	private static void closeEntityManager() {
		em.close( );
	    emfactory.close( );
	}

}
