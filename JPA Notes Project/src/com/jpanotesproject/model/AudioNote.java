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
	private Object audio = "";
	
	public AudioNote() {
		super();
	}
	
	public AudioNote(User author, String title, Object audio) {
		super(author, title);
		this.audio = audio;
	}
	
	public Object getAudio() {
		return audio;
	}
	
	public void setAudio(Object audio) {
		this.audio = audio;
	}
}