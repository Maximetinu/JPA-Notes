package com.jpanotesproject.model;

import java.util.HashMap;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table
public /* abstract */ class Note extends BaseEntity {
	
	private String title;
	private String creationDate;

	@ManyToOne
	private User author;

	@ElementCollection
	private HashMap<User, Integer> sharedUsers;

	public Note(User author, String creation_date, String title) {
		super();
		this.author = author;
		this.title = title;
		creationDate = creation_date;
		
		sharedUsers = new HashMap<User, Integer>();
	}

	public Note() {
		super();
	}
	
	public boolean canRead(User user) {
		return sharedUsers.containsKey(user) && sharedUsers.get(user) >= 1;
	}

	public boolean canReadAndWrite(User user) {
		return sharedUsers.containsKey(user) && sharedUsers.get(user) == 2;
	}
	
	public void setPermission(User user, Integer permissionLevel) {
		if (permissionLevel > 0)
			sharedUsers.put(user, permissionLevel);
		else
			sharedUsers.remove(user);
	}

	public String getCreationDate() {
		return creationDate;
	}

	private void setCreationDate(String creationDate) {
		this.creationDate = creationDate;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Override
	public String toString() {
		return "Note [creationDate=" + creationDate + ", title=" + title + "]";
	}
}