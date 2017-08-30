package com.jpanotesproject.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name = "IMAGE_NOTE")
@PrimaryKeyJoinColumn(name="NOTE.ID")
public class ImageNote extends Note  {
	@Column(name = "CONTENT")
	private Object img = "";
	
	public ImageNote() {
		super();
	}
	
	public ImageNote(User author, String title, Object img) {
		super(author, title);
		this.img = img;
	}
	
	public Object getImage() {
		return img;
	}
	
	public void setImage(Object img) {
		this.img = img;
	}
}
