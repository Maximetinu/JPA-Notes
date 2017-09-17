package com.jpanotesproject.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "Tag")
public class Tag extends BaseEntity {

	@Column(name = "tag_text", unique = true, length = 255)
	private String tag_text;

	public Tag() {
		super();
	}

	public Tag(String tagText) {
		this.tag_text = tagText;
	}

	public String getTagText() {
		return tag_text;
	}

	public void setTagText(String tagText) {
		this.tag_text = tagText;
	}
}
