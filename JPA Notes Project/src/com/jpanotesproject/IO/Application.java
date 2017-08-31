package com.jpanotesproject.IO;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.jpanotesproject.daos.NoteDAO;
import com.jpanotesproject.daos.TagDAO;
import com.jpanotesproject.daos.UserDAO;
import com.jpanotesproject.model.Note;
import com.jpanotesproject.model.Tag;
import com.jpanotesproject.model.User;

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

		// USUARIOS: Metinu, Gang, Oceloto, Penquiu, Ulises

		// NOTAS: NOTA-METINU-A, NOTA-METINU-B, NOTA-METINU-C -> METINU
		// NOTA-GANG-N -> GANG
		// NOTA-OCELOTO-X, NOTA-OCELOTO-Y, NOTA-OCELOTO-Z -> OCELOTO

		// NOTAS_COMPARTIDAS:
		// NOTA-METINU-C -> OCELOTO(2), GANG(1)
		// NOTA-GANG-N -> PENQUIU(2), METINU(2)
		// NOTA-OCELOTO-Y -> GANG(1)

		// TAGS: NOTA-METINU-C -> T1, T2, T3
		// NOTA-METINU-A -> T1, T7
		// NOTA-GANG-N -> T3, T5

		/* --------- START ------------- */

		/// MODEL
		// USERS
		User u1 = new User("metinu", "miwe117", "maximetinu@gmail.com");
		User u2 = new User("gang", "gangRulses", "ultrajavierman@hotmail.com");
		User u3 = new User("oceloto", "ocelotito_guapo1", "maxiguerrero@hotmail.com");
		User u4 = new User("penquiu", "mickeyesmiamigo", "penquiu@ono.com");
		User u5 = new User("ulises", "contraUlisescom", "ulyssesdarkline@gmail.com");

		// NOTES
		Note n1 = new Note(u1, "NOTA-METINU-A");
		Note n2 = new Note(u1, "NOTA-METINU-B");
		Note n3 = new Note(u1, "NOTA-METINU-C");

		Note n4 = new Note(u2, "NOTA-GANG-N");

		Note n5 = new Note(u3, "NOTA-OCELOTO-Y");
		Note n6 = new Note(u4, "NOTA-OCELOTO-X");
		Note n7 = new Note(u5, "NOTA-OCELOTO-Z");

		// SHARED_NOTES
		n3.shareWith(u3, 2);
		n3.shareWith(u2, 1);

		n4.shareWith(u4, 2);
		n4.shareWith(u1, 2);

		n5.shareWith(u2, 1); // COMO GANG YA TENÍA UNA NOTA COMPARTIDA NO SE HA METIDO

		// TAGS CREATION
		Tag t1 = new Tag("T1");
		Tag t2 = new Tag("T2");
		Tag t3 = new Tag("T3");
		Tag t4 = new Tag("T4");
		Tag t5 = new Tag("T5");
		Tag t6 = new Tag("T6");
		Tag t7 = new Tag("T7");

		// TAG ASSIGNMENT
		n3.addTag(t1);
		n3.addTag(t2);
		n3.addTag(t3);

		n1.addTag(t1);
		n1.addTag(t7);

		n4.addTag(t3);
		n4.addTag(t5);

		/// DAOS
		// DAOS CREATION
		UserDAO uDAO = new UserDAO(em);
		NoteDAO nDAO = new NoteDAO(em);
		TagDAO tDAO = new TagDAO(em);

		// PERSIST

		uDAO.persist(u1);
		uDAO.persist(u2);
		uDAO.persist(u3);
		uDAO.persist(u4);
		uDAO.persist(u5);

		nDAO.persist(n1);
		nDAO.persist(n2);
		nDAO.persist(n3);
		nDAO.persist(n4);
		nDAO.persist(n5);
		nDAO.persist(n6);
		nDAO.persist(n7);

		tDAO.persist(t1);
		tDAO.persist(t2);
		tDAO.persist(t3);
		tDAO.persist(t4);
		tDAO.persist(t5);
		tDAO.persist(t6);
		tDAO.persist(t7);

		/* ---------- END ------------- */

		em.getTransaction().commit();

		em.close();
		emfactory.close();
	}

}
