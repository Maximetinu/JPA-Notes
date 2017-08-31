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
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.MapKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name = "NOTE")
public /* abstract */ class Note extends BaseEntity {

	@Column(name = "TITLE", length = 255)
	private String title;
	@Column(name = "CREATION_DATE")
	private java.sql.Timestamp creationDate;
	@Column(name = "LAST_EDIT_DATE")
	private java.sql.Timestamp lastEditDate;;

	@ManyToOne
	@JoinColumn(name = "AUTHORSHIP", referencedColumnName = "USERNAME")
	private User author;

	@ElementCollection
	@CollectionTable(name = "NOTE_HAS_BEEN_SHARED_TO_USER", joinColumns = @JoinColumn(name = "NOTE_ID", referencedColumnName = "ID"))
	@MapKeyJoinColumn(name = "USER_THAT_NOTE_HAS_BEEN_SHARED_TO", referencedColumnName = "USERNAME")
	@Column(name = "PERMISSION_LEVEL")
	private Map<User, Integer> sharedUsers;

	@ManyToMany
	@JoinTable(name = "NOTE_HAS_TAGS", joinColumns = {
			@JoinColumn(name = "NOTE_ID", referencedColumnName = "ID") }, inverseJoinColumns = {
					@JoinColumn(name = "TAG_TEXT", referencedColumnName = "TAG_TEXT") })
	private List<Tag> tags;

	public Note(User author, String title) {
		super();
		this.author = author;
		this.title = title;

		long now = new java.util.Date().getTime();
		this.creationDate = new java.sql.Timestamp(now);
		this.lastEditDate = creationDate;

		tags = new ArrayList<Tag>();

		sharedUsers = new HashMap<User, Integer>();

		author.addAuthorNote(this);
	}

	public void addTag(Tag t) {
		tags.add(t);
	}

	// Comfortable way to create and add a Tag, but remember to persist the returned
	// one
	public Tag addTag(String s) {
		Tag t = new Tag(s);
		tags.add(t);
		return t;
	}

	public void removeTag(Tag t) {
		tags.remove(t);
	}

	public List<Tag> getTagsList() {
		return tags;
	}

	public Note() {
		super();
	}

	public void shareWith(User u) {
		sharedUsers.put(u, 1);
		u.shareNote(this, 1);
	}

	public void shareWith(User u, int permissionLevel) {
		sharedUsers.put(u, permissionLevel);
		u.shareNote(this, permissionLevel);
	}
	//
	// public boolean canRead(User user) {
	// return sharedUsers.containsKey(user) && sharedUsers.get(user) >= 1;
	// }
	//
	// public boolean canReadAndWrite(User user) {
	// return sharedUsers.containsKey(user) && sharedUsers.get(user) == 2;
	// }
	//
	// public void setPermission(User user, Integer permissionLevel) {
	// if (permissionLevel > 0)
	// sharedUsers.put(user, permissionLevel);
	// else
	// sharedUsers.remove(user);
	// }

	public java.sql.Timestamp getCreationDate() {
		return creationDate;
	}

	private void updateLastEditDate() {
		long now = new java.util.Date().getTime();
		this.lastEditDate = new java.sql.Timestamp(now);
	}

	public java.sql.Timestamp getLastEditDate() {
		return lastEditDate;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
		this.updateLastEditDate();
	}

	@Override
	public String toString() {
		return "Note [creationDate=" + creationDate + ", title=" + title + "]";
	}
}
