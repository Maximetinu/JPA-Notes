package com.jpanotesproject.daos;

import java.util.List;

import javax.persistence.Query;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import com.jpanotesproject.model.User;

public class UserDAO  extends BaseDAO<User>{
	
	private EntityManager context;
	
	public UserDAO(EntityManager context) {
		super(User.class, context);
		this.context = context;
	}
	
	public User findByUsername(String username) {
		User result = null;
		
		Query query  =   context.createQuery("select u from User u where u.username=:usr").setParameter("usr", username);
		
		if (!query.getResultList().isEmpty()) {
			result = (User) query.getResultList().get(0);
		}
		
		return result;
		
	}
}
