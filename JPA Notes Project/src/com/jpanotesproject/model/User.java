package com.jpanotesproject.model;

import java.util.ArrayList;
import java.util.HashMap;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table
public class User extends BaseEntity {

	@Column(unique = true)
	private String username;

	private String password;
	private String email;
	private String registration_date;

	@ElementCollection
	private HashMap<Note, Integer> sharedNotes;

	@OneToMany
	private ArrayList<Note> ownNotes;

	public User(String name, String password, String email, String registration_date) {
		super();
		this.username = name;
		this.password = password;
		this.email = email;
		this.registration_date = registration_date;
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
}
