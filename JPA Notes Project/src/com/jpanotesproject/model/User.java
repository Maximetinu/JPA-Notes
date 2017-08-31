package com.jpanotesproject.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.MapKeyJoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "USER")
public class User extends BaseEntity {

	@Column(name = "USERNAME", unique = true, length = 255)
	private String username;

	@Column(name = "PASSWORD", length = 255)
	private String password;

	@Column(name = "EMAIL", unique = true, length = 255)
	private String email;

	@Column(name = "RESGISTRATION_DATE")
	private java.sql.Timestamp registrationDate;

	// @ElementCollection
	// @CollectionTable(name = "USER_SHARED_NOTES")
	// @MapKeyColumn(name = "PERMISSION_LEVEL")
	// @MapKey
	// private Map<Note, Integer> sharedNotes;
	@ElementCollection
	@CollectionTable(name = "USER_HAS_SHARED_NOTES", joinColumns = @JoinColumn(name = "USERNAME", referencedColumnName = "USERNAME"))
	@MapKeyJoinColumn(name = "SHARED_NOTE", referencedColumnName = "ID")
	@Column(name = "PERMISSION_LEVEL")
	private Map<Note, Integer> sharedNotes;

	@OneToMany
	@JoinTable(name = "USER_HAS_NOTES", joinColumns = {
			@JoinColumn(name = "AUTHOR_NAME", referencedColumnName = "USERNAME") }, inverseJoinColumns = {
					@JoinColumn(name = "NOTE_ID", referencedColumnName = "ID") })
	private List<Note> ownNotes;

	public User(String name, String password, String email) {
		super();
		this.username = name;
		this.password = password;
		this.email = email;

		long now = new java.util.Date().getTime();
		this.registrationDate = new java.sql.Timestamp(now);

		sharedNotes = new HashMap<Note, Integer>();
		ownNotes = new ArrayList<Note>();
	}

	public User() {
		super();
	}

	public void addAuthorNote(Note note) {
		this.ownNotes.add(note);
	}

	public void shareNote(Note n, int permissionLevel) {
		this.sharedNotes.put(n, permissionLevel);
	}

	// public boolean canRead(Note note) {
	// if (ownNotes.contains(note))
	// return true;
	// else
	// return sharedNotes.containsKey(note) && sharedNotes.get(note) >= 1;
	// }
	//
	// public boolean canReadAndWrite(Note note) {
	// if (ownNotes.contains(note))
	// return true;
	// else
	// return sharedNotes.containsKey(note) && sharedNotes.get(note) == 2;
	// }
	//
	// public void setPermission(Note note, Integer permissionLevel) {
	// if (permissionLevel > 0)
	// sharedNotes.put(note, permissionLevel);
	// else
	// sharedNotes.remove(note);
	// }

	// public Map<Note, Integer> getSharedNotes() {
	// return sharedNotes;
	// }

	public java.sql.Timestamp getRegistrationDate() {
		return registrationDate;
	}

	public List<Note> getOwnNotes() {
		return ownNotes;
	}
}
