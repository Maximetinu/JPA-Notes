package com.jpanotesproject.businesslogic;

import javax.persistence.EntityManager;

import com.jpanotesproject.daos.NoteDAO;
import com.jpanotesproject.model.Note;
import com.jpanotesproject.model.Tag;
import com.jpanotesproject.model.TextNote;
import com.jpanotesproject.model.User;

public class NoteController {

	private static NoteController instance = null;

	private static EntityManager em;
	private static NoteDAO nDAO;

	private NoteController() {
		em = EntityManagerController.getEntityManager();
		nDAO = new NoteDAO(em);
	}

	private NoteController(EntityManager entityManager) {
		em = entityManager;
		nDAO = new NoteDAO(em);
	}

	public static NoteController getInstance() {
		if (instance == null) {
			instance = new NoteController();
		}
		return instance;
	}

	public static NoteController getInstance(EntityManager entityManager) {
		if (instance == null) {
			instance = new NoteController(entityManager);
		}
		if (em != entityManager)
			em = entityManager;
		return instance;
	}

	public TextNote newTextNote(User author, String title, String text) {
		TextNote note = null;

		em.getTransaction().begin();
		try {
			note = new TextNote(author, title, text);
		} catch (Exception e) {
			e.getMessage();
		}
		em.getTransaction().commit();

		return note;
	}

	public boolean addTagToTextNote(TextNote note, String tag_str) {
		boolean result = false;

		Tag tag = TagController.getInstance(em).getTagForce(tag_str);

		em.getTransaction().begin();/**/

		try {
			note.addTag(tag);
			result = true;
		} catch (Exception e) {
			e.getMessage();
		}

		em.getTransaction().commit();/**/

		return result;
	}

	public boolean editTextNote(TextNote note, String new_content) {
		boolean result = false;

		em.getTransaction().begin();/**/

		try {
			note.setText(new_content);
			result = true;
		} catch (Exception e) {
			e.getMessage();
		}

		em.getTransaction().commit();/**/
		return result;
	}

	public boolean shareNote(Note note, User user) {
		boolean result = false;

		em.getTransaction().begin();/**/

		try {
			note.shareWith(user);
			result = true;
		} catch (Exception e) {
			e.getMessage();
		}

		em.getTransaction().commit();/**/

		return result;
	}

	public void editable(Note note) {
		nDAO.persist(note);
	}

}
