package com.jpanotesproject.daos;

import javax.persistence.EntityManager;

import com.jpanotesproject.model.Note;

public class NotesDAO extends BaseDAO<Note>{

	public NotesDAO(EntityManager context) {
		super(Note.class, context);
	}

}
