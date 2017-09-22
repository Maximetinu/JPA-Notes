package com.jpanotesproject.daos;

import org.junit.Assert;
import org.junit.Test;

import com.jpanotesproject.mocks.EntityManagerMock;

public class TagDAOTest {

	@Test
	public void findByTagnameTest() {
		EntityManagerMock em = new EntityManagerMock();
		TagDAO uDAO = new TagDAO(em);
		uDAO.findByTag("tagText");
		Assert.assertTrue(em.isCreateQueryCalled());
	}

}
