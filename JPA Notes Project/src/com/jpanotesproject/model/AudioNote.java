package com.jpanotesproject.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name = "AudioNote")
public class AudioNote extends Note  {
	@Column(name = "content")
	private String audio;
	
	public AudioNote() {
		super();
	}
	
	public AudioNote(User author, String title, String audio) {
		super(author, title);
		this.audio = audio;
	}
	
	public String getAudio() {
		return audio;
	}
	
	public void setAudio(String audio) {
		this.audio = audio;
	}
}