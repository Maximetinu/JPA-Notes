package com.jpanotesproject.BusinessLogic;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.jpanotesproject.daos.NoteDAO;
import com.jpanotesproject.daos.TagDAO;
import com.jpanotesproject.model.Note;
import com.jpanotesproject.model.Tag;
import com.jpanotesproject.model.TextNote;
import com.jpanotesproject.model.User;

public class NoteController {

	
	private static NoteController instance = null;


	private static EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("JPA Notes Project");
	private static EntityManager em = emfactory.createEntityManager();
	private static NoteDAO nDAO  = new NoteDAO(em);
	
	private NoteController() {
	}
	
	public static NoteController getInstance() {
		if (instance == null) {
			instance = new NoteController();
		}
		return instance;
	}
	
	public TextNote NewTextNote(User author, String title, String text) {
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
	
	public boolean AddTagToTextNote(TextNote note, String tag_str) {
		boolean result = false;
		
		Tag tag = TagController.getInstance().getTagForce(tag_str);

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
	
	public boolean EditTextNote(TextNote note, String new_content) {
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
	
	public boolean ShareNote(Note note, User user) {
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
	
	public void Editable(Note note) {
		nDAO.persist(note);
	}
	

}
