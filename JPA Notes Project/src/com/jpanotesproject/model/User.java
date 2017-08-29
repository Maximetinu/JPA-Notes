package com.jpanotesproject.model;

import java.util.ArrayList;
import java.util.HashMap;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "USER")
public class User extends BaseEntity {

	@Column(name = "USERNAME", unique = true)
	private String username;

	@Column(name = "PASSWORD")
	private String password;
	
	@Column(name = "EMAIL", unique = true)
	private String email;

	@Column(name = "RESGISTRATION_DATE")
	private java.sql.Timestamp registrationDate;

	@ElementCollection
	@Column(name = "SHARED_NOTES")
	private HashMap<Note, Integer> sharedNotes;

	@OneToMany
	private ArrayList<Note> ownNotes;

	public User(String name, String password, String email) {
		super();
		this.username = name;
		this.password = password;
		this.email = email;

		long now = new java.util.Date().getTime();
		this.registrationDate = new java.sql.Timestamp(now);
	}

	public User() {
		super();
	}
	
	public boolean canRead(Note note) {
		if (ownNotes.contains(note)) 
			return true;
		else
			return sharedNotes.containsKey(note) && sharedNotes.get(note) >= 1;
	}

	public boolean canReadAndWrite(Note note) {
		if (ownNotes.contains(note)) 
			return true;
		else
			return sharedNotes.containsKey(note) && sharedNotes.get(note) == 2;
	}
	
	public void setPermission(Note note, Integer permissionLevel) {
		if (permissionLevel > 0)
			sharedNotes.put(note, permissionLevel);
		else
			sharedNotes.remove(note);
	}
	
	public java.sql.Timestamp getRegistrationDate(){
		return registrationDate;
	}
}
