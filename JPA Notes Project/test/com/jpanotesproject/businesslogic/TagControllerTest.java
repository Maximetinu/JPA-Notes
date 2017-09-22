package com.jpanotesproject.businesslogic;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.jpanotesproject.mocks.EntityManagerMock;

public class TagControllerTest {

	TagController controller;
	EntityManagerMock emMock;

	@Before
	public void setUp() {
		emMock = new EntityManagerMock();
		controller = TagController.getInstance(emMock);
	}

	@Test
	public void registerNewTagTest() {
		emMock.resetMockFlags();
		controller.registerNewTag("aaa");
		Assert.assertTrue(emMock.isGetTransactionCalled());
	}

}
