package com.jpanotesproject.IO;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.jpanotesproject.daos.NotesDAO;
import com.jpanotesproject.daos.TagDAO;
import com.jpanotesproject.daos.UserDAO;
import com.jpanotesproject.model.Note;
import com.jpanotesproject.model.Tag;
import com.jpanotesproject.model.User;
import com.jpanotesproject.model.TextNote;

/*
 * This class is meant to be the interface of our application by managing user's input by a terminal menu (in this basic case)
 * Here services will be used, without creating dependencies with DAOs nor Model/Entities
 * ATM it is a testing main
 */
public class Application {

	public static void main(String[] args) {

		// Tag entity working
		// TODO: Uncomment from persistence.xml User and Note (one by one), debug and
		// correct

		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("JPA Notes Project");
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();

		// NotesCRUDService tDAO = new NotesCRUDService();
		// tDAO.create();

		Tag t = new Tag("haaa");
		TagDAO tDAO = new TagDAO(em);
		tDAO.persist(t);

		User user = new User("user_a", "my_password", "johnsnow@agagaga.com");
		UserDAO userDAO = new UserDAO(em);
		userDAO.persist(user);

		TextNote text_note = new TextNote(user, "title of the note", "text text text");
		NotesDAO notesDAO = new NotesDAO(em);
		notesDAO.persist(text_note);


	
		// REALLY NIGGA

		em.getTransaction().commit();

		em.close();
		emfactory.close();
	}

}
