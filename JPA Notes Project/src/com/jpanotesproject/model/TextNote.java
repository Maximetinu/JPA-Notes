package com.jpanotesproject.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name = "TEXT_NOTE")
@PrimaryKeyJoinColumn(name="employeeId")
public class TextNote extends Note  {
	@Column(name = "TEXT")
	private String text = "";
	
	public TextNote() {
		super();
	}
	
	public TextNote(String text) {
		this.text = text;
	}
	
	public String getText() {
		return text;
	}
	
	public void setText(String text) {
		this.text = text;
	}
}
