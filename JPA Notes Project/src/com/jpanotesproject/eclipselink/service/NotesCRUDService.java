package com.jpanotesproject.eclipselink.service;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import com.jpanotesproject.eclipselink.entity.Note;

/*
 * This class is a test. It is expected that, maybe, implement a CRUD Interface, or a BaseService interface
 * Our services will be the BusinessLogic's controllers from our example project and here DAO's will be used
 * It is expected to parameterize this methods and remove Output and Input operations from them
 * All this is said as a first statement, in the absence of a deeper knowledge
 * By the way CRUD means create, read, update and delete
 */
public class NotesCRUDService {
	
	public void create() {
		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory( "JPA Notes Project" );
	      
	    EntityManager entitymanager = emfactory.createEntityManager( );
	    entitymanager.getTransaction( ).begin( );
/*
	    Note note = new Note( ); 
	    note.setCreationDate( "1201" );
	    note.setTitle( "Gopal" );
	      */
	 //   entitymanager.persist( note );
	    entitymanager.getTransaction( ).commit( );

	    entitymanager.close( );
	    emfactory.close( );
	}
	
	// AKA Find
	public void read() {
		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory( "JPA Notes Project" );
	    EntityManager entitymanager = emfactory.createEntityManager();
	    Note note = entitymanager.find( Note.class, "Gopal" );

	    System.out.println("note Creation Date = " + note.getCreationDate( ));
	    System.out.println("note Title = " + note.getTitle( ));
	}
	
	public void update() {
		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory( "JPA Notes Project" );
	      
	    EntityManager entitymanager = emfactory.createEntityManager( );
	    entitymanager.getTransaction( ).begin( );
	    Note note = entitymanager.find( Note.class, "Gopal" );
	      
	    //before update
	    System.out.println( note );
	    note.setCreationDate( "1299" );
	    entitymanager.getTransaction( ).commit( );
	      
	    //after update
	    System.out.println( note );
	    entitymanager.close();
	    emfactory.close();
	}
	
	public void delete() {
		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory( "JPA Notes Project" );
		EntityManager entitymanager = emfactory.createEntityManager( );
		entitymanager.getTransaction( ).begin( );
		  
		Note note = entitymanager.find( Note.class, "Gopal" );
		entitymanager.remove( note );
		entitymanager.getTransaction( ).commit( );
		entitymanager.close( );
		emfactory.close( );		
	}

}
