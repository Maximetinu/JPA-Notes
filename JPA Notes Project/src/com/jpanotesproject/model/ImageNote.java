package com.jpanotesproject.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name = "ImageNote")
public class ImageNote extends Note  {
	@Column(name = "content")
	private String img = "";
	
	public ImageNote() {
		super();
	}
	
	public ImageNote(User author, String title, String img) {
		super(author, title);
		this.img = img;
	}
	
	public String getImage() {
		return img;
	}
	
	public void setImage(String img) {
		this.img = img;
	}
}
