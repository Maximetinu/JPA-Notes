package com.jpanotesproject.daos;

import javax.persistence.EntityManager;
import com.jpanotesproject.model.Tag;

public class TagDAO extends BaseDAO<Tag> {
	public TagDAO(EntityManager context) {
		super(Tag.class, context);
	}
}
