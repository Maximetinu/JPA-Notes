package com.jpanotesproject.daos;

import javax.persistence.Query;
import javax.persistence.EntityManager;
import com.jpanotesproject.model.User;

public class UserDAO  extends BaseDAO<User>{
	
	public UserDAO(EntityManager context) {
		super(User.class, context);
	}
	
	public User findByUsername(String username) {
		User result = null;
		
		Query query  =   super.entityManager.createQuery("select u from User u where u.username=:usr").setParameter("usr", username);
		
		if (!query.getResultList().isEmpty()) {
			result = (User) query.getResultList().get(0);
		}
		
		return result;
		
	}
}
