package com.jpanotesproject.daos;

import org.junit.Assert;
import org.junit.Test;

import com.jpanotesproject.mocks.EntityManagerMock;

public class UserDAOTest {

	@Test
	public void findByUsernameTest() {
		EntityManagerMock em = new EntityManagerMock();
		UserDAO uDAO = new UserDAO(em);
		uDAO.findByUsername("fakeUsername");
		Assert.assertTrue(em.isCreateQueryCalled());
	}

}
