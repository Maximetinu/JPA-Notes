package com.jpanotesproject.businesslogic;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.jpanotesproject.mocks.EntityManagerMock;

public class UserControllerTest {

	UserController controller;
	EntityManagerMock emMock;

	@Before
	public void setUp() {
		emMock = new EntityManagerMock();
		controller = UserController.getInstance(emMock);
	}

	@Test
	public void shareNoteTest() {
		emMock.resetMockFlags();
		controller.shareNote(null, null, 3);
		Assert.assertTrue(emMock.isGetTransactionCalled());
	}

	@Test
	public void existTest() {
		emMock.resetMockFlags();
		controller.exist("aaa");
		Assert.assertTrue(emMock.isCreateQueryCalled());
	}

}
