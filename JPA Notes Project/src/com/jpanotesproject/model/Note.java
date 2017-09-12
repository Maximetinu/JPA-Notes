package com.jpanotesproject.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.MapKeyJoinColumn;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Note extends BaseEntity {

	// TITLE limit to 255 characters
	@Column(name = "title", length = 255)
	private String title;

	// Note creation time
	@Column(name = "creation_date")
	private java.sql.Timestamp creationDate;

	// Note last edit time
	@Column(name = "last_edit_date")
	private java.sql.Timestamp lastEditDate;;

	// AUTHOR = Author of the note --> From column Username
	@ManyToOne
	@JoinColumn(name = "author", referencedColumnName = "username") // Optional, but we want reference by Username
																	// instead of id
	private User author;

	// Collection
	@ElementCollection
	// Optional:
	// New collection table (NoteHasBeenSharedUser) with three column (note_id,
	// user_id, value) || One entry by user have permission in the note
	@CollectionTable(name = "NoteHasBeenSharedUser", joinColumns =
	// (NoteHasBeenSharedUser.note_id = Note.id)
	@JoinColumn(name = "note_id", referencedColumnName = "id"))
	// (NoteHasBeenSharedUser.user_that_note_has_been_shared_to = User.username) -->
	// User referenced by username instead of id
	@MapKeyJoinColumn(name = "user_that_note_has_been_shared_to", referencedColumnName = "username")
	// vale --> permission_level (NoteHasBeenSharedUser.permission_level)
	@Column(name = "permission_level")
	private Map<User, Integer> sharedUsers;

	// tags is a list of entities
	@ManyToMany
	// Optional:
	// New table (NoteHasTags) with two column (note_id and tag_text)
	@JoinTable(name = "NoteHasTags", joinColumns = {
			// Note --> Tag (NoteHasTags.note_id = Note.note_id)
			@JoinColumn(name = "note_id", referencedColumnName = "id") }, inverseJoinColumns = {
					// Tag --> Note (NoteHasTags.tag_text = Tag.tag_text)
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
		sharedUsers = new HashMap<User, Integer>();

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
		sharedUsers.put(u, 1);
		u.shareNote(this, 1);
	}

	public void shareWith(User u, int permissionLevel) {
		sharedUsers.put(u, permissionLevel);
		u.shareNote(this, permissionLevel);
	}

	public boolean canRead(User user) {
		return sharedUsers.containsKey(user) && sharedUsers.get(user) >= 1;
	}

	public boolean canReadOnly(User user) {
		return sharedUsers.containsKey(user) && sharedUsers.get(user) == 1;
	}

	public boolean canReadAndWrite(User user) {
		return sharedUsers.containsKey(user) && sharedUsers.get(user) == 2;
	}

	public void setPermission(User user, Integer permissionLevel) {
		if (permissionLevel > 0)
			sharedUsers.put(user, permissionLevel);
		else {
			sharedUsers.remove(user);
			user.shareNote(this, 0);
		}
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

	public Map<User, Integer> getSharedUsers() {
		return sharedUsers;
	}

	@Override
	public String toString() {
		return "Note [creationDate=" + creationDate + ", title=" + title + "]";
	}
}
