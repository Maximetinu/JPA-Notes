package com.jpanotesproject.daos;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.jpanotesproject.model.Tag;
import com.jpanotesproject.model.User;

public class TagDAO extends BaseDAO<Tag> {
	
	public TagDAO(EntityManager context) {
		super(Tag.class, context);
	}
	
	public Tag findByTag(String tag_text) {
		Tag result = null;
		
		Query query  =   super.entityManager.createQuery("select t from Tag t where t.tag_text=:tag").setParameter("tag", tag_text);


		if (!query.getResultList().isEmpty()) {
			result = (Tag) query.getResultList().get(0);
		}
		
		return result;
		
	}
}
