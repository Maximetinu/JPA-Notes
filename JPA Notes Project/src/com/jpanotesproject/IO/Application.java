package com.jpanotesproject.IO;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.jpanotesproject.daos.TagDAO;
import com.jpanotesproject.model.Tag;

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
		// REALLY NIGGA

		em.getTransaction().commit();

		em.close();
		emfactory.close();
	}

}
