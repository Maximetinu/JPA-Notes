package com.jpanotesproject.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name = "AUdio_NOTE")
@PrimaryKeyJoinColumn(name="NOTE.ID")
public class AudioNote extends Note  {
	@Column(name = "CONTENT")
	private Object img = "";
	
	public AudioNote() {
		super();
	}
	
	public AudioNote(Object img) {
		this.img = img;
	}
	
	public Object getAudio() {
		return img;
	}
	
	public void setAudio(Object img) {
		this.img = img;
	}
}