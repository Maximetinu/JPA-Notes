package com.jpanotesproject.daos;

import javax.persistence.EntityManager;

import com.jpanotesproject.model.User;

public class UserDAO extends BaseDAO<User>{
	public UserDAO(EntityManager context) {
		super(User.class, context);
	}

}
