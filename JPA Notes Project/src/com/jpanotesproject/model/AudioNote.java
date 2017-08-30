package com.jpanotesproject.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name = "AUDIO_NOTE")
@PrimaryKeyJoinColumn(name="NOTE.ID")
public class AudioNote extends Note  {
	@Column(name = "CONTENT")
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