package com.jpanotesproject.businesslogic;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.jpanotesproject.mocks.EntityManagerMock;
import com.jpanotesproject.model.TextNote;
import com.jpanotesproject.model.User;

public class NoteControllerTest {

	NoteController controller;
	EntityManagerMock emMock;

	@Before
	public void setUp() {
		emMock = new EntityManagerMock();
		controller = NoteController.getInstance(emMock);
	}

	@Test
	public void newTextNoteTest() {
		emMock.resetMockFlags();
		controller.newTextNote(new User("aaa", "aaa", "aaa@aaa.aaa"), "aaa", "aaa");
		Assert.assertTrue(emMock.isGetTransactionCalled());
	}

	@Test
	public void addTagToTextNoteTest() {
		emMock.resetMockFlags();
		controller.addTagToTextNote(new TextNote(new User("aaa", "aaa", "aaa@aaa.aaa"), "aaaa", "aaa"), "aaaa");
		Assert.assertTrue(emMock.isGetTransactionCalled());
	}

	@Test
	public void editTextNoteTest() {
		emMock.resetMockFlags();
		controller.editTextNote(new TextNote(new User("aaa", "aaa", "aaa@aaa.aaa"), "aaaa", "aaa"), "aaaa");
		Assert.assertTrue(emMock.isGetTransactionCalled());
	}

	@Test
	public void shareNoteTest() {
		emMock.resetMockFlags();
		User u = new User("aaa", "aaa", "aaa@aaa.aaa");
		TextNote n = new TextNote(u, "aaa", "aaa");
		controller.shareNote(n, u);
		Assert.assertTrue(emMock.isGetTransactionCalled());
	}

}
