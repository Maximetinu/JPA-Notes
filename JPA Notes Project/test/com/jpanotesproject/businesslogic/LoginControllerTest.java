package com.jpanotesproject.businesslogic;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.jpanotesproject.mocks.EntityManagerMock;

public class LoginControllerTest {

	LoginController controller;
	EntityManagerMock emMock;

	@Before
	public void setUp() {
		emMock = new EntityManagerMock();
		controller = LoginController.getInstance(emMock);
	}

	@Test
	public void loginTest() {
		emMock.resetMockFlags();
		controller.login("aaa", "aaa");
		Assert.assertTrue(emMock.isCreateQueryCalled());
	}

	@Test
	public void registerTest() {
		emMock.resetMockFlags();
		controller.register("aa", "aaa", "aaa@aaa.aaa");
		Assert.assertTrue(emMock.isGetTransactionCalled());
	}

}
