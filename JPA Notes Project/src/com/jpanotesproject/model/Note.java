package com.jpanotesproject.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Note extends BaseEntity {

	@Column(name = "title", length = 255)
	private String title;

	@Column(name = "creation_date")
	private java.sql.Timestamp creationDate;

	@Column(name = "last_edit_date")
	private java.sql.Timestamp lastEditDate;

	@ManyToOne
	@JoinColumn(name = "author", referencedColumnName = "username") // Optional, but we want reference by Username instead of id. More visual.
	private User author;

	@ManyToMany
	@JoinTable(name = "Note_Has_Tags", joinColumns = { @JoinColumn(name = "note_id", referencedColumnName = "id") }, inverseJoinColumns = {
			@JoinColumn(name = "tag_text", referencedColumnName = "tag_text") })
	private List<Tag> tags;

	public Note(User author, String title) {
		super();
		this.author = author;
		this.title = title;

		long now = new java.util.Date().getTime();
		this.creationDate = new java.sql.Timestamp(now);
		this.lastEditDate = creationDate;

		tags = new ArrayList<Tag>();

		author.addAuthorNote(this);
	}

	public void addTag(Tag t) {
		tags.add(t);
	}

	// WARNING
	// Comfortable way to create and add a Tag, but remember to persist the returned
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
		u.shareNote(this, 1);
	}

	public void shareWith(User u, int permissionLevel) {
		u.shareNote(this, permissionLevel);
	}

	public boolean canRead(User user) {
		return user.getSharedNotes().containsKey(this) && user.getSharedNotes().get(this) >= 1;
	}

	public boolean canReadOnly(User user) {
		return user.getSharedNotes().containsKey(this) && user.getSharedNotes().get(this) == 1;
	}

	public boolean canReadAndWrite(User user) {
		return user.getSharedNotes().containsKey(this) && user.getSharedNotes().get(this) == 2;
	}

	public void setPermission(User user, Integer permissionLevel) {
		user.setPermission(this, permissionLevel);
	}

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
