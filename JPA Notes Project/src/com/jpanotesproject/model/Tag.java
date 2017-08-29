package com.jpanotesproject.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table
public class Tag extends BaseEntity{
	
	@Column(unique = true)
	private String tagText;
	
	public Tag() {
		super();
	}
	
	public Tag(String tagText) {
		this.tagText = tagText;
	}
	
	public String getTagText() {
		return tagText;
	}
	
	public void setTagText(String tagText) {
		this.tagText = tagText;
	}
}
