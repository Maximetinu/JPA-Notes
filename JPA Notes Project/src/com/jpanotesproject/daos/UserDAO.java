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
		
		String query_str = "SELECT u FROM `User` u WHERE u.username = '" + username + "'";
		
		Query query  =   context.createQuery(query_str);

		System.out.println("--------------------------------------------------------------------------------------------- " + query.getFirstResult());
		
		return result;
		
	}
}
