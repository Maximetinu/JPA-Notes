package com.jpanotesproject.businesslogic;

import javax.persistence.EntityManager;

import com.jpanotesproject.daos.UserDAO;
import com.jpanotesproject.model.Note;
import com.jpanotesproject.model.User;

public class UserController {

	private static UserController instance = null;

	private static EntityManager em;
	private static UserDAO uDAO;

	private UserController() {
		em = EntityManagerController.getEntityManager();
		uDAO = new UserDAO(em);
	}

	private UserController(EntityManager entityManager) {
		em = entityManager;
		uDAO = new UserDAO(em);
	}

	public static UserController getInstance() {
		if (instance == null) {
			instance = new UserController();
		}
		return instance;
	}

	public static UserController getInstance(EntityManager entityManager) {
		if (instance == null) {
			instance = new UserController();
		}
		if (em != entityManager)
			em = entityManager;
		return instance;
	}

	public User getUser(String username) {
		User result = null;

		try {
			result = uDAO.findByUsername(username);
			uDAO.persist(result);
		} catch (Exception e) {
			e.getMessage();
		}

		return result;
	}

	public boolean ShareNote(Note note, User user, int permissions) {
		boolean result = false;

		em.getTransaction().begin();/**/
		try {

			user.shareNote(note, permissions);
			result = true;
		} catch (Exception e) {
			e.getMessage();
		}
		em.getTransaction().commit();/**/

		return result;
	}

	public boolean Exist(String username) {
		return uDAO.findByUsername(username) != null;
	}

}
