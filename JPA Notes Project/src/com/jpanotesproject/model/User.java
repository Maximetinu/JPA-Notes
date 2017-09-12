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
@Table(name = "User")
public class User extends BaseEntity {

	@Column(name = "username", unique = true, length = 255)
	private String username;

	@Column(name = "password", length = 255)
	private String password;

	@Column(name = "email", unique = true, length = 255)
	private String email;

	@Column(name = "resgistration_date")
	private java.sql.Timestamp registrationDate;

	@ElementCollection
	@CollectionTable(name = "UserHasSharedNotes", joinColumns = @JoinColumn(name = "username", referencedColumnName = "username"))
	@MapKeyJoinColumn(name = "shared_note", referencedColumnName = "id")
	@Column(name = "PERMISSION_LEVEL")
	private Map<Note, Integer> sharedNotes;

	@OneToMany
	@JoinTable(name = "UserHasNotes", joinColumns = { @JoinColumn(name = "author_name", referencedColumnName = "username") }, inverseJoinColumns = {
			@JoinColumn(name = "note_id", referencedColumnName = "id") })
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

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void addAuthorNote(Note note) {
		// If ! ownNotes.contains(note) ¿?¿?¿? Solo si es una List. Si fuera un Set no habría que hacer esta comprobación
		// Luego, si hago esta comprobación parece que ownNotes no se rellena nunca
		// if (!ownNotes.contains(note))
		this.ownNotes.add(note);
	}

	// TODO: add this user to note's SharedUsers ?¿?¿
	public void shareNote(Note n, int permissionLevel) {
		if (permissionLevel > 0)
			this.sharedNotes.put(n, permissionLevel);
		else
			this.sharedNotes.remove(n);
	}

	public void unshareNote(Note n) {
		this.sharedNotes.remove(n);
	}

	public boolean canRead(Note note) {
		return ownNotes.contains(note) || (sharedNotes.containsKey(note) && sharedNotes.get(note) >= 1);
	}

	public boolean canReadAndWrite(Note note) {
		return ownNotes.contains(note) || (sharedNotes.containsKey(note) && sharedNotes.get(note) == 2);
	}

	public void setPermission(Note note, Integer permissionLevel) {
		if (permissionLevel > 0)
			sharedNotes.put(note, permissionLevel);
		else
			sharedNotes.remove(note);
	}

	// public Map<Note, Integer> getSharedNotes() {
	// return sharedNotes;
	// }

	public java.sql.Timestamp getRegistrationDate() {
		return registrationDate;
	}

	public List<Note> getOwnNotes() {
		return ownNotes;
	}

	public Map<Note, Integer> getSharedNotes() {
		return sharedNotes;
	}
}
