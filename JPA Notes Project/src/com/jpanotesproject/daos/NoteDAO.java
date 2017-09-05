package com.jpanotesproject.daos;

import javax.persistence.EntityManager;

import com.jpanotesproject.model.Note;

public class NoteDAO extends BaseDAO<Note>{
	public NoteDAO(EntityManager context) {
		super(Note.class, context);
	}

}
