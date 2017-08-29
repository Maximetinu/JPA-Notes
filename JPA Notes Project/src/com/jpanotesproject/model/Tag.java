package com.jpanotesproject.model;

public class Tag extends BaseEntity{
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
