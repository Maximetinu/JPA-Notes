package com.jpanotesproject.BusinessLogic;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.jpanotesproject.daos.UserDAO;
import com.jpanotesproject.model.User;

public class LoginController {
	
	private static LoginController instance = null;


	private static EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("JPA Notes Project");
	private static EntityManager em = emfactory.createEntityManager();
	private static UserDAO uDAO  = new UserDAO(em);;
	
	private LoginController() {
	}
	
	public static LoginController getInstance() {
		if (instance == null) {
			instance = new LoginController();
		}
		return instance;
	}
	
	public User login(String username, String password) {
		User user = null;
		
		try {
			user = uDAO.findByUsername(username);
			if (!user.getPassword().equals(password)) {
				user = null;
			}
		} catch (Exception e) {
			e.getMessage();
		}
		
		return user;
	}
	
	public User register(String username, String password, String email) {
		User user = null;

		em.getTransaction().begin();
		try {
			user = new User(username, password, email);
			uDAO.persist(user);
		} catch (Exception e) {
			e.getMessage();
		}
		em.getTransaction().commit();
		
		return user;
	}
	

}
